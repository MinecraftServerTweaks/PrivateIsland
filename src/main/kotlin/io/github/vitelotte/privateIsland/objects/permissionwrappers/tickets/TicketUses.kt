package io.github.vitelotte.privateIsland.objects.permissionwrappers.tickets

import org.bukkit.permissions.Permission
import org.bukkit.permissions.PermissionDefault

object TicketUses {

    val TICKET_USE_SELF: Permission = Permission(
        "9um4.private-island.ticket.use",
        "Permission for using island tickets.",
        PermissionDefault.TRUE
    )

    val TICKET_USE_OTHER_SUPER: Permission = Permission(
        "9um4.private-island.ticket.use.other",
        "Permission for using island tickets on other players.",
        PermissionDefault.OP
    )

    val TICKET_USE_OTHER_EXCEPT_OP: Permission = Permission(
        "9um4.private-island.ticket.use.other.except-op",
        "Permission for using island tickets on other players except OPs.",
        PermissionDefault.OP
    )

    val TICKET_USE_OTHER_ONLY_OP: Permission = Permission(
        "9um4.private-island.ticket.use.other.only-op",
        "Permission for using island tickets on other players except admins.",
        PermissionDefault.OP
    )

    init{
        TICKET_USE_OTHER_EXCEPT_OP.addParent(TICKET_USE_OTHER_SUPER, true)
        TICKET_USE_OTHER_ONLY_OP.addParent(TICKET_USE_OTHER_SUPER, true)
    }

    fun getSuperPermissions(): List<Permission> {
        return listOf(
            TICKET_USE_SELF,
            TICKET_USE_OTHER_SUPER
        )
    }
}