package net.diversionmc.ServerEssentials.management;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.common.Loader;

import java.io.File;
import java.util.UUID;

public class SEUUID {

    public static Configuration uuid;
    public static ConfigCategory players;

    public static void loadConfig(){
        File f = new File(Loader.instance().getConfigDir() + File.separator + "ServerEssentials", "uuid.cfg");
        uuid = new Configuration(f);
        uuid.load();
        players = uuid.getCategory("players");
        players.setComment("Store of player UserNames -> UUID");
    }

    public static UUID get(String name){
        if(!players.containsKey(name)) return null;
        return UUID.fromString(players.get(name).getString());
    }

    public static void addPlayer(EntityPlayerMP p){
        if(!players.containsKey(p.getName())) players.put(p.getName(), new Property(p.getName(), p.getUniqueID().toString(), Property.Type.STRING));
        uuid.save();
    }


}
