package net.diversionmc.ServerEssentials;

import net.diversionmc.ServerEssentials.managment.*;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

import java.io.File;

import static net.diversionmc.ServerEssentials.ServerEssentials.*;

@Mod(modid = MODID, name = NAME, acceptedMinecraftVersions = "[1.10.2]", version = VERSION)
public class ServerEssentials {

    public static final String VERSION = "1.0";
    public static final String NAME = "ServerEssentials";
    public static final String MODID = "serveressentials";


    public static MinecraftServer server;

    @Mod.Instance
    public static ServerEssentials instance;

    @Mod.EventHandler
    public void onEnable(FMLServerStartingEvent e) {
        System.out.println("Welcome to ServerEssentials, the one mod needed to manage commands on your server!");
        server = e.getServer();
        File file = new File(Loader.instance().getConfigDir() + File.separator + "ServerEssentials");
        file.mkdir();
        SEPermissions.loadConfig();
        SERoles.loadConfig();
        SEConfig.loadConfig();
        System.out.println("Loaded config files, proceeding to load commands!");
        SECommand.loadCommands();
        MinecraftForge.EVENT_BUS.register(new SEEvents());
        SECommand.commands.forEach(c -> e.registerServerCommand(c.mc));
        System.out.println("Loaded ServerEssentials!");
    }


}
