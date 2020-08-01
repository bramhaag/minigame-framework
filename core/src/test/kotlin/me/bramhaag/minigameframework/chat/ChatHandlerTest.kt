package me.bramhaag.minigameframework.chat

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException

internal class ChatHandlerTest {

    val identifier = "My-Identifier"
    lateinit var chatHandler: ChatHandler

    @BeforeEach
    fun init() {
        chatHandler = ChatHandler()
    }

    @Test
    fun createChannel() {
        val channel = chatHandler.createChannel(identifier)

        assertEquals(identifier, channel.identifier)
        assertEquals(channel, chatHandler.getChannel(identifier))
    }

    @Test
    fun createChannel_CreateDuplicate_ThrowsException() {
        val channel = chatHandler.createChannel(identifier)

        assertEquals(identifier, channel.identifier)
        assertEquals(channel, chatHandler.getChannel(identifier))

        assertThrows(IllegalArgumentException::class.java) { chatHandler.createChannel(identifier) }
    }

    @Test
    fun removeChannel() {
        val channel = chatHandler.createChannel(identifier)

        assertEquals(identifier, channel.identifier)
        assertEquals(channel, chatHandler.getChannel(identifier))

        chatHandler.removeChannel(identifier)
        assertNull(chatHandler.getChannel(identifier))
    }

    @Test
    fun getChannel() {
        chatHandler.createChannel(identifier)

        assertNotNull(chatHandler.getChannel(identifier))
        assertNull(chatHandler.getChannel("Invalid"))
    }
}