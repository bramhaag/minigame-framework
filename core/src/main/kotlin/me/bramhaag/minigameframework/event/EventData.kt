package me.bramhaag.minigameframework.event

import me.bramhaag.minigameframework.feature.AbstractFeature
import me.bramhaag.minigameframework.game.AbstractGame
import java.lang.reflect.Method


class EventData(val listener: AbstractFeature, val method: Method, val game: AbstractGame)