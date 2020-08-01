package me.bramhaag.minigameframework.event

import com.nhaarman.mockitokotlin2.*
import me.bramhaag.minigameframework.annotations.GameEvent
import me.bramhaag.minigameframework.event.events.player.PlayerMoveEvent
import me.bramhaag.minigameframework.feature.AbstractFeature
import me.bramhaag.minigameframework.game.AbstractGame
import me.bramhaag.minigameframework.phase.AbstractPhase
import me.bramhaag.minigameframework.player.AbstractPlayer
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*
import org.mockito.Mockito

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class EventHandlerTest {

    private val testFeature = spy(TestFeature())
    private val invalidTestFeature1 = InvalidTestFeature1()
    private val invalidTestFeature2 = InvalidTestFeature2()
    private val invalidTestFeature3 = InvalidTestFeature3()

    lateinit var eventHandler: EventHandler

    @BeforeAll
    fun setup() {
        val game = mock<AbstractGame>()
        val phase = mock<AbstractPhase>()
        whenever(phase.game).thenReturn(game)

        testFeature.phase = phase
        invalidTestFeature1.phase = phase
        invalidTestFeature2.phase = phase
        invalidTestFeature3.phase = phase
    }

    @BeforeEach
    fun init() {
        eventHandler = EventHandler()
    }

    @Test
    fun registerEvents() {
        assertNull(eventHandler.events[PlayerMoveEvent::class.java])
        eventHandler.registerEvents(testFeature)
        assertEquals(1, eventHandler.events[TestEvent::class.java]?.size)
    }

    @Test
    fun registerEvents_NoArguments_ThrowsException() {
        assertNull(eventHandler.events[TestEvent::class.java])
        assertThrows(IllegalArgumentException::class.java) {
            eventHandler.registerEvents(invalidTestFeature1)
        }

        assertNull(eventHandler.events[TestEvent::class.java])
    }

    @Test
    fun registerEvents_InvalidType_ThrowsException() {
        assertNull(eventHandler.events[TestEvent::class.java])
        assertThrows(IllegalArgumentException::class.java) {
            eventHandler.registerEvents(invalidTestFeature2)
        }

        assertNull(eventHandler.events[TestEvent::class.java])
    }

    @Test
    fun registerEvents_TooManyArguments_ThrowsException() {
        assertNull(eventHandler.events[TestEvent::class.java])
        assertThrows(IllegalArgumentException::class.java) {
            eventHandler.registerEvents(invalidTestFeature3)
        }

        assertNull(eventHandler.events[TestEvent::class.java])
    }

    @Test
    fun unregisterEvents() {
        assertNull(eventHandler.events[TestEvent::class.java])
        eventHandler.registerEvents(testFeature)
        assertEquals(1, eventHandler.events[TestEvent::class.java]?.size)

        eventHandler.unregisterEvents()
        assertNull(eventHandler.events[TestEvent::class.java])
    }

    @Test
    fun callEvent() {
        assertNull(eventHandler.events[TestEvent::class.java])
        eventHandler.registerEvents(testFeature)

        val event = TestEvent()
        val player = mock<AbstractPlayer>()
        val game = testFeature.phase.game
        whenever(player.game).thenReturn(game)

        eventHandler.callEvent(player, event)
        verify(testFeature, times(1)).testEvent(any())
    }
    
    internal class TestEvent : MinigameEvent()

    internal class TestFeature : AbstractFeature() {
        @GameEvent
        fun testEvent(e: TestEvent) {

        }
    }

    internal class InvalidTestFeature1 : AbstractFeature() {
        @GameEvent
        fun testEvent() {
        }
    }

    internal class InvalidTestFeature2 : AbstractFeature() {
        @GameEvent
        fun testEvent(s: String) {
        }
    }

    internal class InvalidTestFeature3 : AbstractFeature() {
        @GameEvent
        fun testEvent(e: TestEvent, s: String) {
        }
    }
}