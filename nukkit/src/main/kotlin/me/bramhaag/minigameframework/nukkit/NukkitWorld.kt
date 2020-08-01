package me.bramhaag.minigameframework.nukkit

import cn.nukkit.Server
import cn.nukkit.level.Level
import me.bramhaag.minigameframework.util.Location
import me.bramhaag.minigameframework.world.AbstractWorld
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.StandardCopyOption


class NukkitWorld(worldName: String) : AbstractWorld(worldName) {

    lateinit var nukkitWorld: Level

    private val originalWorldDir = Paths.get(Server.getInstance().dataPath, "worlds", originalPrefix + worldName.split('-').first())
    private val worldDir = Paths.get(Server.getInstance().dataPath, "worlds", worldName)

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

        Server.getInstance().loadLevel(worldName)
        nukkitWorld = Server.getInstance().getLevelByName(worldName)
        nukkitWorld.autoSave = false
    }

    override fun unload(save: Boolean) : Boolean {
        if(!Server.getInstance().unloadLevel(nukkitWorld, save)) return false

        return deleteWorld()
    }

    override fun isLoaded() : Boolean {
        return Server.getInstance().levels.values.firstOrNull { it.name == worldName } != null
    }

    override fun deleteWorld(): Boolean {
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
        val spawn = nukkitWorld.spawnLocation
        return Location(this, spawn.x, spawn.y, spawn.z)
    }

    private fun copy(source: Path, destination: Path) {
        Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING)
    }
}