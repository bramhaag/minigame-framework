package me.bramhaag.minigameframework.feature.features

import me.bramhaag.minigameframework.annotations.GameEvent
import me.bramhaag.minigameframework.event.events.game.GameJoinEvent
import me.bramhaag.minigameframework.feature.AbstractFeature


class HealFeature(val heal: Boolean = true, val feed: Boolean = true) : AbstractFeature() {

    override fun enable() {
        if(heal) {
            phase.game.players.forEach { it.setHealth(it.getMaxHealth()) }
        }

        if(feed) {
            phase.game.players.forEach { it.setFoodLevel(20) }
        }
    }

    @GameEvent
    fun onJoin(e: GameJoinEvent) {
        if (heal) {
            e.player.setHealth(e.player.getMaxHealth())
        }

        if (feed) {
            e.player.setFoodLevel(20)
        }
    }
}