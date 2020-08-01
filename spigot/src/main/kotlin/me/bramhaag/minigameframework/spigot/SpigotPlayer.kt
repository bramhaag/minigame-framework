package me.bramhaag.minigameframework.spigot

import me.bramhaag.minigameframework.player.AbstractPlayer
import me.bramhaag.minigameframework.player.GameMode
import me.bramhaag.minigameframework.util.Location
import org.bukkit.Material
import org.bukkit.attribute.Attribute
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import java.util.*

typealias BGameMode = org.bukkit.GameMode
typealias BLocation = org.bukkit.Location

class SpigotPlayer(private val player: Player) : AbstractPlayer() {

    private var playerInventory: Array<ItemStack>? = null
    private var playerArmor: Array<ItemStack>? = null

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
        return GameMode.valueOf(player.gameMode.name)
    }

    override fun setGameMode(gameMode: GameMode) {
        player.gameMode = BGameMode.valueOf(gameMode.name)
    }

    override fun clearInventory() {
        player.inventory.clear()
    }

    override fun getHealth(): Double {
        return player.health
    }

    override fun setHealth(health: Double) {
        player.health = health
    }

    override fun getMaxHealth(): Double {
        return Attribute.GENERIC_MAX_HEALTH.ordinal * player.healthScale
    }

    override fun getFoodLevel(): Int {
        return player.foodLevel
    }

    override fun setFoodLevel(foodLevel: Int) {
        player.foodLevel = foodLevel
    }

    override fun getLocation(): Location {
        val worldName = player.location.world?.name ?: throw IllegalArgumentException("World is not set")
        return Location(
                SpigotWorld(worldName),
                player.location.x,
                player.location.y,
                player.location.z,
                player.location.yaw,
                player.location.pitch
        )
    }

    override fun setLocation(location: Location) {
        player.teleport(BLocation(
                (location.world as SpigotWorld).spigotWorld,
                location.x,
                location.y,
                location.z,
                location.yaw,
                location.pitch
        ))
    }

    override fun sendTitle(message: String, fadeIn: Int, stay: Int, fadeOut: Int) {
        player.sendTitle(message, "", fadeIn, stay, fadeOut)
    }

    override fun clearTitle() {
        player.resetTitle()
    }

    override fun setItem(slot: Int, name: String) {
        val material = Material.matchMaterial(name) ?: throw IllegalArgumentException("Material not found")
        val itemStack = ItemStack(material)
        itemStack.amount = 1

        player.inventory.setItem(slot, itemStack)
    }

    override fun saveInventory() {
        playerInventory = player.inventory.contents.clone()
        playerArmor = player.inventory.armorContents.clone()
    }

    override fun restoreInventory() {
        playerInventory?.let {
            player.inventory.contents = it
        }

        playerArmor?.let {
            player.inventory.setArmorContents(it)
        }
    }

    override fun clearPotions() {
        player.activePotionEffects.forEach {
            player.removePotionEffect(it.type)
        }
    }
    override fun canFly(enable: Boolean) {
        player.isFlying = false
    }

}