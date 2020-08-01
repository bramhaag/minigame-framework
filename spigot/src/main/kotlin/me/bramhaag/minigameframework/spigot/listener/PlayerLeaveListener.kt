package me.bramhaag.minigameframework.spigot.listener

import me.bramhaag.minigameframework.event.events.game.GameLeaveEvent
import me.bramhaag.minigameframework.player.AbstractPlayer
import me.bramhaag.minigameframework.spigot.SpigotGameHandler
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerQuitEvent


class PlayerLeaveListener(private val gameHandler: SpigotGameHandler) : Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    fun onPlayerLeave(e: PlayerQuitEvent) {
        val spigotPlayer = AbstractPlayer.players[e.player.uniqueId]
        if(spigotPlayer?.game != null) {
            spigotPlayer.game?.leave(spigotPlayer)
            spigotPlayer.game?.let {
                gameHandler.eventHandler.callEvent(spigotPlayer, GameLeaveEvent(it, spigotPlayer))
            }
        }

        AbstractPlayer.players.remove(e.player.uniqueId)
    }
}