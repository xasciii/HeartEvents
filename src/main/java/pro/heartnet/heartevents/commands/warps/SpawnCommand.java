package pro.heartnet.heartevents.commands.warps;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import pro.heartnet.heartevents.HeartEvents;
import pro.heartnet.heartstaff.utils.other.CC;

public class SpawnCommand extends BaseCommand {
    @CommandAlias("spawn")
    public void spawn(Player player){
        player.teleport(new Location(Bukkit.getWorld(HeartEvents.instance.config.getString("spawn.world")),
                HeartEvents.instance.config.getDouble("spawn.location-x"),
                HeartEvents.instance.config.getDouble("spawn.location-y"),
                HeartEvents.instance.config.getDouble("spawn.location-z"),
                HeartEvents.instance.config.getInt("spawn.location-yaw"),
                HeartEvents.instance.config.getInt("spawn.location-pitch")
        ));
    }
    @CommandAlias("setspawn")
    @CommandPermission("heartevents.command.setspawn")
    public void setSpawn(Player player){
        HeartEvents.instance.config.set("spawn.world", player.getWorld().getName());
        HeartEvents.instance.config.set("spawn.location-x", player.getLocation().getX());
        HeartEvents.instance.config.set("spawn.location-y", player.getLocation().getY());
        HeartEvents.instance.config.set("spawn.location-z", player.getLocation().getZ());
        HeartEvents.instance.config.set("spawn.location-yaw", player.getLocation().getYaw());
        HeartEvents.instance.config.set("spawn.location-pitch", player.getLocation().getPitch());
        HeartEvents.instance.config.save();
        player.sendMessage(CC.translate("&aYou've updated the spawn location."));
    }
}
