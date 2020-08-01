package me.bramhaag.minigameframework.event.events.player

import me.bramhaag.minigameframework.player.AbstractPlayer


class PlayerBlockPlaceEvent(player: AbstractPlayer, val blockName: String) : PlayerEvent(player)