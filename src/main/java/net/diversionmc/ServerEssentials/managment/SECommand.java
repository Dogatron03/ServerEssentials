package net.diversionmc.ServerEssentials.managment;

import net.diversionmc.ServerEssentials.ServerEssentials;
import net.diversionmc.ServerEssentials.commands.admin.Permissions;
import net.diversionmc.ServerEssentials.commands.user.Spawn;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static net.diversionmc.ServerEssentials.ServerEssentials.info;

public class SECommand {

    public static List<SECommand> commands = new ArrayList<>();

    public final String name;
    public final String permission;
    public final String usage;
    public final int argsLength;
    public final boolean playerOnly;
    public final String[] aliases;
    public final SECommand i;
    public final CommandBase mc;


    public SECommand(String name, String permission, String usage, int argsLength, boolean playerOnly, String... aliases) {
        this.name = name;
        this.permission = permission;
        this.usage = usage;
        this.argsLength = argsLength;
        this.playerOnly = playerOnly;
        this.aliases = aliases;
        this.i = this;
        commands.add(this);
        info("Registered Command: " + name);
        mc = new CommandBase() {

            public String getCommandName() {
                return i.name;
            }

            public String getCommandUsage(ICommandSender sender) {
                return i.usage;
            }

            public int getRequiredPermissionLevel() {
                return 0;
            }

            public List<String> getCommandAliases() {
                return Arrays.asList(i.aliases);
            }

            public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
                return true;
            }

            public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
                if (!SEPermissions.hasPermission(sender, i.permission) && sender instanceof EntityPlayerMP) {
                    sender.addChatMessage(new TextComponentString(SEColour.RED + "You don't have enough permissions to perform this command!"));
                    return;
                }
                if (i.argsLength > args.length) {
                    sender.addChatMessage(new TextComponentString(SEColour.RED + "Invalid Arguments: " + i.usage));
                    return;
                }
                if (i.playerOnly) i.call(server, getCSAsPl(sender), args);
                else i.call(server, sender, args);
            }

            public EntityPlayerMP getCSAsPl(ICommandSender sender){
                if(sender instanceof EntityPlayerMP) return (EntityPlayerMP) sender;
                throw new RuntimeException(SEColour.RED + "This command can only be ran by a player!");
            }

        };
    }

    public void call(MinecraftServer s, EntityPlayerMP p, String[] args) {

    }


    public void call(MinecraftServer s, ICommandSender p, String[] args) {

    }

    public static void loadCommands(){
        new Spawn();
        new Permissions();
    }
}
