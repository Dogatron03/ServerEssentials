package net.diversionmc.ServerEssentials.commands.admin;

import net.diversionmc.ServerEssentials.ServerEssentials;
import net.diversionmc.ServerEssentials.managment.SEColour;
import net.diversionmc.ServerEssentials.managment.SECommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import org.apache.logging.log4j.core.jmx.Server;

public class Permissions extends SECommand {

    public Permissions() {
        super("perms", "*", "/perms help", 1, false, "sep", "perm", "permissions");
    }

    public void call(MinecraftServer s, ICommandSender p, String[] args) {
        switch(args[0].toLowerCase()){
            case "help":
                p.addChatMessage(new TextComponentString(SEColour.DARK_BLUE + "ServerEssentials Permissions: "));
                p.addChatMessage(new TextComponentString(SEColour.DARK_BLUE + "* /perms reload - Reloads permissions config"));
                p.addChatMessage(new TextComponentString(SEColour.DARK_BLUE + "* /perms role set <user> <role> - Sets a users role"));
                p.addChatMessage(new TextComponentString(SEColour.DARK_BLUE + "* /perms role get <user> - Gets users role"));
                p.addChatMessage(new TextComponentString(SEColour.DARK_BLUE + "* /perms add <role> <permission> - Gives a role a permission"));
                p.addChatMessage(new TextComponentString(SEColour.DARK_BLUE + "* /perms remove <role> <permission> - Removes a roles permission"));
                p.addChatMessage(new TextComponentString(SEColour.DARK_BLUE + "* /perms check <role> <permission> - Check if a role has permission"));
                p.addChatMessage(new TextComponentString(SEColour.DARK_BLUE + "* /perms save - Save permissions config"));
                p.addChatMessage(new TextComponentString(SEColour.DARK_BLUE + "ServerEssentials version: " + ServerEssentials.VERSION));
        }
    }
}
