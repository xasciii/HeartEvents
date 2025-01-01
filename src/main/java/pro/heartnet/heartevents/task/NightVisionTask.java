package pro.heartnet.heartevents.task;

import org.bukkit.Bukkit;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import pro.heartnet.heartevents.HeartEvents;

public class NightVisionTask implements Runnable {
    @Override
    public void run() {
        Bukkit.getOnlinePlayers().forEach(players -> {
            boolean isNightVision = HeartEvents.instance.flatFileManager.getNightVision(players.getUniqueId());
            if(isNightVision){
                players.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, PotionEffect.INFINITE_DURATION, 9, false, false, false));
            } else {
                players.removePotionEffect(PotionEffectType.NIGHT_VISION);
            }
        });
    }
}
