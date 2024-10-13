package io.github.vitelotte.privateIsland

import io.github.vitelotte.privateIsland.listeners.ItemUseListener
import io.github.vitelotte.privateIsland.managers.IslandManager
import io.github.vitelotte.privateIsland.objects.Permissions
import org.bukkit.permissions.Permission
import org.bukkit.permissions.PermissionDefault
import org.bukkit.plugin.java.JavaPlugin

class PrivateIsland : JavaPlugin() {

    companion object {
        lateinit var instance: PrivateIsland
            private set
    }

    lateinit var islandManger: IslandManager

    override fun onEnable() {
        instance = this

        saveDefaultConfig()

        islandManger = IslandManager()

        server.pluginManager.addPermissions()

        server.pluginManager.registerEvents(ItemUseListener(), this)
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
}
