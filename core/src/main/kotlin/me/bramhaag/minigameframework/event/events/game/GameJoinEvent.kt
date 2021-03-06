package me.bramhaag.minigameframework.event.events.game

import me.bramhaag.minigameframework.game.AbstractGame
import me.bramhaag.minigameframework.player.AbstractPlayer

class GameJoinEvent(game: AbstractGame, val player: AbstractPlayer) : GameEvent(game)