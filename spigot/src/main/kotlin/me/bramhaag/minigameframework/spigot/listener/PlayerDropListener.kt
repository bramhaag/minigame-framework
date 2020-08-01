package me.bramhaag.minigameframework.spigot.listener

import me.bramhaag.minigameframework.event.events.player.PlayerDropEvent
import me.bramhaag.minigameframework.player.AbstractPlayer
import me.bramhaag.minigameframework.spigot.SpigotGameHandler
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerDropItemEvent

class PlayerDropListener(private val gameHandler: SpigotGameHandler) : Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    fun onItemDrop(e: PlayerDropItemEvent) {
        val spigotPlayer = AbstractPlayer.players[e.player.uniqueId]
        if(spigotPlayer?.game == null) return

        if(gameHandler.eventHandler.callEvent(spigotPlayer, PlayerDropEvent(spigotPlayer))) {
            e.isCancelled = true
        }
    }
}