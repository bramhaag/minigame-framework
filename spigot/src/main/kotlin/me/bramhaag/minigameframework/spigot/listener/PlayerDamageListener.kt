package me.bramhaag.minigameframework.spigot.listener

import me.bramhaag.minigameframework.event.events.player.PlayerDamageEvent
import me.bramhaag.minigameframework.player.AbstractPlayer
import me.bramhaag.minigameframework.spigot.SpigotGameHandler
import me.bramhaag.minigameframework.spigot.SpigotPlayer
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageEvent


class PlayerDamageListener(private val gameHandler: SpigotGameHandler) : Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    fun onEntityDamage(e: EntityDamageEvent) {
        val entity = e.entity as? Player ?: return

        val spigotPlayer = AbstractPlayer.players[entity.uniqueId]
        if(spigotPlayer?.game == null) return

        if(gameHandler.eventHandler.callEvent(spigotPlayer, PlayerDamageEvent(spigotPlayer))) {
            e.isCancelled = true
        }
    }
}