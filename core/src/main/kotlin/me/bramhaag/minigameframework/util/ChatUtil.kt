package me.bramhaag.minigameframework.util

import me.bramhaag.minigameframework.player.AbstractPlayer


object ChatUtil {

    fun formatChannelMessage(sender: AbstractPlayer, prefix: String?, message: String) : String {
        return "$prefix ${sender.getDisplayName()}: $message"
    }
}