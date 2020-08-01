package me.bramhaag.minigameframework.feature.features

import me.bramhaag.minigameframework.annotations.GameEvent
import me.bramhaag.minigameframework.event.events.player.PlayerDropEvent
import me.bramhaag.minigameframework.feature.AbstractFeature


class NoItemDropFeature : AbstractFeature() {

    @GameEvent
    fun onItemDrop(e: PlayerDropEvent) {
        e.cancelled = true
    }
}
