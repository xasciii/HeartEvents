package pro.heartnet.heartevents.commands.essentials;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import pro.heartnet.heartevents.HeartEvents;
import pro.heartnet.heartstaff.utils.other.CC;

@CommandAlias("nightvision|nv")
public class NightVisionCommand extends BaseCommand {
    @Default
    public void nightVision(Player player){
        if(!HeartEvents.instance.flatFileManager.getNightVision(player.getUniqueId())){
            player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, PotionEffect.INFINITE_DURATION, 9, false, false, false));
            HeartEvents.instance.flatFileManager.setNightVision(player.getUniqueId(), true);
            player.sendMessage(CC.translate(CC.HEX_LIGHT_GREEN + "You've enabled your night vision."));
        } else {
            player.removePotionEffect(PotionEffectType.NIGHT_VISION);
            HeartEvents.instance.flatFileManager.setNightVision(player.getUniqueId(), false);
            player.sendMessage(CC.translate(CC.HEX_LIGHT_RED_2 + "You've disabled your night vision."));
        }
    }
}
