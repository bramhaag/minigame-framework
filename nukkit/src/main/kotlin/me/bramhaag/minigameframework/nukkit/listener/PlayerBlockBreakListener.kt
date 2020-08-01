package me.bramhaag.minigameframework.nukkit.listener

import cn.nukkit.event.EventHandler
import cn.nukkit.event.Listener
import cn.nukkit.event.block.BlockBreakEvent
import me.bramhaag.minigameframework.event.events.player.PlayerBlockBreakEvent
import me.bramhaag.minigameframework.nukkit.NukkitGameHandler
import me.bramhaag.minigameframework.nukkit.NukkitPlayer

class PlayerBlockBreakListener(private val gameHandler: NukkitGameHandler) : Listener {

    @EventHandler
    fun onBlockBreak(e: BlockBreakEvent) {
        val nukkitPlayer = NukkitPlayer(e.player)
        gameHandler.eventHandler.callEvent(nukkitPlayer, PlayerBlockBreakEvent(nukkitPlayer, e.block.name))
    }
}