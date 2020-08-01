package me.bramhaag.minigameframework.nukkit.listener

import cn.nukkit.event.EventHandler
import cn.nukkit.event.Listener
import cn.nukkit.event.block.BlockPlaceEvent
import me.bramhaag.minigameframework.event.events.player.PlayerBlockPlaceEvent
import me.bramhaag.minigameframework.nukkit.NukkitPlayer
import me.bramhaag.minigameframework.nukkit.NukkitGameHandler

class PlayerBlockPlaceListener(private val gameHandler: NukkitGameHandler) : Listener {

    @EventHandler
    fun onBlockPlace(e: BlockPlaceEvent) {
        val nukkitPlayer = NukkitPlayer(e.player)
        gameHandler.eventHandler.callEvent(nukkitPlayer, PlayerBlockPlaceEvent(nukkitPlayer, e.block.name))
    }
}