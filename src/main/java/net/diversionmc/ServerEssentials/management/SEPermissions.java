package net.diversionmc.ServerEssentials.management;

import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.common.Loader;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class SEPermissions {

    public static Configuration perms;

    public static void loadConfig() {
        File file = new File(Loader.instance().getConfigDir() + File.separator + "ServerEssentials", "permissions.cfg");
        perms = new Configuration(file);
        perms.load();
        ConfigCategory perm = perms.getCategory("permissions");
        if (!perm.containsKey("member"))
            perm.put("member", new Property("member", new String[]{"default", "permissions"}, Property.Type.STRING));
        if (!perm.containsKey("mod"))
            perm.put("mod", new Property("mod", new String[]{"default", "permissions"}, Property.Type.STRING));
        if (!perm.containsKey("wizard"))
            perm.put("wizard", new Property("wizard", new String[]{"default", "permissions"}, Property.Type.STRING));
        if (!perm.containsKey("admin"))
            perm.put("admin", new Property("admin", new String[]{"default", "permissions"}, Property.Type.STRING));
        perms.save();
    }

    public static boolean hasPermission(ICommandSender p, String permission) {
        if (!(p instanceof EntityPlayerMP)) return true;
        return hasPermission(SERoles.getRole((EntityPlayerMP) p), permission);
    }

    public static boolean hasPermission(UUID u, String permission) {
        return hasPermission(SERoles.getRole(u), permission);
    }

    public static boolean hasPermission(SERoles r, String permission) {
        String[] userPermsRaw = perms.getCategory("permissions").get(r.id).getStringList();
        List<String> userPerms = Arrays.asList(userPermsRaw);
        if (userPerms.contains("*")) return true;
        if (userPerms.contains(permission.toLowerCase())) return true;
        return false;
    }

    public static void givePermission(SERoles r, String permission) {
        String[] userPermsRaw = perms.getCategory("permissions").get(r.id).getStringList();
        List<String> userPerms = new ArrayList<>(Arrays.asList(userPermsRaw));
        if (!userPerms.contains(permission.toLowerCase())) userPerms.add(permission.toLowerCase());
        perms.getCategory("permissions").put(r.id, new Property(r.id, userPerms.toArray(new String[]{}), Property.Type.STRING));
    }

    public static void takePermission(SERoles r, String permission){
        String[] userPermsRaw = perms.getCategory("permissions").get(r.id).getStringList();
        List<String> userPerms = new ArrayList<>(Arrays.asList(userPermsRaw));
        if (userPerms.contains(permission.toLowerCase())) userPerms.remove(permission.toLowerCase());
        perms.getCategory("permissions").put(r.id, new Property(r.id, userPerms.toArray(new String[]{}), Property.Type.STRING));
    }

}
