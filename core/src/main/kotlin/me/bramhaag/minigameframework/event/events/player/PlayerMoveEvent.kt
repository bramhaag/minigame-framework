package me.bramhaag.minigameframework.event.events.player

import me.bramhaag.minigameframework.player.AbstractPlayer
import me.bramhaag.minigameframework.util.Location


class PlayerMoveEvent(player: AbstractPlayer, val from: Location, val to: Location) : PlayerEvent(player)