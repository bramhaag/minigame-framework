package me.bramhaag.minigameframework.event.events.game

import me.bramhaag.minigameframework.game.AbstractGame
import me.bramhaag.minigameframework.player.AbstractPlayer


class GameEndEvent(game: AbstractGame, val winner: AbstractPlayer?) : GameEvent(game)