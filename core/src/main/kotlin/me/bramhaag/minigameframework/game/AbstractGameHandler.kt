package me.bramhaag.minigameframework.game

import me.bramhaag.minigameframework.event.EventHandler
import me.bramhaag.minigameframework.event.events.game.GameStartEvent
import me.bramhaag.minigameframework.handler.IHandler
import me.bramhaag.minigameframework.phase.TimedPhase
import me.bramhaag.minigameframework.player.AbstractPlayer
import me.bramhaag.minigameframework.tick.AbstractTickHandler
import me.bramhaag.minigameframework.world.AbstractWorldHandler
import java.util.*
import java.util.concurrent.atomic.AtomicInteger
import kotlin.collections.HashMap

abstract class AbstractGameHandler(val tickHandler: AbstractTickHandler, val worldHandler: AbstractWorldHandler) : IHandler {

    private val counter = AtomicInteger(0)

    val eventHandler = EventHandler()

    private val modes = ArrayList<GameMode>()
    private val games = HashMap<Int, AbstractGame>()

    fun registerGameMode(gameMode: GameMode) {
        if(!modes.contains(gameMode)) modes.add(gameMode)
    }

    fun startGame(gameMode: GameMode, player: AbstractPlayer) : AbstractGame {
        val index = counter.getAndIncrement()
        val game = gameMode.klass.newInstance()
        games[index] = game

        game.id = index
        game.gameHandler = this
        game.init(player)

        eventHandler.callEvent(player, GameStartEvent(game))

        return game
    }

    fun endGame(key: Int) {
        games.remove(key)
    }

    fun getGame(key: Int) : AbstractGame? = games[key]
    fun getGames(gameMode: GameMode) : List<AbstractGame>? = games.values.filter { it::class.java == gameMode.klass }
}