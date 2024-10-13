package io.github.vitelotte.privateIsland.utils

import io.github.vitelotte.privateIsland.PrivateIsland
import org.bukkit.Material
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.inventory.ItemStack

private val config: FileConfiguration = PrivateIsland.instance.config

fun ItemStack.isIslandTicket(): Boolean {
    return this.type == Material.PAPER && this.itemMeta.customModelData == config.getInt("ticket.custom-model-data")
}