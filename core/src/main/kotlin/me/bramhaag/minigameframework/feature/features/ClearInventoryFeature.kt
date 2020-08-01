package me.bramhaag.minigameframework.feature.features

import me.bramhaag.minigameframework.annotations.GameEvent
import me.bramhaag.minigameframework.event.events.game.GameJoinEvent
import me.bramhaag.minigameframework.feature.AbstractFeature


class ClearInventoryFeature : AbstractFeature() {

    override fun enable() {
        phase.game.players.forEach { it.clearInventory() }
    }

    @GameEvent
    fun onJoin(e: GameJoinEvent) {
        e.player.clearInventory()
    }
}