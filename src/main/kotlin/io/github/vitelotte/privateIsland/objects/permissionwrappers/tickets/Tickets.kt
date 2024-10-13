package io.github.vitelotte.privateIsland.objects.permissionwrappers.tickets

import org.bukkit.permissions.Permission
import org.bukkit.permissions.PermissionDefault

object Tickets {

    val TICKET_SUPER: Permission = Permission(
        "9um4.private-island.ticket.*",
        "Permission for all activities for private island tickets.",
        PermissionDefault.OP,
    )

    init {
        for (perm in TicketUses.getSuperPermissions()) {
            perm.addParent(TICKET_SUPER, true)
        }
    }

    fun

}