package me.bramhaag.minigameframework.phase

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import me.bramhaag.minigameframework.game.AbstractGame
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class TimedPhaseTest {

    private lateinit var phase: TimedPhase
    private val game = mock<AbstractGame>()

    @BeforeEach
    fun init() {
        phase = object : TimedPhase(duration = 1) {
            override fun init() {}
        }
        phase.game = game
    }

    @Test
    fun tick() {
        assertEquals(0, phase.currentTick)
        phase.tick()
        assertEquals(1, phase.currentTick)
        phase.tick()
        verify(game, times(1)).endPhase()
    }
}