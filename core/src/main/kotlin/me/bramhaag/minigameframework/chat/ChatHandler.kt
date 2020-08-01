package me.bramhaag.minigameframework.chat

import me.bramhaag.minigameframework.handler.IHandler
import me.bramhaag.minigameframework.player.AbstractPlayer
import java.lang.IllegalArgumentException

/**
 * Handles everything related to chat
 */
class ChatHandler : IHandler {

    private var activeChannels = HashMap<String, ChatChannel>()

    /**
     * Create a new channel
     *
     * @param identifier the channel's identifier.
     * @param prefix the channel's prefix that is send in all messages.
     */
    fun createChannel(identifier: String, prefix: String? = null) : ChatChannel {
        if (activeChannels.containsKey(identifier)) {
            throw IllegalArgumentException("A channel with this identifier already exists")
        }

        val chatChannel = ChatChannel(identifier, prefix)
        activeChannels[identifier] = chatChannel

        return chatChannel
    }

    /**
     * Remove a channel
     *
     * @param identifier the channel's identifier
     */
    fun removeChannel(identifier: String) {
        getChannel(identifier)?.let {
            it.listeners.forEach { listener ->
                listener.activeChannel = null
            }
        }

        activeChannels.remove(identifier)
    }

    /**
     * Get a channel by it's identifier
     *
     * @param identifier the channel's identifier
     */
    fun getChannel(identifier: String) : ChatChannel? {
        return activeChannels[identifier]
    }

    companion object {
        fun processMessage(receiver: AbstractPlayer, message: String) : Boolean {
            receiver.activeChannel?.let {
                it.sendMessage(receiver, message)
                return true
            }

            return false
        }
    }
}