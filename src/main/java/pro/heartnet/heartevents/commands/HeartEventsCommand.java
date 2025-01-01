package pro.heartnet.heartevents.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.*;
import org.bukkit.command.CommandSender;
import pro.heartnet.heartevents.HeartEvents;
import pro.heartnet.heartstaff.utils.other.CC;
import pro.heartnet.heartstaff.utils.other.Stopwatch;

import java.util.concurrent.TimeUnit;

@CommandAlias("events")
@CommandPermission("heartevents.command.heartevents")
public class HeartEventsCommand extends BaseCommand {
    @HelpCommand
    @Syntax("[query]")
    public void help(CommandSender sender, CommandHelp help){
        help.showHelp();
    }

    @Subcommand("reload")
    @CommandPermission("heartevents.command.heartevents.subcommand.reload")
    @Description("Reload HeartEvents' configuration")
    public void reload(CommandSender sender){
        Stopwatch stopwatch = new Stopwatch();
        stopwatch.start();
        HeartEvents.instance.reloadConfig();
        stopwatch.stop();
        sender.sendMessage(CC.translate("&aBox's configuration reloaded successfully in &f&n" + stopwatch.elapsedTime(TimeUnit.MILLISECONDS) + "ms&a."));
    }
}
