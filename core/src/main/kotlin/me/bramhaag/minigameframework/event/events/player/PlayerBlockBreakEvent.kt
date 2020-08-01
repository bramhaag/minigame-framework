package me.bramhaag.minigameframework.event.events.player

import me.bramhaag.minigameframework.player.AbstractPlayer


class PlayerBlockBreakEvent(player: AbstractPlayer, val blockName: String) : PlayerEvent(player) {
    var dropItems: Boolean = true
}