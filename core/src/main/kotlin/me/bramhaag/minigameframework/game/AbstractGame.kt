package me.bramhaag.minigameframework.game

import me.bramhaag.minigameframework.condition.AbstractVictoryCondition
import me.bramhaag.minigameframework.event.events.game.GameEndEvent
import me.bramhaag.minigameframework.event.events.game.GameJoinEvent
import me.bramhaag.minigameframework.event.events.game.GameLeaveEvent
import me.bramhaag.minigameframework.event.events.game.GameSpectateEvent
import me.bramhaag.minigameframework.feature.AbstractFeature
import me.bramhaag.minigameframework.feature.features.WorldFeature
import me.bramhaag.minigameframework.phase.AbstractPhase
import me.bramhaag.minigameframework.player.AbstractPlayer
import java.util.*


abstract class AbstractGame(val minPlayers: Int = 1,
                            val maxPlayers: Int? = null
) {
    var id = -1

    lateinit var gameHandler: AbstractGameHandler
    lateinit var activePhase : AbstractPhase

    var players = ArrayList<AbstractPlayer>()
    var spectators = ArrayList<AbstractPlayer>()

    var phases = ArrayList<AbstractPhase>()

    abstract fun init(player: AbstractPlayer)

    /**
     * Create [AbstractPhase] of type [T]
     *
     * @return Initialized [AbstractPhase] of type [T]
     */
    inline fun <reified T: AbstractPhase> createPhase() : T {
        return createPhase(T::class.java.newInstance())
    }

    /**
     * Create [AbstractPhase] of type [T]
     *
     * @param phase Instance of [AbstractPhase]
     *
     * @return Initialized [AbstractPhase] of type [T]
     */
    fun <T : AbstractPhase> createPhase(phase: T) : T {
        phase.game = this
        phases.add(phase)
        return phase
    }

    /**
     * End current phase and load the new phase if possible, else end the game with no winner
     */
    fun endPhase() {
        activePhase.disable()
        activePhase.nextPhase?.let { setActive(it) } ?: endGame(null)
    }

    /**
     * End the game
     *
     * @param winner Winner of the game, null if none
     */
    fun endGame(winner: AbstractPlayer?) {
        //val event = GameEndEvent(this, winner)
        //gameHandler.eventHandler.callEvent(event)

        allPlayers().forEach { leave(it) }

        activePhase.disable()
        unregister()

        gameHandler.endGame(id)
    }

    /**
     * End the game because of an error
     */
    fun endGameError() {
        allPlayers().forEach {
            it.sendMessage("Er is iets fout gegaan. Het spel is gestopt.")
            leave(it)
        }

        activePhase.disable()
        unregister()

        gameHandler.endGame(id)
    }

    /**
     * Let a player join the game
     *
     * @param player The player
     *
     * @return Status of the player joining
     */
    fun join(player: AbstractPlayer) : JoinStatus {
        if(!activePhase.allowJoin || maxPlayers == players.size) {
            return spectate(player)
        }

        if (isPlaying(player)) return JoinStatus.CANNOT_JOIN_IN_GAME

        player.saveInventory()
        player.clearPotions()
        player.canFly(false)

        player.game = this

        val event = GameJoinEvent(this, player)
        gameHandler.eventHandler.callEvent(player, event)
        if(event.cancelled) {
            player.game = null
            return JoinStatus.CANNOT_JOIN
        }

        players.add(player)

        /* TODO:
         * Add to chat channel
         * Broadcast message
         */
        return JoinStatus.PLAYER
    }

    /**
     * Let a player leave the game
     *
     * @param player The player
     */
    fun leave(player: AbstractPlayer) {
        gameHandler.eventHandler.callEvent(player, GameLeaveEvent(this, player))

        players.remove(player)
        player.game = null

        spectators.remove(player)

        player.restoreInventory()
        player.canFly(true)

        if(players.size == 0) endGame(null)

        /* TODO:
         * Remove player from chat channel
         * Broadcast message
         * TP player back if required
         */
    }

    /**
     * Let a player spectate the game
     *
     * @param player The player
     *
     * @return Status of the player spectating
     */
    fun spectate(player: AbstractPlayer) : JoinStatus {
        if(players.contains(player)) {
            players.remove(player)
        }

        spectators.add(player)
        player.game = this
        gameHandler.eventHandler.callEvent(player, GameSpectateEvent(this, player))

        return JoinStatus.SPECTATOR
    }

    fun isPlaying(player: AbstractPlayer) : Boolean {
        return players.contains(player)
    }

    fun isSpectating(player: AbstractPlayer) : Boolean {
        return spectators.contains(player)
    }

    fun allPlayers() : ArrayList<AbstractPlayer> {
        val newList = ArrayList<AbstractPlayer>(players)
        newList.addAll(spectators)
        return newList
    }

    /**
     * Change the current active phase to [phase]
     *
     * @param The phase
     */
    fun setActive(phase: AbstractPhase) {
        unregister()

        phase.init()

        phase.features.forEach {
            gameHandler.tickHandler.registerTickable(it)
            gameHandler.eventHandler.registerEvents(it)
        }

        gameHandler.tickHandler.registerTickable(phase)

        activePhase = phase
    }

    /**
     * Unregister all events and [ITickable]s
     */
    fun unregister() {
        gameHandler.eventHandler.unregisterEvents()
        gameHandler.tickHandler.tickables.forEach {
            gameHandler.tickHandler.removeQueue.add(it)
        }
    }

    /**
     * Broadcast message to all player in the game
     *
     * @param message The message
     */
    fun broadcastMessage(message: String) {
        players.forEach { it.sendMessage(message) }
    }
}