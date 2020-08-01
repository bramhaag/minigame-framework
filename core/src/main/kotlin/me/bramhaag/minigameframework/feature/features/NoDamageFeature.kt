package me.bramhaag.minigameframework.feature.features

import me.bramhaag.minigameframework.annotations.GameEvent
import me.bramhaag.minigameframework.event.events.player.PlayerDamageEvent
import me.bramhaag.minigameframework.feature.AbstractFeature


class NoDamageFeature : AbstractFeature() {

    @GameEvent
    fun onPlayerDamage(e: PlayerDamageEvent) {
        e.cancelled = true
    }
}