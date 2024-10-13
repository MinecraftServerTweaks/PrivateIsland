package io.github.vitelotte.privateIsland.utils

import io.github.vitelotte.privateIsland.PrivateIsland
import org.bukkit.*
import org.bukkit.block.structure.Mirror
import org.bukkit.block.structure.StructureRotation
import org.bukkit.persistence.PersistentDataType
import org.bukkit.structure.Structure
import org.bukkit.structure.StructureManager
import java.io.File
import java.util.*
import kotlin.math.abs
import kotlin.math.max

private val plugin: PrivateIsland = PrivateIsland.instance
private val config = plugin.config

private val islandsWorldName: String = config.getString("islands.world") ?: "islands"
val islandsDistance: Int = config.getInt("islands.distance")

val centerX: Int = config.getInt("islands.center.x")
val centerY: Int = config.getInt("islands.center.y")
val centerZ: Int = config.getInt("islands.center.z")
val islandPools: List<String> = config.getStringList("islands.pools")

private val islandsCountKey: NamespacedKey = NamespacedKey(plugin, "IslandCount")
private val islandsIndicesKey: NamespacedKey = NamespacedKey(plugin, "IslandIndices")


fun World.getIslandCount(): Int {
    return this.persistentDataContainer.getOrDefault(islandsCountKey, PersistentDataType.INTEGER, 0)
}

fun World.incrementIslandCount() {
    this.persistentDataContainer.set(islandsCountKey, PersistentDataType.INTEGER, this.getIslandCount() + 1)
}

fun World.decrementIslandCount() {
    val count = this.persistentDataContainer.getOrDefault(islandsCountKey, PersistentDataType.INTEGER, 0)
    if (count > 0) {
        this.persistentDataContainer.set(islandsCountKey, PersistentDataType.INTEGER, count - 1)
    }
}

fun World.getOccupiedIslands(): MutableList<Int> {
    return this.persistentDataContainer.getOrDefault(islandsCountKey, PersistentDataType.INTEGER_ARRAY, intArrayOf()).toMutableList()
}

fun World.isOccupiedIsland(): Boolean {
    return this.getOccupiedIslands().contains(this.getIslandCount())
}

fun World.getAvailableIslandIndex(): Int {
    val occupiedIslands = this.getOccupiedIslands()
    return (0..this.getIslandCount()).firstOrNull {!occupiedIslands.contains(it) } ?: getIslandCount()
}

fun World.setOccupiedIslands(islandIndices: IntArray) {
    this.persistentDataContainer.set(islandsIndicesKey, PersistentDataType.INTEGER_ARRAY, islandIndices)
}

fun World.createNewIsland(): Location? {
    // 섬 위치 계산
    val islandIndex: Int = this.getAvailableIslandIndex()
    val islandLocation: Location = this.calculateIslandLocation(islandIndex)

    // 섬 구조물 로드
    val success = this.pasteIsland(islandLocation)

    if (!success) {
        plugin.logger.severe("섬 구조물을 로드하는 데 실패했습니다.")
        return null
    }
    incrementIslandCount()

    setOccupiedIslands(getOccupiedIslands().plus(islandIndex).toIntArray())

    return islandLocation
}

fun World.deleteIsland(location: Location): Boolean{

    // clear area
    val radius = islandsDistance / 2
    val minX = location.blockX - radius
    val maxX = location.blockX + radius
    val minY = 0
    val maxY = this.maxHeight
    val minZ = location.blockZ - radius
    val maxZ = location.blockZ + radius

    for (x in minX..maxX) {
        for (y in minY..maxY) {
            for (z in minZ..maxZ) {
                this.getBlockAt(x, y, z).type = Material.AIR
            }
        }
    }

    val x: Int = location.blockX
    val z: Int = location.blockZ

    val n = max(abs(x), abs(z))
    val layerStartIndex = if (n == 0) 0 else (2 * n - 1) * (2 * n - 1)

    val offset = when {
        x == n && z != -n -> (n - z)
        z == -n -> (2 * n) + (n - x)
        x == -n -> (4 * n) + (n + z)
        z == n -> (6 * n) + (n + x)
        else -> 0
    }

    val islandIndex: Int = layerStartIndex + offset

    decrementIslandCount()
    setOccupiedIslands(getOccupiedIslands().filterNot { it == islandIndex }.toIntArray())
    return true
}

fun World.calculateIslandLocation(islandIndex: Int): Location {
    var x = 0
    var z = 0
    var dx = 0
    var dz = -1
    var t = islandIndex
    val maxI = 1000

    for (i in 0 until maxI) {
        if ((-max(abs(x), abs(z)) <= x) && (x <= max(abs(x), abs(z)))) {
            if ((-max(abs(x), abs(z)) <= z) && (z <= max(abs(x), abs(z)))) {
                if (t == 0) break
                t--
            }
        }

        if ((x == z) || (x < 0 && x == -z) || (x > 0 && x == 1 - z)) {
            val temp = dx
            dx = -dz
            dz = temp
        }

        x += dx
        z += dz
    }

    val finalX = centerX + x * islandsDistance
    val finalZ = centerZ + z * islandsDistance

    return Location(this, finalX.toDouble(), centerY.toDouble(), finalZ.toDouble())
}

fun World.pasteIsland(location: Location): Boolean {
    val poolSize = islandPools.size
    if (poolSize == 0) return false

    val structureName: String = islandPools.random()

    val structureManager: StructureManager = Bukkit.getStructureManager()
    val structureFile: File = File(plugin.dataFolder, "$structureName.nbt")

    if (!structureFile.exists()) {
        plugin.logger.severe("$structureName.nbt 파일을 찾을 수 없습니다!")
        return false
    }

    val structure: Structure = structureManager.loadStructure(structureFile)

    structure.place(location, true, StructureRotation.NONE, Mirror.NONE, 0, 1.0f, Random())

    return true
}
