package me.bramhaag.minigameframework.spigot

import me.bramhaag.minigameframework.util.Location
import me.bramhaag.minigameframework.world.AbstractWorld
import org.bukkit.Bukkit
import org.bukkit.World
import org.bukkit.WorldCreator
import java.io.IOException
import java.lang.IllegalArgumentException
import java.lang.IllegalStateException
import java.nio.file.*


class SpigotWorld(worldName: String) : AbstractWorld(worldName) {

    lateinit var spigotWorld: World

    private val originalWorldDir = Paths.get(Bukkit.getServer().worldContainer.absolutePath, originalPrefix + worldName.split('-').first())
    private val worldDir = Paths.get(Bukkit.getServer().worldContainer.absolutePath, worldName)

    override fun load() {
        if(!Files.exists(worldDir)) {
            Files.walk(originalWorldDir)
                    .forEach {
                        Files.copy(
                                it,
                                worldDir.resolve(originalWorldDir.relativize(it)),
                                StandardCopyOption.REPLACE_EXISTING)
                    }
        }

        spigotWorld = Bukkit.createWorld(WorldCreator(worldName)) ?: throw IllegalStateException("World not loaded")
        spigotWorld.isAutoSave = false
    }

    override fun unload(save: Boolean) : Boolean {
        if(!Bukkit.getServer().unloadWorld(spigotWorld, save)) return false

        return deleteWorld()
    }

    override fun isLoaded() : Boolean {
        return Bukkit.getWorlds().firstOrNull { it.name == worldName } != null
    }

    override fun deleteWorld() : Boolean {
        if(!Files.exists(worldDir)) return false

        try {
            Files.walk(worldDir)
                    .sorted(Comparator.reverseOrder())
                    .forEach { Files.deleteIfExists(it) }
        } catch (e: IOException) {
            e.printStackTrace()
            return false
        }

        return true
    }

    override fun getSpawn(): Location {
        val spawn = spigotWorld.spawnLocation
        return Location(this, spawn.x, spawn.y, spawn.z)
    }

    private fun copy(source: Path, destination: Path) {
        Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING)
    }
}