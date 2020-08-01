package me.bramhaag.minigameframework.feature.features

import me.bramhaag.minigameframework.annotations.GameEvent
import me.bramhaag.minigameframework.event.events.player.PlayerBlockPlaceEvent
import me.bramhaag.minigameframework.feature.AbstractFeature


class NoBlockPlaceFeature : AbstractFeature() {

    @GameEvent
    fun onBlockPlace(e: PlayerBlockPlaceEvent) {
        e.cancelled = true
    }
}