package me.bramhaag.minigameframework.spigot.listener

import me.bramhaag.minigameframework.player.AbstractPlayer
import me.bramhaag.minigameframework.spigot.SpigotGameHandler
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent

typealias MinigameDeathEvent = me.bramhaag.minigameframework.event.events.player.PlayerDeathEvent

class PlayerDeathListener(private val gameHandler: SpigotGameHandler) : Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    fun onPlayerDeath(e: PlayerDeathEvent) {
        val spigotPlayer = AbstractPlayer.players[e.entity.uniqueId]
        if(spigotPlayer?.game == null) return

        gameHandler.eventHandler.callEvent(spigotPlayer, MinigameDeathEvent(spigotPlayer))
    }
}