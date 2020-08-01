package me.bramhaag.minigameframework.util

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import me.bramhaag.minigameframework.player.AbstractPlayer
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class ChatUtilTest {

    @Test
    fun formatChannelMessage() {
        val sender = mock<AbstractPlayer> {
            on { getDisplayName() } doReturn "TestPlayer"
        }

        val message = ChatUtil.formatChannelMessage(sender, "My-Prefix", "My Message")
        assertEquals("My-Prefix TestPlayer: My Message", message)
    }
}