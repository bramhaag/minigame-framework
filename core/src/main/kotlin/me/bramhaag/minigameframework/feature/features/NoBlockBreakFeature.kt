package me.bramhaag.minigameframework.feature.features

import me.bramhaag.minigameframework.annotations.GameEvent
import me.bramhaag.minigameframework.event.events.player.PlayerBlockBreakEvent
import me.bramhaag.minigameframework.feature.AbstractFeature


class NoBlockBreakFeature : AbstractFeature() {

    @GameEvent
    fun onBlockBreak(e: PlayerBlockBreakEvent) {
        e.cancelled = true
    }
}