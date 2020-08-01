package me.bramhaag.minigameframework.condition

import com.nhaarman.mockitokotlin2.mock
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.validateMockitoUsage


internal class AbstractVictoryConditionTest {
    lateinit var victoryCondition: AbstractVictoryCondition

    @BeforeEach
    fun init() {
        victoryCondition = object : AbstractVictoryCondition() {}
    }

    @Test
    fun completed_NoWinner_False() {
        assertFalse(victoryCondition.completed())
    }

    @Test
    fun completed_HasWinner_True() {
        victoryCondition.winner = mock {}
        assertTrue(victoryCondition.completed())
    }
}