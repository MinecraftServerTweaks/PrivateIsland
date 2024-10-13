package io.github.vitelotte.privateIsland.listeners

import io.github.vitelotte.privateIsland.PrivateIsland
import io.github.vitelotte.privateIsland.managers.IslandManager
import io.github.vitelotte.privateIsland.utils.isIslandTicket
import org.bukkit.Material
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.permissions.Permission

class ItemUseListener: Listener {

    private val plugin: PrivateIsland = PrivateIsland.instance
    private val islandManager: IslandManager = plugin.islandManger
    private val config: FileConfiguration = plugin.config

    @EventHandler
    fun onPlayerUseItem(event: PlayerInteractEvent) {
        val player: Player = event.player

        if (event.item == null || event.item?.type != Material.PAPER) return

        val item = event.item!!

        if (!item.isIslandTicket()) return

        if (!player.hasPermission(Permission()))
    }
}