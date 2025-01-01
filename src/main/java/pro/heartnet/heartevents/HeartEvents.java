package pro.heartnet.heartevents;

import co.aikar.commands.PaperCommandManager;
import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;
import pro.heartnet.heartevents.commands.HeartEventsCommand;
import pro.heartnet.heartevents.commands.essentials.BuildCommand;
import pro.heartnet.heartevents.commands.essentials.KMSCommand;
import pro.heartnet.heartevents.commands.essentials.NightVisionCommand;
import pro.heartnet.heartevents.commands.warps.SpawnCommand;
import pro.heartnet.heartevents.flatfile.FlatFileManager;
import pro.heartnet.heartevents.listeners.BuildListener;
import pro.heartnet.heartevents.listeners.JoinQuitListener;
import pro.heartnet.heartevents.listeners.PortalListener;
import pro.heartnet.heartevents.task.NightVisionTask;
import pro.heartnet.heartstaff.HeartStaff;
import pro.heartnet.heartstaff.utils.ConfigFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;

public final class HeartEvents extends JavaPlugin {

    public static HeartEvents instance;

    public FlatFileManager flatFileManager;

    public ConfigFile config;

    @Override
    public void onEnable() {
        instance = this;

        flatFileManager = new FlatFileManager();

        loadFiles();

        loadConfig();
        reloadConfig();

        setupCommands();
        registerListeners();

        setupSchedulers();
        setupWorlds();
    }

    public void setupCommands() {
        PaperCommandManager commandManager = HeartStaff.instance.commandManager;

        commandManager.enableUnstableAPI("help");

        Arrays.asList(
                new HeartEventsCommand(),
                new NightVisionCommand(),
                new BuildCommand(),
                new SpawnCommand()
        ).forEach(command -> commandManager.registerCommand(command, true));
    }

    public void registerListeners() {
        Arrays.asList(
                new JoinQuitListener(),
                new KMSCommand(),
                new BuildListener(),
                new PortalListener()
        ).forEach(listeners -> Bukkit.getServer().getPluginManager().registerEvents(listeners, this));
    }

    public void setupSchedulers() {
        BukkitScheduler scheduler = Bukkit.getServer().getScheduler();

        scheduler.scheduleSyncRepeatingTask(this, NightVisionTask::new, 0L, 2 * 20L);
    }

    public void setupWorlds() {
        Bukkit.getWorlds().forEach(worlds -> {
            worlds.setGameRule(GameRule.ANNOUNCE_ADVANCEMENTS, false);
            worlds.setGameRule(GameRule.SPAWN_RADIUS, 0);
        });
    }

    private void loadFiles() {
        try {
            config = new ConfigFile(this, "config.yml");
        } catch (IOException | InvalidConfigurationException e) {
            this.getLogger().log(Level.SEVERE, "=======================================================================================================");
            this.getLogger().log(Level.SEVERE, "There was an error while loading one or more of the files, please check for any configuration mistakes: ");
            e.printStackTrace();
            this.getLogger().log(Level.SEVERE, "=======================================================================================================");
        }
    }

    public void loadConfig() {
        instance.config.options().copyDefaults(true);
        saveConfig();
    }

    public void reloadConfig() {
        super.reloadConfig();

        saveDefaultConfig();
        config = instance.config;
        config.options().copyDefaults(true);
        saveConfig();
    }
}
