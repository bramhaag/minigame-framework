package me.bramhaag.minigameframework.nukkit.listener

import cn.nukkit.event.EventHandler
import cn.nukkit.event.Listener
import cn.nukkit.event.player.PlayerMoveEvent
import me.bramhaag.minigameframework.nukkit.NukkitPlayer
import me.bramhaag.minigameframework.nukkit.util.fromNukkitLocation
import me.bramhaag.minigameframework.nukkit.NukkitGameHandler
import me.bramhaag.minigameframework.util.Location

typealias MinigameMoveEvent = me.bramhaag.minigameframework.event.events.player.PlayerMoveEvent

class PlayerMoveListener(private val gameHandler: NukkitGameHandler) : Listener {

    @EventHandler
    fun onPlayerMove(e: PlayerMoveEvent) {
        val nukkitPlayer = NukkitPlayer(e.player)
        gameHandler.eventHandler.callEvent(nukkitPlayer, MinigameMoveEvent(nukkitPlayer,
                Location.fromNukkitLocation(e.from),
                Location.fromNukkitLocation(e.to))
        )
    }
}