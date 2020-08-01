package me.bramhaag.minigameframework.nukkit.listener

import cn.nukkit.Player
import cn.nukkit.event.EventHandler
import cn.nukkit.event.Listener
import cn.nukkit.event.entity.EntityDamageEvent
import me.bramhaag.minigameframework.event.events.player.PlayerDamageEvent
import me.bramhaag.minigameframework.nukkit.NukkitGameHandler
import me.bramhaag.minigameframework.nukkit.NukkitPlayer

class PlayerDamageListener(private val gameHandler: NukkitGameHandler) : Listener {

    @EventHandler
    fun onEntityDamage(e: EntityDamageEvent) {
        val entity = e.entity as? Player ?: return

        val nukkitPlayer = NukkitPlayer(entity)
        gameHandler.eventHandler.callEvent(nukkitPlayer, PlayerDamageEvent(nukkitPlayer))
    }
}