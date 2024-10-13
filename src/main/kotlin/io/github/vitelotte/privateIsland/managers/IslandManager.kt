package io.github.vitelotte.privateIsland.managers

import io.github.vitelotte.privateIsland.PrivateIsland
import io.github.vitelotte.privateIsland.generator.chunk.VoidGenerator
import org.bukkit.Bukkit
import org.bukkit.NamespacedKey
import org.bukkit.World
import org.bukkit.WorldCreator

class IslandManager(){

    private val plugin: PrivateIsland = PrivateIsland.instance
    private val config = plugin.config

    private val islandsWorldName: String = config.getString("islands.world") ?: "islands"
    val islandsDistance: Int = config.getInt("islands.distance")

    val centerX: Int = config.getInt("islands.center.x")
    val centerY: Int = config.getInt("islands.center.y")
    val centerZ: Int = config.getInt("islands.center.z")
    val islandPools: List<String> = config.getStringList("islands.pools")

    val islandWorld: World = loadOrCreateWorld()

    private val islandsCountKey: NamespacedKey = NamespacedKey(plugin, "IslandCount")

    // 월드 로드 또는 생성
    private fun loadOrCreateWorld(): World {
        return Bukkit.getWorld(islandsWorldName) ?:
            WorldCreator(islandsWorldName).generator(VoidGenerator()).createWorld()!!
    }



}