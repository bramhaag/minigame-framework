package me.bramhaag.minigameframework.event.events.player

import me.bramhaag.minigameframework.event.MinigameEvent
import me.bramhaag.minigameframework.player.AbstractPlayer


abstract class PlayerEvent(val player: AbstractPlayer) : MinigameEvent()