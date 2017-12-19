package net.diversionmc.ServerEssentials;

import net.diversionmc.ServerEssentials.managment.SECommand;
import net.diversionmc.ServerEssentials.managment.SEEvents;
import net.diversionmc.ServerEssentials.managment.SEPermissions;
import net.diversionmc.ServerEssentials.managment.SERoles;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

@Mod(modid = "serveressentials", name = "serveressentials", acceptedMinecraftVersions = "[1.10.2]", version = "1.0")
public class ServerEssentials {

    public static MinecraftServer server;

    @Mod.EventHandler
    public static void onEnable(FMLServerStartingEvent e) {
        server = e.getServer();
        MinecraftForge.EVENT_BUS.register(new SEEvents());
        SECommand.commands.forEach(c -> e.registerServerCommand(c.mc));
        SEPermissions.loadConfig();
        SERoles.loadConfig();
    }


}
