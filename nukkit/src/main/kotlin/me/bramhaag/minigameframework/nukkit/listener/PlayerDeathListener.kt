package me.bramhaag.minigameframework.nukkit.listener

import cn.nukkit.event.EventHandler
import cn.nukkit.event.Listener
import cn.nukkit.event.player.PlayerDeathEvent
import me.bramhaag.minigameframework.nukkit.NukkitGameHandler
import me.bramhaag.minigameframework.nukkit.NukkitPlayer

typealias MinigameDeathEvent = me.bramhaag.minigameframework.event.events.player.PlayerDeathEvent //TODO naming conflict

class PlayerDeathListener(private val gameHandler: NukkitGameHandler) : Listener {

    @EventHandler
    fun onPlayerDeath(e: PlayerDeathEvent) {
        val nukkitPlayer = NukkitPlayer(e.entity)
        gameHandler.eventHandler.callEvent(nukkitPlayer, MinigameDeathEvent(nukkitPlayer))
    }
}