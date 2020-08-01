package me.bramhaag.minigameframework.spigot.listener

import me.bramhaag.minigameframework.player.AbstractPlayer
import me.bramhaag.minigameframework.spigot.SpigotGameHandler
import me.bramhaag.minigameframework.spigot.util.fromBukkitLocation
import me.bramhaag.minigameframework.util.Location
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerMoveEvent

typealias MinigameMoveEvent = me.bramhaag.minigameframework.event.events.player.PlayerMoveEvent

class PlayerMoveListener(private val gameHandler: SpigotGameHandler) : Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    fun onPlayerMove(e: PlayerMoveEvent) {
        val spigotPlayer = AbstractPlayer.players[e.player.uniqueId]
        if(spigotPlayer?.game == null) return

        if(gameHandler.eventHandler.callEvent(spigotPlayer, MinigameMoveEvent(spigotPlayer,
                Location.fromBukkitLocation(e.from),
                Location.fromBukkitLocation(e.to!!))
        )) {
            e.isCancelled = true
       }
    }
}