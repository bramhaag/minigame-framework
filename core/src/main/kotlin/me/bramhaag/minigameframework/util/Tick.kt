package me.bramhaag.minigameframework.util

import me.bramhaag.minigameframework.game.GameConstants


enum class Tick(private val multiplier: Int) {
    SECONDS(1),
    MINUTES(60),
    HOURS(3600);

    fun toTicks(time: Number) : Long {
        return time.toLong() * multiplier * GameConstants.TPS
    }

    fun fromTicks(time: Number) : Long {
        return time.toLong() / (multiplier * GameConstants.TPS)
    }
}