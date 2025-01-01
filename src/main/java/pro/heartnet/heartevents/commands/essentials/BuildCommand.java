package pro.heartnet.heartevents.commands.essentials;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.session.Session;
import org.bukkit.entity.Player;
import pro.heartnet.heartstaff.utils.other.CC;

@CommandAlias("build|buildmode")
@CommandPermission("worldguard.region.bypass")
public class BuildCommand extends BaseCommand {
    @Default
    @CommandCompletion("on|off @empty")
    public void build(Player player, @Optional String toggle){
        Session session = WorldGuard.getInstance().getPlatform().getSessionManager().get(WorldGuardPlugin.inst().wrapPlayer(player));
        if(toggle != null) {
            if (toggle.equalsIgnoreCase("on")) {
                if (!session.hasBypassDisabled()) {
                    player.sendMessage(CC.translate(CC.HEX_LIGHT_RED_2 + "Your build mode is already enabled."));
                    return;
                }
                session.setBypassDisabled(false);
                player.sendMessage(CC.translate(CC.HEX_LIGHT_GREEN + "You've enabled your build mode."));
                return;
            }

            if (toggle.equalsIgnoreCase("off")) {
                if (session.hasBypassDisabled()) {
                    player.sendMessage(CC.translate(CC.HEX_LIGHT_RED_2 + "Your build mode is already disabled."));
                    return;
                }
                session.setBypassDisabled(true);
                player.sendMessage(CC.translate(CC.HEX_LIGHT_RED_2 + "You've disabled your build mode."));
                return;
            }
        }

        if(session.hasBypassDisabled()){
            session.setBypassDisabled(false);
            player.sendMessage(CC.translate(CC.HEX_LIGHT_GREEN + "You've enabled your build mode."));
            return;
        }
        session.setBypassDisabled(true);
        player.sendMessage(CC.translate(CC.HEX_LIGHT_RED_2 + "You've disabled your build mode."));
    }
}
