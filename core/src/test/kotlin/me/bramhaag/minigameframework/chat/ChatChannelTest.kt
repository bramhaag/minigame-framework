package me.bramhaag.minigameframework.chat

import com.nhaarman.mockitokotlin2.atLeastOnce
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import me.bramhaag.minigameframework.player.AbstractPlayer
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach

import org.junit.jupiter.api.Test

internal class ChatChannelTest {

    lateinit var chatChannel: ChatChannel

    @BeforeEach
    fun init() {
        chatChannel = ChatChannel("my-id", "my-prefix")
    }

    @Test
    fun addListener() {
        assertTrue(chatChannel.listeners.isEmpty())

        val user = mock<AbstractPlayer> {}
        chatChannel.addListener(user)

        assertEquals(1, chatChannel.listeners.size)
        assertTrue(chatChannel.listeners.contains(user))
    }

    @Test
    fun addListener_AlreadyExists() {
        assertTrue(chatChannel.listeners.isEmpty())

        val user = mock<AbstractPlayer> {}
        chatChannel.addListener(user)

        assertEquals(1, chatChannel.listeners.size)
        assertTrue(chatChannel.listeners.contains(user))

        chatChannel.addListener(user)
        assertEquals(1, chatChannel.listeners.size)
        assertTrue(chatChannel.listeners.contains(user))
    }

    @Test
    fun removeListener() {
        assertTrue(chatChannel.listeners.isEmpty())

        val user = mock<AbstractPlayer> {}
        chatChannel.addListener(user)

        assertEquals(1, chatChannel.listeners.size)
        assertTrue(chatChannel.listeners.contains(user))

        chatChannel.removeListener(user)
        assertEquals(0, chatChannel.listeners.size)
    }

    @Test
    fun removeListener_NotFound() {
        assertEquals(0, chatChannel.listeners.size)

        val user = mock<AbstractPlayer> {}

        chatChannel.removeListener(user)
        assertEquals(0, chatChannel.listeners.size)

    }

    @Test
    fun sendMessage() {
        val sender = mock<AbstractPlayer> {}
        val user = mock<AbstractPlayer> {}
        chatChannel.addListener(user)

        chatChannel.sendMessage(sender, "Test message")
        verify(user, times(1)).sendMessage("Test message")
    }
}