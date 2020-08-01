package me.bramhaag.minigameframework.spigot.listener

import me.bramhaag.minigameframework.event.events.player.PlayerBlockBreakEvent
import me.bramhaag.minigameframework.player.AbstractPlayer
import me.bramhaag.minigameframework.spigot.SpigotGameHandler
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent

class PlayerBlockBreakListener(private val gameHandler: SpigotGameHandler) : Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    fun onBlockBreak(e: BlockBreakEvent) {
        val spigotPlayer = AbstractPlayer.players[e.player.uniqueId]
        if(spigotPlayer?.game == null) return

        val event = PlayerBlockBreakEvent(spigotPlayer, e.block.type.name)
        if(gameHandler.eventHandler.callEvent(spigotPlayer, PlayerBlockBreakEvent(spigotPlayer, e.block.type.name))) {
            e.isCancelled = true
            return
        }

        e.isDropItems = event.dropItems
    }
}