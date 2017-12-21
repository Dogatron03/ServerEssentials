package net.diversionmc.ServerEssentials.commands.admin;

import net.diversionmc.ServerEssentials.ServerEssentials;
import net.diversionmc.ServerEssentials.managment.*;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Property;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class Permissions extends SECommand {

    public Permissions() {
        super("perms", "*", "/perms", 0, false, "sep", "perm", "permissions");
    }

    public void call(MinecraftServer s, ICommandSender p, String[] args) {
        if (args.length == 0) {
            sendHelp(p);
            return;
        }
        switch (args[0].toLowerCase()) {
            case "role": {
                if (args.length >= 2) {
                    switch (args[1]) {
                        case "set": {
                            if (args.length < 4) {
                                sendHelp(p);
                                return;
                            }
                            UUID u = EntityPlayerMP.getOfflineUUID(args[2]);
                            if (u == null) {
                                p.addChatMessage(new TextComponentString(SEColour.RED + "That's not a valid player!"));
                                return;
                            }
                            ConfigCategory c = SERoles.getConfigCategory(SERoles.getRole(u));
                            SERoles newRole = SERoles.getById(args[3]);
                            if (newRole == null) {
                                p.addChatMessage(new TextComponentString(SEColour.RED + "That't not a valid role!"));
                                return;
                            }
                            if (c != null) {
                                List<String> members = Arrays.asList(c.get("members").getStringList());
                                members.remove(u.toString());
                                c.put("members", new Property("members", members.toArray(new String[]{}), Property.Type.STRING));
                                SERoles.roles.save();
                            }
                            if (newRole != SERoles.MEMBER) {
                                ConfigCategory cfg = SERoles.getConfigCategory(newRole);
                                List<String> members = Arrays.asList(cfg.get("members").getStringList());
                                members.forEach(m -> System.out.println(m));
                                members.add(u.toString());
                                cfg.put("members", new Property("members", members.toArray(new String[]{}), Property.Type.STRING));
                                SERoles.roles.save();
                            }
                            p.addChatMessage(new TextComponentString(SEColour.GREEN + "Successfully changed " + args[2] + "'s role to be " + args[3] + "!"));
                            return;
                        }
                        case "get": {
                            if (args.length < 3) {
                                sendHelp(p);
                                return;
                            }
                            UUID u = EntityPlayerMP.getOfflineUUID(args[2]);
                            if (u == null) {
                                p.addChatMessage(new TextComponentString(SEColour.RED + "That's not a valid player!"));
                                return;
                            }
                            SERoles role = SERoles.getRole(u);
                            p.addChatMessage(new TextComponentString(SEColour.GREEN + args[2] + "'s role is" + role.colour + role.prefix + " (" + role.id + ")!"));
                            return;
                        }
                    }
                    sendHelp(p);
                } else {
                    sendHelp(p);
                }
                return;
            }
            case "reload": {
                SEPermissions.loadConfig();
                SERoles.loadConfig();
                SEConfig.loadConfig();
                p.addChatMessage(new TextComponentString(SEColour.GREEN + "Reloaded Config Files!"));
                return;
            }
            case "save": {
                SEPermissions.perms.save();
                SERoles.roles.save();
                SEConfig.config.save();
                p.addChatMessage(new TextComponentString(SEColour.GREEN + "Saved Config Files!"));
                return;
            }

        }
        sendHelp(p);
    }


    public void sendHelp(ICommandSender p) {
        p.addChatMessage(new TextComponentString(SEColour.BLUE + "ServerEssentials Permissions: "));
        p.addChatMessage(new TextComponentString(SEColour.BLUE + "* /perms reload - Reloads permissions config"));
        p.addChatMessage(new TextComponentString(SEColour.BLUE + "* /perms role set <user> <role> - Sets a users role"));
        p.addChatMessage(new TextComponentString(SEColour.BLUE + "* /perms role get <user> - Gets users role"));
        p.addChatMessage(new TextComponentString(SEColour.BLUE + "* /perms add <role> <permission> - Gives a role a permission"));
        p.addChatMessage(new TextComponentString(SEColour.BLUE + "* /perms remove <role> <permission> - Removes a roles permission"));
        p.addChatMessage(new TextComponentString(SEColour.BLUE + "* /perms check <role> <permission> - Check if a role has permission"));
        p.addChatMessage(new TextComponentString(SEColour.BLUE + "* /perms save - Save permissions config"));
        p.addChatMessage(new TextComponentString(SEColour.BLUE + "ServerEssentials version: " + ServerEssentials.VERSION));
    }

}
