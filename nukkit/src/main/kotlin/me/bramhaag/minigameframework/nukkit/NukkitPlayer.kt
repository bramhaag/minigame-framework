package me.bramhaag.minigameframework.nukkit

import cn.nukkit.Player
import cn.nukkit.block.Block
import cn.nukkit.block.BlockID
import cn.nukkit.item.Item
import cn.nukkit.item.ItemID
import me.bramhaag.minigameframework.nukkit.util.materialToId
import me.bramhaag.minigameframework.player.AbstractPlayer
import me.bramhaag.minigameframework.player.GameMode
import me.bramhaag.minigameframework.util.Location
import java.lang.IllegalArgumentException
import java.util.*
import kotlin.collections.HashMap

typealias NLocation = cn.nukkit.level.Location


class NukkitPlayer(private val player: Player) : AbstractPlayer() {
    var playerInventory: HashMap<Int, Item>? = null
    var playerArmor: Array<Item>? = null

    override fun getUUID(): UUID {
        return player.uniqueId
    }

    override fun sendMessage(message: String) {
        player.sendMessage(message)
    }

    override fun getDisplayName(): String {
        return player.displayName
    }

    override fun getName(): String {
        return player.name
    }

    override fun getGameMode(): GameMode {
        return GameMode.getByValue(player.gamemode) ?: throw IllegalArgumentException("GameMode not found")
    }

    override fun setGameMode(gameMode: GameMode) {
        player.gamemode = gameMode.value
    }

    override fun clearInventory() {
        player.inventory.clearAll()
    }

    override fun getHealth(): Double {
        return player.health.toDouble()
    }

    override fun setHealth(health: Double) {
        player.health = health.toFloat()
    }

    override fun getMaxHealth(): Double {
        return player.maxHealth.toDouble()
    }

    override fun getFoodLevel(): Int {
        return player.foodData.level
    }

    override fun setFoodLevel(foodLevel: Int) {
        player.foodData.level = foodLevel
    }

    override fun getLocation(): Location {
        return Location(
                NukkitWorld(player.location.level.name),
                player.location.x,
                player.location.y,
                player.location.z,
                player.location.yaw.toFloat(),
                player.location.pitch.toFloat()
        )
    }

    override fun setLocation(location: Location) {
        //TODO world
        player.teleport(NLocation(
                location.x,
                location.y,
                location.z,
                location.yaw.toDouble(),
                location.pitch.toDouble(),
                player.server.getLevelByName(location.world?.worldName)
        ))
    }

    override fun sendTitle(message: String, fadeIn: Int, stay: Int, fadeOut: Int) {
        player.sendTitle(message, "", fadeIn, stay, fadeOut)
    }

    override fun clearTitle() {
        player.clearTitle()
    }

    override fun setItem(slot: Int, name: String) {
        val id = materialToId(name) ?: throw IllegalArgumentException("Material not found")
        val item = Item(id)
        player.inventory.setItem(slot, item)
    }


    override fun saveInventory() {
        playerInventory = HashMap(player.inventory.contents)
        playerArmor = player.inventory.armorContents.clone()
    }

    override fun restoreInventory() {
        player.inventory.contents = playerInventory
        player.inventory.armorContents = playerArmor
    }

    override fun clearPotions() {
    }

    override fun canFly(enable: Boolean) {
        player.allowFlight = false
    }
}