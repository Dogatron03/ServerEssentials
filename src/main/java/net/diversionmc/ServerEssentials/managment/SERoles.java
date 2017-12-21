package net.diversionmc.ServerEssentials.managment;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.common.Loader;
import scala.actors.threadpool.Arrays;

import java.io.File;
import java.util.List;
import java.util.UUID;

public enum SERoles {

    MEMBER("member", "Member", SEColour.LIGHT_GREY),
    MODERATOR("mod", "Mod", SEColour.DARK_GREEN),
    WIZARD("wizard", "Modding Wizard", SEColour.GREEN),
    ADMIN("admin", "Admin", SEColour.RED);

    public static Configuration roles;

    public String id;
    public String prefix;
    public SEColour colour;

    SERoles(String id, String prefix, SEColour colour) {
        this.id = id;
        this.prefix = prefix;
        this.colour = colour;
    }

    public static SERoles getById(String id){
        return ((List<SERoles>)Arrays.asList(values())).stream().filter(r -> r.id.equalsIgnoreCase(id)).findFirst().orElse(null);
    }


    public static void loadConfig() {
        File file = new File(Loader.instance().getConfigDir() + File.separator + "ServerEssentials", "roles.cfg");
        roles = new Configuration(file);
        roles.load();
        ConfigCategory mod = roles.getCategory("mod");
        if(!mod.containsKey("members")) mod.put("members", new Property("members", new String[]{"'069a79f4-44e9-4726-a5be-fca90e38aaf5'"}, Property.Type.STRING));
        ConfigCategory wizard = roles.getCategory("wizard");
        if(!wizard.containsKey("members")) wizard.put("members", new Property("members", new String[]{"'61699b2e-d327-4a01-9f1e-0ea8c3f06bc6'"}, Property.Type.STRING));
        ConfigCategory admin = roles.getCategory("admin");
        if(!admin.containsKey("members")) admin.put("members", new Property("members", new String[]{"'696a82ce-41f4-4b51-aa31-b8709b8686f0'"}, Property.Type.STRING));
        roles.save();
    }

    public static SERoles getRole(EntityPlayerMP p) {
        {
            String[] membersRaw = roles.getCategory("mod").get("members").getStringList();
            List<String> members = Arrays.asList(membersRaw);
            if (members.contains(p.getUniqueID().toString())) return MODERATOR;
        }
        {
            String[] membersRaw = roles.getCategory("wizard").get("members").getStringList();
            List<String> members = Arrays.asList(membersRaw);
            if (members.contains(p.getUniqueID().toString())) return WIZARD;
        }
        {
            String[] membersRaw = roles.getCategory("admin").get("members").getStringList();
            List<String> members = Arrays.asList(membersRaw);
            if (members.contains(p.getUniqueID().toString())) return ADMIN;
        }
        return MEMBER;
    }

    public static SERoles getRole(UUID u) {
        {
            String[] membersRaw = roles.getCategory("mod").get("members").getStringList();
            List<String> members = Arrays.asList(membersRaw);
            if (members.contains(u.toString())) return MODERATOR;
        }
        {
            String[] membersRaw = roles.getCategory("wizard").get("members").getStringList();
            List<String> members = Arrays.asList(membersRaw);
            if (members.contains(u.toString())) return WIZARD;
        }
        {
            String[] membersRaw = roles.getCategory("admin").get("members").getStringList();
            List<String> members = Arrays.asList(membersRaw);
            if (members.contains(u.toString())) return ADMIN;
        }
        return MEMBER;
    }

    public static ConfigCategory getConfigCategory(SERoles role){
        switch(role){
            case MODERATOR: return roles.getCategory("mod");
            case WIZARD: return roles.getCategory("wizard");
            case ADMIN: return roles.getCategory("admin");
        }
        return null;
    }


}
