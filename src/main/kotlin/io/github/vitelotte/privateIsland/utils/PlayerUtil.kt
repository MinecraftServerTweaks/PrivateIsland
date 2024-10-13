package io.github.vitelotte.privateIsland.utils

import io.github.vitelotte.privateIsland.PrivateIsland
import io.github.vitelotte.privateIsland.generator.chunk.VoidGenerator
import net.kyori.adventure.text.Component
import org.bukkit.*
import org.bukkit.entity.Player
import org.bukkit.persistence.PersistentDataContainer
import org.bukkit.persistence.PersistentDataType

private val islandsWorldName: String = PrivateIsland.instance.config.getString("islands.world")?: "islands"
private val islandsWorld: World = Bukkit.getWorld(islandsWorldName) ?: WorldCreator(islandsWorldName).generator(VoidGenerator()).createWorld()!!
private val islandLocationKey: NamespacedKey = NamespacedKey(PrivateIsland.instance, "Location")

fun Player.setIslandLocation(x: Int, y: Int, z: Int) {

    val position: IntArray = IntArray(3, {0})
    position[0] = x
    position[1] = y
    position[2] = z

    this.persistentDataContainer.set(islandLocationKey, PersistentDataType.INTEGER_ARRAY, position)
}

fun Player.getIslandLocation(): Location? {
    if (!this.persistentDataContainer.has(islandLocationKey, PersistentDataType.INTEGER_ARRAY)) return null

    val position: IntArray = persistentDataContainer.get(islandLocationKey, PersistentDataType.INTEGER_ARRAY)!!
    return Location(islandsWorld, position[0].toDouble(), position[1].toDouble(), position[2].toDouble())
}

fun Player.deleteIslandLocation(){
    this.persistentDataContainer.remove(islandLocationKey)
}

fun Player.allocateIsland(): Location? {
    if (this.getIslandLocation() != null) {
        this.sendMessage(Component.text("이미 섬이 존재합니다!"))
        return null
    }

    val loc: Location = islandsWorld.createNewIsland() ?: return null

    this.setIslandLocation(loc.blockX, loc.blockY, loc.blockZ)

    return loc
}

fun Player.deallocateIsland(): Boolean {
    val islandLocation: Location = this.getIslandLocation()?: return false

    if (islandsWorld.deleteIsland(islandLocation)) {
        this.sendMessage(Component.text("섬을 성공적으로 삭제했습니다."))
        this.deleteIslandLocation()
        return true
    }

    return false
}