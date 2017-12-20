package net.diversionmc.ServerEssentials.managment;

import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.common.Loader;
import scala.actors.threadpool.Arrays;

import java.io.File;

public class SEConfig {

    public static Configuration config;

    public static void loadConfig(){
        File file = new File(Loader.instance().getConfigDir() + File.separator + "ServerEssentials", "config.cfg");
        config = new Configuration(file);
        config.load();
        ConfigCategory spawn = config.getCategory("spawn");
        if(!spawn.containsKey("x")) spawn.put("x", new Property("x", "0", Property.Type.INTEGER));
        if(!spawn.containsKey("y")) spawn.put("y", new Property("y", "128", Property.Type.INTEGER));
        if(!spawn.containsKey("z")) spawn.put("z", new Property("z", "0", Property.Type.INTEGER));
        if(!spawn.containsKey("yaw")) spawn.put("yaw", new Property("yaw", "0", Property.Type.INTEGER));
        if(!spawn.containsKey("pitch")) spawn.put("pitch", new Property("pitch", "0", Property.Type.INTEGER));
        spawn.setComment("Location used by /spawn");
        spawn.setPropertyOrder(Arrays.asList(new String[]{"x","y","z","yaw","pitch"}));
        config.save();
    }

}
