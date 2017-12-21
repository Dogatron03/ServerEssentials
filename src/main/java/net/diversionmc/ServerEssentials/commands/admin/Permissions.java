package net.diversionmc.ServerEssentials.commands.admin;

import net.diversionmc.ServerEssentials.ServerEssentials;
import net.diversionmc.ServerEssentials.commands.user.ConfigCommands;
import net.diversionmc.ServerEssentials.management.*;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Property;

import java.util.ArrayList;
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
            case "user": {
                if (args.length >= 2) {
                    switch (args[1]) {
                        case "set": {
                            if (args.length < 4) {
                                sendHelp(p);
                                return;
                            }
                            UUID u = SEUUID.get(args[2]);
                            if (u == null) {
                                p.addChatMessage(new TextComponentString(SEColour.RED + "That's not a valid player!"));
                                return;
                            }
                            SERoles role = SERoles.getRole(u);
                            SERoles newRole = SERoles.getById(args[3]);
                            if (newRole == null) {
                                p.addChatMessage(new TextComponentString(SEColour.RED + "That't not a valid role!"));
                                return;
                            }
                            if (role != SERoles.MEMBER) {
                                ConfigCategory c = SERoles.getConfigCategory(SERoles.getRole(u));
                                List<String> members = new ArrayList<>(Arrays.asList(c.get("members").getStringList()));
                                members.remove(u.toString());
                                c.put("members", new Property("members", members.toArray(new String[]{}), Property.Type.STRING));
                                SERoles.roles.save();
                            }
                            if (newRole != SERoles.MEMBER) {
                                ConfigCategory c = SERoles.getConfigCategory(newRole);
                                List<String> members = new ArrayList<>(Arrays.asList(c.get("members").getStringList()));
                                members.add(u.toString());
                                c.put("members", new Property("members", members.toArray(new String[]{}), Property.Type.STRING));
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
                            UUID u = SEUUID.get(args[2]);
                            if (u == null) {
                                p.addChatMessage(new TextComponentString(SEColour.RED + "That's not a valid player!"));
                                return;
                            }
                            SERoles role = SERoles.getRole(u);
                            p.addChatMessage(new TextComponentString(SEColour.GREEN + args[2] + "'s role is " + role.colour + role.prefix + " (" + role.id + ")!"));
                            return;
                        }
                        case "check": {
                            if (args.length < 4) {
                                sendHelp(p);
                                return;
                            }
                            UUID u = SEUUID.get(args[2]);
                            if (u == null) {
                                p.addChatMessage(new TextComponentString(SEColour.RED + "That's not a valid player!"));
                                return;
                            }
                            p.addChatMessage(new TextComponentString(SEPermissions.hasPermission(u, args[3]) ? SEColour.GREEN + args[2] + " has the permission " + args[3] + "!" : SEColour.GREEN + args[2] + SEColour.RED + " does not" + SEColour.GREEN + " have the permission " + args[3] + "!"));
                            return;
                        }
                    }
                    sendHelp(p);
                } else {
                    sendHelp(p);
                }
                return;
            }
            case "role": {
                if (args.length >= 2) {
                    switch (args[1]) {
                        case "add": {
                            if (args.length < 4) {
                                sendHelp(p);
                                return;
                            }
                            SERoles r = SERoles.getById(args[2]);
                            if (r == null) {
                                p.addChatMessage(new TextComponentString(SEColour.RED + "That's not a valid role!"));
                                return;
                            }
                            SEPermissions.givePermission(r, args[3]);
                            p.addChatMessage(new TextComponentString(SEColour.GREEN + "Given " + args[2] + " the permission " + args[3] + "!"));
                            return;
                        }
                        case "remove": {
                            if (args.length < 4) {
                                sendHelp(p);
                                return;
                            }
                            SERoles r = SERoles.getById(args[2]);
                            if (r == null) {
                                p.addChatMessage(new TextComponentString(SEColour.RED + "That's not a valid role!"));
                                return;
                            }
                            SEPermissions.takePermission(r, args[3]);
                            p.addChatMessage(new TextComponentString(SEColour.GREEN + "Removed permission " + args[3] + " from " + args[2] + "!"));
                            return;
                        }
                        case "check": {
                            if (args.length < 4) {
                                sendHelp(p);
                                return;
                            }
                            SERoles r = SERoles.getById(args[2]);
                            if (r == null) {
                                p.addChatMessage(new TextComponentString(SEColour.RED + "That's not a valid role!"));
                                return;
                            }
                            p.addChatMessage(new TextComponentString(SEPermissions.hasPermission(r, args[3]) ? SEColour.GREEN + args[2] + " has the permission " + args[3] + "!" : SEColour.GREEN + args[2] + SEColour.RED + " does not" + SEColour.GREEN + " have the permission " + args[3] + "!"));
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
                SEUUID.loadConfig();
                ConfigCommands.loadConfig();
                p.addChatMessage(new TextComponentString(SEColour.GREEN + "Reloaded Config Files!"));
                return;
            }
            case "save": {
                SEPermissions.perms.save();
                SERoles.roles.save();
                SEConfig.config.save();
                SEUUID.uuid.save();
                ConfigCommands.config.save();
                p.addChatMessage(new TextComponentString(SEColour.GREEN + "Saved Config Files!"));
                return;
            }

        }
        sendHelp(p);
    }


    public void sendHelp(ICommandSender p) {
        p.addChatMessage(new TextComponentString(SEColour.BLUE + "ServerEssentials Permissions: "));
        p.addChatMessage(new TextComponentString(SEColour.BLUE + "* /perms user set <user> <role> - Sets a users role"));
        p.addChatMessage(new TextComponentString(SEColour.BLUE + "* /perms user get <user> - Gets users role"));
        p.addChatMessage(new TextComponentString(SEColour.BLUE + "* /perms user check <user> <permission> - Check if a user has permission"));
        p.addChatMessage(new TextComponentString(SEColour.BLUE + "* /perms role add <role> <permission> - Gives a role a permission"));
        p.addChatMessage(new TextComponentString(SEColour.BLUE + "* /perms role remove <role> <permission> - Removes a roles permission"));
        p.addChatMessage(new TextComponentString(SEColour.BLUE + "* /perms role check <role> <permission> - Check if a role has permission"));
        p.addChatMessage(new TextComponentString(SEColour.BLUE + "* /perms reload - Reloads permissions config"));
        p.addChatMessage(new TextComponentString(SEColour.BLUE + "* /perms save - Save permissions config"));
        p.addChatMessage(new TextComponentString(SEColour.BLUE + "ServerEssentials version: " + ServerEssentials.VERSION));
    }

}
