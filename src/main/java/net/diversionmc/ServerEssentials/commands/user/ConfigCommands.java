package net.diversionmc.ServerEssentials.commands.user;

import net.diversionmc.ServerEssentials.management.SEColour;
import net.diversionmc.ServerEssentials.management.SECommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Loader;

import java.io.File;

public class ConfigCommands {

    public static Configuration config;

    public static void loadConfig() {
        File file = new File(Loader.instance().getConfigDir() + File.separator + "ServerEssentials", "commands.cfg");
        config = new Configuration(file);
        config.load();
        for (String s : config.getCategoryNames()) {
            ConfigCategory c = config.getCategory(s);
            new SECommand(s, c.get("permission").getString(), "/" + s, 0, false, c.get("aliases").getStringList()) {
                public void call(MinecraftServer s, ICommandSender p, String[] args) {
                    for (String message : c.get("message").getStringList()) {
                        p.addChatMessage(new TextComponentString(SEColour.translate(message)));
                    }
                }
            };
        }
    }
}
