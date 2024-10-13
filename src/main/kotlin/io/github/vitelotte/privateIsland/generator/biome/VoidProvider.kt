package io.github.vitelotte.privateIsland.generator.biome

import org.bukkit.block.Biome
import org.bukkit.generator.BiomeProvider
import org.bukkit.generator.WorldInfo

class VoidProvider: BiomeProvider() {

    override fun getBiome(worldInfo: WorldInfo, x: Int, y: Int, z: Int): Biome {
        return Biome.THE_VOID
    }

    override fun getBiomes(worldInfo: WorldInfo): MutableList<Biome?> {
        return mutableListOf(Biome.THE_VOID)
    }
}