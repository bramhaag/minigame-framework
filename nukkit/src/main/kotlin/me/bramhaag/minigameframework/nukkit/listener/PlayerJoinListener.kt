package me.bramhaag.minigameframework.nukkit.listener

import cn.nukkit.event.EventHandler
import cn.nukkit.event.EventPriority
import cn.nukkit.event.Listener
import cn.nukkit.event.player.PlayerJoinEvent
import me.bramhaag.minigameframework.nukkit.NukkitPlayer
import me.bramhaag.minigameframework.player.AbstractPlayer

class PlayerJoinListener : Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    fun onPlayerLeave(e: PlayerJoinEvent) {
        AbstractPlayer.players[e.player.uniqueId] = NukkitPlayer(e.player)
    }
}