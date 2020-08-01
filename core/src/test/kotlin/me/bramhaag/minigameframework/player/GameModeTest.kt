package me.bramhaag.minigameframework.player

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class GameModeTest {

    @Test
    fun getByValue() {
        assertEquals(GameMode.SURVIVAL, GameMode.getByValue(0))
        assertEquals(GameMode.CREATIVE, GameMode.getByValue(1))
        assertEquals(GameMode.ADVENTURE, GameMode.getByValue(2))
        assertEquals(GameMode.SPECTATOR, GameMode.getByValue(3))
    }
}