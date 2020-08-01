package me.bramhaag.minigameframework.tick

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

internal class AbstractTickHandlerTest {

    private lateinit var tickHandler: AbstractTickHandler
    private lateinit var tickable: ITickable

    @BeforeEach
    fun init() {
        tickHandler = object : AbstractTickHandler() {
            override fun start() {}
        }

        tickable = mock()
    }

    @Test
    fun tick() {
        tickHandler.registerTickable(tickable)
        tickHandler.tick()
        verify(tickable, times(1)).enable()
    }

    @Test
    fun registerTickable() {
        assertTrue(tickHandler.tickables.isEmpty())
        tickHandler.registerTickable(tickable)
        assertEquals(1, tickHandler.tickables.size)
        assertTrue(tickHandler.tickables.contains(tickable))
        verify(tickable, times(1)).enable()
    }

    @Test
    fun endTickable() {
        tickHandler.registerTickable(tickable)
        tickHandler.endTickable(tickable)
        tickHandler.tick()
        verify(tickable, times(1)).disable()
        assertFalse(tickHandler.tickables.contains(tickable))
    }

    @Test
    fun endTickable_NotFound() {
        assertFalse(tickHandler.tickables.contains(tickable))
        tickHandler.endTickable(tickable)
        tickHandler.tick()
        assertFalse(tickHandler.tickables.contains(tickable))
    }
}