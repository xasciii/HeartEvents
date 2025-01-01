package pro.heartnet.heartevents.flatfile;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import pro.heartnet.heartevents.HeartEvents;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

public class FlatFileManager {

    public FlatFileManager() {
        createBase();
    }

    public void create(Player player) {
        File file = getFile(player);
        if (file.exists()) return;
        try {
            if (!file.exists()) {
                file.createNewFile();
                YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
                config.set("nightVision", false);
                config.save(file);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean getNightVision(UUID uuid) {
        File file = getFile(uuid);
        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
        return config.getBoolean("nightVision");
    }
    public boolean getNightVision(String uuid) {
        File file = getFile(uuid);
        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
        return config.getBoolean("nightVision");
    }

    public void setNightVision(UUID uuid, boolean nightVision) {
        try {
            File file = getFile(uuid);
            YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
            config.set("nightVision", nightVision);
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean hasAccount(UUID uuid) {
        return getFile(uuid).exists();
    }

    public ArrayList<String> getPlayers() {
        File f = new File(HeartEvents.instance.getDataFolder() + File.separator + "playerdata");
        File[] files = f.getAbsoluteFile().listFiles();
        ArrayList<String> list = new ArrayList<>();
        for (File fi : Objects.requireNonNull(files)) {
            String name = fi.getName().replace(".yml", "");
            list.add(name);
        }
        return list;
    }

    private File getFile(Player player) {
        return getFile(player.getUniqueId());
    }

    private File getFile(UUID uuid) {
        return new File(HeartEvents.instance.getDataFolder() + File.separator + "playerdata" + File.separator + uuid + ".yml");
    }

    private File getFile(String nameuuid) {
        return new File(HeartEvents.instance.getDataFolder() + File.separator + "playerdata" + File.separator + nameuuid + ".yml");
    }

    private void createBase() {
        File file = new File(HeartEvents.instance.getDataFolder() + File.separator + "playerdata");
        if (!file.exists()) {
            file.mkdirs();
        }
    }
}
