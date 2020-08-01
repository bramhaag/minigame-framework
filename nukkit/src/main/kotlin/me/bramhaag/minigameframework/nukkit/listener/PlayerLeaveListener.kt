package me.bramhaag.minigameframework.spigot.listener

import cn.nukkit.event.EventHandler
import cn.nukkit.event.EventPriority
import cn.nukkit.event.Listener
import cn.nukkit.event.player.PlayerQuitEvent
import me.bramhaag.minigameframework.event.events.game.GameLeaveEvent
import me.bramhaag.minigameframework.nukkit.NukkitGameHandler
import me.bramhaag.minigameframework.player.AbstractPlayer

class PlayerLeaveListener(private val gameHandler: NukkitGameHandler) : Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    fun onPlayerLeave(e: PlayerQuitEvent) {
        val nukkitPlayer = AbstractPlayer.players[e.player.uniqueId]
        if(nukkitPlayer?.game != null) {
            nukkitPlayer.game?.leave(nukkitPlayer)
            nukkitPlayer.game?.let {
                gameHandler.eventHandler.callEvent(nukkitPlayer, GameLeaveEvent(it, nukkitPlayer))
            }
        }

        AbstractPlayer.players.remove(e.player.uniqueId)
    }
}