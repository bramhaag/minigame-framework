package me.bramhaag.minigameframework.event.events.game

import me.bramhaag.minigameframework.event.MinigameEvent
import me.bramhaag.minigameframework.game.AbstractGame


abstract class GameEvent(val game: AbstractGame) : MinigameEvent()