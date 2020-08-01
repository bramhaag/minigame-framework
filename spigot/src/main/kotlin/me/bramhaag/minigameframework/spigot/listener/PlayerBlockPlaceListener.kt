package me.bramhaag.minigameframework.spigot.listener

import me.bramhaag.minigameframework.event.events.player.PlayerBlockPlaceEvent
import me.bramhaag.minigameframework.player.AbstractPlayer
import me.bramhaag.minigameframework.spigot.SpigotGameHandler
import me.bramhaag.minigameframework.spigot.SpigotPlayer
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockPlaceEvent


class PlayerBlockPlaceListener(private val gameHandler: SpigotGameHandler) : Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    fun onBlockPlace(e: BlockPlaceEvent) {
        val spigotPlayer = AbstractPlayer.players[e.player.uniqueId]
        if(spigotPlayer?.game == null) return

        if(gameHandler.eventHandler.callEvent(spigotPlayer, PlayerBlockPlaceEvent(spigotPlayer, e.blockPlaced.type.name))) {
            e.isCancelled = true
        }
    }
}