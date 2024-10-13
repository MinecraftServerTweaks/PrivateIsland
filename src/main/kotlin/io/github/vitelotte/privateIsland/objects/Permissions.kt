package io.github.vitelotte.privateIsland.objects

import it.unimi.dsi.fastutil.Hash
import org.bukkit.permissions.Permission
import org.bukkit.permissions.PermissionDefault

object Permissions {
    /*
        PERMISSION_SUPER
            PERMISSION_TICKET_SUPER
                PERMISSION_TICKET_USE
            PERMISSION_COMMAND_SUPER
                PERMISSION_COMMAND_TICKET_SUPER
                    PERMISSION_COMMAND_TICKET_ISSUE
                PERMISSION_COMMAND_ISLAND_SUPER
                    PERMISSION_COMMAND_ISLAND_CREATE_SUPER
                        PERMISSION_COMMAND_ISLAND_CREATE_SELF
                        PERMISSION_COMMAND_ISLAND_CREATE_OTHER_SUPER
                            PERMISSION_COMMAND_ISLAND_CREATE_OTHER_EXCEPT_OP
                            PERMISSION_COMMAND_ISLAND_CREATE_OTHER_ONLY_OP
                    PERMISSION_COMMAND_ISLAND_DELETE_SUPER
                        PERMISSION_COMMAND_ISLAND_DELETE_SELF
                        PERMISSION_COMMAND_ISLAND_DELETE_OTHER_SUPER
                            PERMISSION_COMMAND_ISLAND_DELETE_OTHER_EXCEPT_OP
                            PERMISSION_COMMAND_ISLAND_DELETE_OTHER_ONLY_OP
            PERMISSION
     */

    lateinit var PERMISSION_SUPER: Permission
        private set

    lateinit var PERMISSION_TICKET_SUPER: Permission
        private set


    lateinit var PERMISSION_COMMAND_SUPER: Permission
        private set

    lateinit var PERMISSION_COMMAND_TICKET_SUPER: Permission
        private set

    val PERMISSION_COMMAND_TICKET_ISSUE: Permission = Permission(
        "9um4.private-island.command.ticket.issue",
        "Permission for issuing island tickets.",
        PermissionDefault.OP
    )

    lateinit var PERMISSION_COMMAND_ISLAND_SUPER: Permission
        private set

    lateinit var PERMISSION_COMMAND_ISLAND_CREATE_SUPER: Permission
    private set

    val PERMISSION_COMMAND_ISLAND_CREATE_SELF: Permission = Permission(
        "9um4.private-island.command.island.create.self",
        "Permission for creating private island.",
        PermissionDefault.TRUE
    )

    lateinit var PERMISSION_COMMAND_ISLAND_CREATE_OTHER_SUPER: Permission
    private set

    val PERMISSION_COMMAND_ISLAND_CREATE_OTHER_EXCEPT_OP: Permission = Permission(
        "9um4.private-island.command.island.create.other.except-op",
        "Permission for creating private island except OP.",
        PermissionDefault.OP
    )

    val PERMISSION_COMMAND_ISLAND_CREATE_OTHER_ONLY_OP: Permission = Permission(
        "9um4.private-island.command.island.create.other.only-op",
        "Permission for creating private island only OP.",
        PermissionDefault.OP
    )

    lateinit var PERMISSION_COMMAND_ISLAND_DELETE_SUPER: Permission

    val PERMISSION_COMMAND_ISLAND_DELETE_SELF: Permission = Permission(
        "9um4.private-island.command.island.delete.self",
        "Permission for deleting own private island.",
        PermissionDefault.OP
    )

    lateinit var PERMISSION_COMMAND_ISLAND_DELETE_OTHER_SUPER: Permission
        private set

    val PERMISSION_COMMAND_ISLAND_DELETE_OTHER_EXCEPT_OP: Permission = Permission(
        "9um4.private-island.command.island.delete.other.except-op",
        "Permission for deleting private island except OP.",
        PermissionDefault.OP
    )

    val PERMISSION_COMMAND_ISLAND_DELETE_OTHER_ONLY_OP: Permission = Permission(
        "9um4.private-island.command.island.delete.other.only-op",
        "Permission for deleting private island only OP.",
        PermissionDefault.OP
    )

    lateinit var PERMISSION_ISLAND_CREATE_SUPER: Permission
    private set

    val PERMISSION_ISLAND_CREATE_SELF: Permission = Permission(
        "9um4.private-island.island.create.self",
        "Permission for creating private island.",
        PermissionDefault.TRUE
    )

    lateinit var PERMISSION_ISLAND_CREATE_OTHER_SUPER: Permission
    private set

    val PERMISSION_ISLAND_CREATE_OTHER_EXCEPT_OP: Permission = Permission(
        "9um4.private-island.island.create.other.except-op",
        "Permission for creating private island except OP.",
        PermissionDefault.OP
    )

    val PERMISSION_ISLAND_CREATE_OTHER_ONLY_OP: Permission = Permission(
        "9um4.private-island.island.create.other.only-op",
        "Permission for creating private island only OP.",
        PermissionDefault.OP
    )

    lateinit var PERMISSION_ISLAND_DELETE_SUPER: Permission
    private set

    val PERMISSION_ISLAND_DELETE_SELF: Permission = Permission(
        "9um4.private-island.island.delete.self",
        "Permission for deleting own private island.",
        PermissionDefault.TRUE
    )

    lateinit var PERMISSION_ISLAND_DELETE_OTHER_SUPER: Permission
    private set

    val PERMISSION_ISLAND_DELETE_OTHER_EXCEPT_OP: Permission = Permission(
        "9um4.private-island.island.delete.other.except-op",
        "Permission for deleting private island except OP.",
        PermissionDefault.OP
    )

    val PERMISSION_ISLAND_DELETE_OTHER_ONLY_OP: Permission = Permission(
        "9um4.private-island.island.delete.other.only-op",
        "Permission for deleting private island only OP.",
        PermissionDefault.OP
    )

    init{
        PERMISSION_SUPER = Permission(
            "9um4.private-island.*",
            "Permission for all activities for private island.",
            PermissionDefault.OP,
            getSuperChildren()
        )

        PERMISSION_ISLAND_SUPER = Permission(
            "9um4.private-island.island.*",
        "Permission for all activities of private island",
            PermissionDefault.OP,
            getIslandSuperChildren()
        )

        PERMISSION_TICKET_SUPER = Permission(
            "9um4.private-island.ticket.*",
            "Permission for all activities of ticket",
            PermissionDefault.OP,
            getTicketSuperChildren()
        )

        PERMISSION_ISLAND_DELETE_OTHER_SUPER = Permission(
            "9um4.private-island.island.delete.other.*",
            "Permission for deleting private island except OP.",
            PermissionDefault.OP,
            getIslandDeleteOtherPermissionChildren()
        )
    }

    private fun getIslandDeleteOtherPermissionChildren(): HashMap<String, Boolean>{
        val children: HashMap<String, Boolean> = HashMap()
        PERMISSION_ISLAND_DELETE_OTHER_ONLY_OP.addParent(PERMISSION_ISLAND_DELETE_OTHER_SUPER, true)
        children[PERMISSION_ISLAND_DELETE_OTHER_ONLY_OP.name] = true
    }

    fun getAllPermissions(): MutableList<Permission>{
        return mutableListOf(
            PERMI
            Permissions.PERMISSION_SUPER,
            Permissions.PERMISSION_TICKET_SUPER,
            Permissions.PERMISSION_ISLAND_SUPER
        )
    }

}