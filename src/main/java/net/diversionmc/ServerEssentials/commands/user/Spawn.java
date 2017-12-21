package net.diversionmc.ServerEssentials.commands.user;

import net.diversionmc.ServerEssentials.management.SEColour;
import net.diversionmc.ServerEssentials.management.SECommand;
import net.diversionmc.ServerEssentials.management.SEConfig;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.common.config.ConfigCategory;

public class Spawn extends SECommand {

    public Spawn() {
        super("spawn", "command.spawn", "/spawn", 0, true);
    }

    public void call(MinecraftServer s, EntityPlayerMP p, String[] args) {
        ConfigCategory l = SEConfig.config.getCategory("spawn");
        p.moveToBlockPosAndAngles(new BlockPos(l.get("x").getDouble(), l.get("y").getDouble(), l.get("z").getDouble()), Float.parseFloat(l.get("yaw").getString()), Float.parseFloat(l.get("pitch").getString()));
        p.addChatMessage(new TextComponentString(SEColour.GREEN + "You have been teleported to spawn!"));
    }

}
