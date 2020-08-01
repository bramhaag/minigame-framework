package me.bramhaag.minigameframework.player

import me.bramhaag.minigameframework.chat.ChatChannel
import me.bramhaag.minigameframework.game.AbstractGame
import me.bramhaag.minigameframework.util.Location
import java.util.*


abstract class AbstractPlayer {

    var activeChannel: ChatChannel? = null
    var game: AbstractGame? = null

    abstract fun getUUID() : UUID

    abstract fun sendMessage(message: String)

    abstract fun getDisplayName() : String
    abstract fun getName() : String

    abstract fun getGameMode() : GameMode
    abstract fun setGameMode(gameMode: GameMode)

    abstract fun clearInventory()

    abstract fun getHealth() : Double
    abstract fun setHealth(health: Double)
    abstract fun getMaxHealth() : Double

    abstract fun getFoodLevel(): Int
    abstract fun setFoodLevel(foodLevel: Int)

    abstract fun getLocation(): Location
    abstract fun setLocation(location: Location)

    abstract fun sendTitle(message: String, fadeIn: Int, stay: Int, fadeOut: Int)
    abstract fun clearTitle()

    abstract fun setItem(slot: Int, name: String)

    abstract fun saveInventory()
    abstract fun restoreInventory()

    abstract fun clearPotions()

    abstract fun canFly(enable: Boolean)

    companion object {
        val players = HashMap<UUID, AbstractPlayer>()
    }
}