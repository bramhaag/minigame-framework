package me.bramhaag.minigameframework.chat

import me.bramhaag.minigameframework.player.AbstractPlayer

/**
 * A chat channel represents a collection of listeners who are able to receive messages send into this channel
 */
class ChatChannel(val identifier: String, val prefix: String? = null) {
    val listeners = ArrayList<AbstractPlayer>()

    /**
     * Adds a new listener to the channel <p> Listeners can hear messages, but messages they send will not be sent to
     * this channel unless it is their active channel.
     *
     * @param user the new listener
     */
    fun addListener(user: AbstractPlayer) {
        if (!listeners.contains(user)) {
            this.listeners.add(user)
        }
    }

    /**
     * Removes a listener from the channel
     *
     * @param user the listener to remove
     */
    fun removeListener(user: AbstractPlayer) {
        this.listeners.remove(user)
    }

    /**
     * Sends a plaintext message to the channel's listeners
     *
     * @param sender  sender
     * @param message message
     */
    fun sendMessage(sender: AbstractPlayer, message: String) {
        listeners.forEach { it.sendMessage(message) }
    }




}