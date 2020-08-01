package me.bramhaag.minigameframework.feature.features

import me.bramhaag.minigameframework.annotations.GameEvent
import me.bramhaag.minigameframework.event.events.game.GameJoinEvent
import me.bramhaag.minigameframework.feature.AbstractFeature
import me.bramhaag.minigameframework.player.GameMode


class GameModeFeature : AbstractFeature() {

    lateinit var gameMode: GameMode

    override fun enable() {
        phase.game.players.forEach { it.setGameMode(gameMode) }
    }

    @GameEvent
    fun onJoin(e: GameJoinEvent) {
        e.player.setGameMode(gameMode)
    }
}