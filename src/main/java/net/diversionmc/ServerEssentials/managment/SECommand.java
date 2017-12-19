package net.diversionmc.ServerEssentials.managment;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;

import java.util.ArrayList;
import java.util.List;

public class SECommand {

    public static List<SECommand> commands = new ArrayList<>();

    public final String name;
    public final String permission;
    public final String usage;
    public final int argsLength;
    public final boolean playerOnly;
    public final SECommand i;
    public final CommandBase mc;


    public SECommand(String name, String permission, String usage, int argsLength, boolean playerOnly) {
        this.name = name;
        this.permission = permission;
        this.usage = usage;
        this.argsLength = argsLength;
        this.playerOnly = playerOnly;
        this.i = this;
        mc = new CommandBase() {

            @Override
            public String getCommandName() {
                return i.name;
            }

            @Override
            public String getCommandUsage(ICommandSender sender) {
                return i.usage;
            }

            @Override
            public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
                if (!SEPermissions.hasPermission(sender, i.permission)) {
                    sender.addChatMessage(new TextComponentString(SEColour.RED + "You don't have enough permissions to perform this command!"));
                    return;
                }
                if (i.argsLength > args.length) {
                    sender.addChatMessage(new TextComponentString(SEColour.RED + "Invalid Arguments: " + i.usage));
                }
                if (i.playerOnly) i.call(server, getCommandSenderAsPlayer(sender), args);
                else i.call(server, sender, args);
            }
        };
    }

    public void call(MinecraftServer s, EntityPlayerMP p, String[] args) {

    }


    public void call(MinecraftServer s, ICommandSender p, String[] args) {

    }
}
