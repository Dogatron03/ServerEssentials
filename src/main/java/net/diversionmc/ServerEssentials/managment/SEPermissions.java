package net.diversionmc.ServerEssentials.managment;

import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.common.Loader;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class SEPermissions {

    public static Configuration perms;

    public static void loadConfig() {
        File file = new File(Loader.instance().getConfigDir() + File.separator + "ServerEssentials", "permissions.cfg");
        perms = new Configuration(file);
        perms.load();
        ConfigCategory perm = perms.getCategory("permissions");
        if(!perm.containsKey("member")) perm.put("member", new Property("member", new String[]{"default", "permissions"}, Property.Type.STRING));
        if(!perm.containsKey("mod")) perm.put("mod", new Property("mod", new String[]{"default", "permissions"}, Property.Type.STRING));
        if(!perm.containsKey("wizard")) perm.put("wizard", new Property("wizard", new String[]{"default", "permissions"}, Property.Type.STRING));
        if(!perm.containsKey("admin")) perm.put("admin", new Property("admin", new String[]{"default", "permissions"}, Property.Type.STRING));
        perms.save();
    }

    public static boolean hasPermission(ICommandSender p, String permission) {
        if(!(p instanceof EntityPlayerMP)) return true;
        String[] userPermsRaw = perms.getCategory("permissions").get(SERoles.getRole((EntityPlayerMP) p).id).getStringList();
        List<String> userPerms = Arrays.asList(userPermsRaw);
        if (userPerms.contains(permission)) return true;
        if (userPerms.contains("*")) return true;
        return false;
    }

}
