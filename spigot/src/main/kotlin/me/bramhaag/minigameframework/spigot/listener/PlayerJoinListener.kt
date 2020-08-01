package me.bramhaag.minigameframework.spigot.listener

import me.bramhaag.minigameframework.player.AbstractPlayer
import me.bramhaag.minigameframework.spigot.SpigotPlayer
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

class PlayerJoinListener : Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    fun onPlayerLeave(e: PlayerJoinEvent) {
        AbstractPlayer.players[e.player.uniqueId] = SpigotPlayer(e.player)
    }
}