package pro.heartnet.heartevents.commands.essentials;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import pro.heartnet.heartstaff.utils.other.CC;

public class KMSCommand implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onCommand(PlayerCommandPreprocessEvent event) {
        String command = event.getMessage();
        Player player = event.getPlayer();

        if (command.equalsIgnoreCase("/kms") || command.equalsIgnoreCase("/killme") || command.equalsIgnoreCase("/suicide")) {
            event.setCancelled(true);
            player.setLastDeathLocation(player.getLocation());
            player.setHealth(0);
            player.setKiller(null);
            Bukkit.broadcast(CC.translateComponent(player.getName() + "&r found a short rope and a tall ladder"));
        }

    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        var killer = event.getEntity().getKiller();
        if (killer == null) {
            event.deathMessage(Component.empty());
            event.setShouldDropExperience(false);
            event.setDroppedExp(0);
        }
    }
}
