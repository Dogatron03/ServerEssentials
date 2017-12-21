package net.diversionmc.ServerEssentials.commands.mod;

import net.diversionmc.ServerEssentials.management.SEColour;
import net.diversionmc.ServerEssentials.management.SECommand;
import net.diversionmc.ServerEssentials.management.SEPermissions;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;

public class Fly extends SECommand {
    public Fly() {
        super("fly", "command.fly.self", "/fly [player] [true/false]", 0, false);
    }

    public void call(MinecraftServer s, EntityPlayerMP p, String[] args) {
        if (args.length == 0) {
            if (p.capabilities.allowFlying) {
                p.capabilities.allowFlying = true;
                p.addChatMessage(new TextComponentString(SEColour.GREEN + "You can now fly!"));
            } else {
                p.capabilities.allowFlying = false;
                p.capabilities.isFlying = false;
                p.addChatMessage(new TextComponentString(SEColour.GREEN + "You can no longer fly!"));
            }
        } else if (args.length == 1) {
            if (!SEPermissions.hasPermission(p, "command.fly.others")) {
                p.addChatMessage(new TextComponentString(SEColour.RED + "You don't have enough permissions to toggle others flight this command!"));
                return;
            }
            EntityPlayerMP t = s.getPlayerList().getPlayerByUsername(args[0]);
            if (t == null) {
                p.addChatMessage(new TextComponentString(SEColour.RED + "That's not a valid player!"));
                return;
            }
            if (t.capabilities.allowFlying) {
                t.capabilities.allowFlying = true;
                t.addChatMessage(new TextComponentString(SEColour.GREEN + "You can now fly!"));
                p.addChatMessage(new TextComponentString(SEColour.GREEN + "Let " + t.getName() + " fly!"));
            } else {
                t.capabilities.allowFlying = false;
                t.capabilities.isFlying = false;
                t.addChatMessage(new TextComponentString(SEColour.GREEN + "You can no longer fly!"));
                p.addChatMessage(new TextComponentString(SEColour.GREEN + "Made " + t.getName() + " not be able to fly!"));
            }
        } else {
            if (!SEPermissions.hasPermission(p, "command.fly.others")) {
                p.addChatMessage(new TextComponentString(SEColour.RED + "You don't have enough permissions to toggle others flight this command!"));
                return;
            }
            EntityPlayerMP t = s.getPlayerList().getPlayerByUsername(args[0]);
            if (t == null) {
                p.addChatMessage(new TextComponentString(SEColour.RED + "That's not a valid player!"));
                return;
            }
            boolean b = Boolean.parseBoolean(args[1]);
            if (b) {
                t.capabilities.allowFlying = true;
                t.addChatMessage(new TextComponentString(SEColour.GREEN + "You can now fly!"));
                p.addChatMessage(new TextComponentString(SEColour.GREEN + "Let " + t.getName() + " fly!"));
            } else {
                t.capabilities.allowFlying = false;
                t.capabilities.isFlying = false;
                t.addChatMessage(new TextComponentString(SEColour.GREEN + "You can no longer fly!"));
                p.addChatMessage(new TextComponentString(SEColour.GREEN + "Made " + t.getName() + " not be able to fly!"));
            }
        }
    }

    @Override
    public void call(MinecraftServer s, ICommandSender p, String[] args) {
        if (args.length == 0) {
            p.addChatMessage(new TextComponentString(mc.getCommandUsage(p)));
            return;
        }
        if (args.length == 1) {
            if (!SEPermissions.hasPermission(p, "command.fly.others")) {
                p.addChatMessage(new TextComponentString(SEColour.RED + "You don't have enough permissions to toggle others flight this command!"));
                return;
            }
            EntityPlayerMP t = s.getPlayerList().getPlayerByUsername(args[0]);
            if (t == null) {
                p.addChatMessage(new TextComponentString(SEColour.RED + "That's not a valid player!"));
                return;
            }
            if (t.capabilities.allowFlying) {
                t.capabilities.allowFlying = true;
                t.addChatMessage(new TextComponentString(SEColour.GREEN + "You can now fly!"));
                p.addChatMessage(new TextComponentString(SEColour.GREEN + "Let " + t.getName() + " fly!"));
            } else {
                t.capabilities.allowFlying = false;
                t.capabilities.isFlying = false;
                t.addChatMessage(new TextComponentString(SEColour.GREEN + "You can no longer fly!"));
                p.addChatMessage(new TextComponentString(SEColour.GREEN + "Made " + t.getName() + " not be able to fly!"));
            }
        } else {
            if (!SEPermissions.hasPermission(p, "command.fly.others")) {
                p.addChatMessage(new TextComponentString(SEColour.RED + "You don't have enough permissions to toggle others flight this command!"));
                return;
            }
            EntityPlayerMP t = s.getPlayerList().getPlayerByUsername(args[0]);
            if (t == null) {
                p.addChatMessage(new TextComponentString(SEColour.RED + "That's not a valid player!"));
                return;
            }
            boolean b = Boolean.parseBoolean(args[1]);
            if (b) {
                t.capabilities.allowFlying = true;
                t.addChatMessage(new TextComponentString(SEColour.GREEN + "You can now fly!"));
                p.addChatMessage(new TextComponentString(SEColour.GREEN + "Let " + t.getName() + " fly!"));
            } else {
                t.capabilities.allowFlying = false;
                t.capabilities.isFlying = false;
                t.addChatMessage(new TextComponentString(SEColour.GREEN + "You can no longer fly!"));
                p.addChatMessage(new TextComponentString(SEColour.GREEN + "Made " + t.getName() + " not be able to fly!"));
            }
        }

    }
}
