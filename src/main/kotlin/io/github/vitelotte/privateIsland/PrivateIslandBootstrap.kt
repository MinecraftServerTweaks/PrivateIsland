package io.github.vitelotte.privateIsland

import io.papermc.paper.command.brigadier.Commands
import io.papermc.paper.plugin.bootstrap.BootstrapContext
import io.papermc.paper.plugin.bootstrap.PluginBootstrap
import io.papermc.paper.plugin.lifecycle.event.LifecycleEventManager
import io.papermc.paper.plugin.lifecycle.event.registrar.ReloadableRegistrarEvent
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents
import org.eclipse.sisu.bean.LifecycleManager

class PrivateIslandBootstrap: PluginBootstrap {
    override fun bootstrap(context: BootstrapContext) {
        val manager: LifecycleEventManager<BootstrapContext> = context.lifecycleManager
        manager.registerEventHandler<ReloadableRegistrarEvent<Commands>>(
            LifecycleEvents.COMMANDS
        ) {
            event: ReloadableRegistrarEvent<Commands> ->
            val commands = event.registrar()
            commands.register()
        }

    }
}