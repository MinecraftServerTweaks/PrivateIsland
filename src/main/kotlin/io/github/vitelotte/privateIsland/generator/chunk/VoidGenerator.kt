package io.github.vitelotte.privateIsland.generator.chunk

import io.github.vitelotte.privateIsland.PrivateIsland
import io.github.vitelotte.privateIsland.generator.biome.VoidProvider
import org.bukkit.Location
import org.bukkit.World
import org.bukkit.generator.BiomeProvider
import org.bukkit.generator.BlockPopulator
import org.bukkit.generator.ChunkGenerator
import org.bukkit.generator.WorldInfo
import java.util.*


class VoidGenerator : ChunkGenerator() {

    private val config = PrivateIsland.instance.config

    private val centerX: Int = config.getInt("islands.center.x")
    private val centerY: Int = config.getInt("islands.center.y")
    private val centerZ: Int = config.getInt("islands.center.z")

    override fun getDefaultPopulators(world: World): MutableList<BlockPopulator> {
        return mutableListOf()
    }

    override fun generateNoise(
        worldInfo: WorldInfo, random: Random, chunkX: Int, chunkZ: Int,
        chunkData: ChunkData
    ) {
        // No need to generate noise, we want an empty world
    }

    override fun generateSurface(
        worldInfo: WorldInfo, random: Random, chunkX: Int, chunkZ: Int,
        chunkData: ChunkData
    ) {
        // No need to generate surface, we want an empty world
    }

    override fun generateBedrock(
        worldInfo: WorldInfo, random: Random, chunkX: Int, chunkZ: Int,
        chunkData: ChunkData
    ) {
        // No need to generate bedrock, we want an empty world
    }

    override fun generateCaves(
        worldInfo: WorldInfo, random: Random, chunkX: Int, chunkZ: Int,
        chunkData: ChunkData
    ) {
        // No need to generate caves, we want an empty world
    }

    override fun getDefaultBiomeProvider(worldInfo: WorldInfo): BiomeProvider {
        return VoidProvider()
    }

    override fun canSpawn(world: World, x: Int, z: Int): Boolean {
        return true
    }

    override fun getFixedSpawnLocation(world: World, random: Random): Location {
        return Location(world, centerX.toDouble(), centerY.toDouble(), centerZ.toDouble())
    }
}