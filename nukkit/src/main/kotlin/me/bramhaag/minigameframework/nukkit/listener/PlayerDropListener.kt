package me.bramhaag.minigameframework.nukkit.listener

import cn.nukkit.event.EventHandler
import cn.nukkit.event.EventPriority
import cn.nukkit.event.Listener
import cn.nukkit.event.player.PlayerDropItemEvent
import me.bramhaag.minigameframework.event.events.player.PlayerDropEvent
import me.bramhaag.minigameframework.nukkit.NukkitGameHandler
import me.bramhaag.minigameframework.player.AbstractPlayer

class PlayerDropListener(private val gameHandler: NukkitGameHandler) : Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    fun onItemDrop(e: PlayerDropItemEvent) {
        val nukkitPlayer = AbstractPlayer.players[e.player.uniqueId]
        if(nukkitPlayer?.game == null) return

        if(gameHandler.eventHandler.callEvent(nukkitPlayer, PlayerDropEvent(nukkitPlayer))) {
            e.isCancelled = true
        }
    }
}