package net.diversionmc.ServerEssentials.managment;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Loader;
import scala.actors.threadpool.Arrays;

import java.io.File;
import java.util.List;

public enum SERoles {

    MEMBER("member", "Member", SEColour.LIGHT_GREY),
    MODERATOR("mod", "Mod", SEColour.DARK_GREEN),
    WIZARD("wizard", "Modding Wizard", SEColour.GREEN),
    ADMIN("admin", "Admin", SEColour.RED);

    public static Configuration roles;

    String id;
    String prefix;
    SEColour colour;

    SERoles(String id, String prefix, SEColour colour) {
        this.id = id;
        this.prefix = prefix;
        this.colour = colour;
    }


    public static void loadConfig() {
        File file = new File(Loader.instance().getConfigDir() + File.separator + "ServerEssentials", "roles.cfg");
        file.mkdir();
        roles = new Configuration(file);
        roles.load();
        roles.save();
    }

    public static SERoles getRole(EntityPlayerMP p) {
        {
            String[] membersRaw = roles.getCategory("mod").get("members").getStringList();
            List<String> members = Arrays.asList(membersRaw);
            if (members.contains(p.getUniqueID())) return MODERATOR;
        }
        {
            String[] membersRaw = roles.getCategory("wizard").get("members").getStringList();
            List<String> members = Arrays.asList(membersRaw);
            if (members.contains(p.getUniqueID())) return WIZARD;
        }
        {
            String[] membersRaw = roles.getCategory("admin").get("members").getStringList();
            List<String> members = Arrays.asList(membersRaw);
            if (members.contains(p.getUniqueID())) return ADMIN;
        }
        return MEMBER;
    }


}
