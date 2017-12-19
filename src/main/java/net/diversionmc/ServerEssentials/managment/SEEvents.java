package net.diversionmc.ServerEssentials.managment;

import net.diversionmc.ServerEssentials.ServerEssentials;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class SEEvents {

    @SubscribeEvent(priority = EventPriority.LOWEST, receiveCanceled = true)
    public static void onChat(ServerChatEvent e) {
        e.setCanceled(true);
        SERoles role = SERoles.getRole(e.getPlayer());
        String prefix = role.colour + role.prefix + SEColour.DARK_GREY + " | " + e.getUsername() + SEColour.AQUA + " ≫ " + SEColour.LIGHT_GREY;
        String message = SEColour.translate(e.getMessage());
        ServerEssentials.server.getPlayerList().getPlayerList().forEach(p -> p.addChatMessage(new TextComponentString(prefix + message)));
        System.out.println(SEColour.strip(prefix + message));
    }

}
