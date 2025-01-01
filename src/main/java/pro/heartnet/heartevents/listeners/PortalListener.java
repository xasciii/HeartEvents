package pro.heartnet.heartevents.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.world.PortalCreateEvent;

public class PortalListener implements Listener {
    @EventHandler
    public void onPortal(PlayerPortalEvent event){
        event.setCancelled(true);
    }
    @EventHandler
    public void onPortal(PortalCreateEvent event){
        event.setCancelled(true);
    }
}
