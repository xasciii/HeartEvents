package pro.heartnet.heartevents.listeners;

import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import de.myzelyam.api.vanish.VanishAPI;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import pro.heartnet.heartevents.HeartEvents;
import pro.heartnet.heartstaff.utils.other.CC;

public class JoinQuitListener implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();

        event.joinMessage(Component.empty());

        if(!VanishAPI.isInvisible(player)) {
            Bukkit.broadcast(CC.translateComponent("&7[" + CC.HEX_LIGHT_GREEN + "+&7] " + player.getName()));
        }

        HeartEvents.instance.flatFileManager.create(player);

        WorldGuard.getInstance().getPlatform().getSessionManager().get(WorldGuardPlugin.inst().wrapPlayer(player)).setBypassDisabled(true);
    }
    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        event.quitMessage(Component.empty());
    }
}
