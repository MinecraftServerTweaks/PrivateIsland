package io.github.vitelotte.privateIsland.objects.permissionwrappers

import org.bukkit.permissions.Permission

abstract class AbstractPermissionWrapper{
    abstract val childrenPermissionWrapper: List<AbstractPermissionWrapper>

    abstract fun getSuperPermissions(): List<Permission>

    init {
        if (childrenPermissionWrapper.isNullOrEmpty()) {
            for (childPermissionWrapper in childPerms) {
                for (perm)
            }
        }
    }
}