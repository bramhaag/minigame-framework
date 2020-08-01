package me.bramhaag.minigameframework.util

import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.Test

internal class TickTest {
    @Test
    fun toTicks_Seconds() {
        assertEquals(20, Tick.SECONDS.toTicks(1))
    }

    @Test
    fun fromTicks_Seconds() {
        assertEquals(1, Tick.SECONDS.fromTicks(20))
    }

    @Test
    fun toTicks_Minutes() {
        assertEquals(1200, Tick.MINUTES.toTicks(1))
    }

    @Test
    fun fromTicks_Minutes() {
        assertEquals(1, Tick.MINUTES.fromTicks(1200))
    }

    @Test
    fun toTicks_HOURS() {
        assertEquals(72000, Tick.HOURS.toTicks(1))
    }

    @Test
    fun fromTicks_HOURS() {
        assertEquals(1, Tick.HOURS.fromTicks(72000))
    }

}