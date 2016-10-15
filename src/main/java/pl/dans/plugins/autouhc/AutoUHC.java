package pl.dans.plugins.autouhc;

import java.util.logging.Level;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;
import pl.dans.plugins.autouhc.listeners.BlazePowderListener;
import pl.dans.plugins.autouhc.listeners.BookShelfListener;
import pl.dans.plugins.autouhc.listeners.GlowstoneListener;
import pl.dans.plugins.autouhc.listeners.HorseHealingListener;
import pl.dans.plugins.autouhc.listeners.HorseListener;
import pl.dans.plugins.autouhc.listeners.QuartzListener;

public class AutoUHC extends JavaPlugin implements CommandExecutor {

    private static final int SECONDS_IN_A_MINUTE = 60;
    
    private TimerConfig timerConfig;
    
    @Override
    public void onEnable() {
        getLogger().log(Level.INFO, "{0}onEnable", ChatColor.RED);
        readConfig();
        registerEvents();
    }

    @Override
    public void onDisable() {
        getLogger().log(Level.INFO, "{0}onDisable", ChatColor.RED);
    }

    private AutoUHCTask autoUHCTask;
    private BukkitTask task;
    private boolean running = false;

    @Override
    public boolean onCommand(final CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().compareToIgnoreCase("uhcstart") == 0) {

            if (running) {
                sender.sendMessage(ChatColor.RED + "Task is already running!");
                return true;
            }

            autoUHCTask = new AutoUHCTask(-6, sender, timerConfig);
            task = autoUHCTask.runTaskTimer(this, 0, 20);
            running = true;

        } else if (command.getName().compareToIgnoreCase("uhcset") == 0) {

            int elapsed = Integer.parseInt(args[0]);

            if (autoUHCTask == null) {
                autoUHCTask = new AutoUHCTask(elapsed, sender, timerConfig);
            } else {
                autoUHCTask.setElapsed(elapsed);
            }

        } else if (command.getName().compareToIgnoreCase("uhcresume") == 0) {

            if (running) {
                sender.sendMessage(ChatColor.RED + "Task is already running!");
                return true;
            }

            int elapsed = autoUHCTask.getElapsed();

            autoUHCTask = autoUHCTask = new AutoUHCTask(elapsed, sender, timerConfig);
            task = autoUHCTask.runTaskTimer(this, 0, 20);
            running = true;
        } else if (command.getName().compareToIgnoreCase("uhcstop") == 0) {

            if (!running) {
                sender.sendMessage(ChatColor.RED + "Task is not running!");
                return true;
            }
            task.cancel();
            running = false;

        } else if (command.getName().compareToIgnoreCase("timeleft") == 0) {

            if (!running) {
                sender.sendMessage(ChatColor.RED + "UHC timer is not running!");
                return true;
            }

            sender.sendMessage(getPvPTimeMessage());
            sender.sendMessage(getPermadayMessage());
            sender.sendMessage(getMeetupMessage());
        }

        return true;
    }

    private String getPvPTimeMessage() {
        int pvptime = getTimeUntilPvP();

        String message;
        if (pvptime >= 0) {
            int seconds = pvptime % 60;
            int minutes = (pvptime - seconds) / 60;
            message = ChatColor.GOLD + "PvP/iPvP/Moles: " + ChatColor.GRAY + "in " + minutes + " minutes and " + seconds + " seconds";
        } else {
            message = ChatColor.GOLD + "PvP/iPvP/Moles: " + ChatColor.YELLOW + "ON!";
        }
        return message;
    }

    private int getTimeUntilPvP() {
        return timerConfig.getPvpTime() * SECONDS_IN_A_MINUTE  - autoUHCTask.getElapsed();
    }

    private String getPermadayMessage() {
        int timeleft = getTimeUntilPermaday();

        String message;
        if (timeleft >= 0) {
            int seconds = timeleft % 60;
            int minutes = (timeleft - seconds) / 60;
            message = ChatColor.GOLD + "Permaday: " + ChatColor.GRAY + "in " + minutes + " minutes and " + seconds + " seconds";
        } else {
            message = ChatColor.GOLD + "Permaday: " + ChatColor.YELLOW + "ON!";
        }
        return message;
    }

    private int getTimeUntilPermaday() {
        return timerConfig.getPermadayTime() * SECONDS_IN_A_MINUTE  - autoUHCTask.getElapsed();
    }

    private String getMeetupMessage() {
        int timeleft = getTimeUntilMeetup();

        String message;
        if (timeleft >= 0) {
            int seconds = timeleft % 60;
            int minutes = (timeleft - seconds) / 60;
            message = ChatColor.GOLD + "Meetup: " + ChatColor.GRAY + "in " + minutes + " minutes and " + seconds + " seconds";
        } else {
            message = ChatColor.GOLD + "Meetup: " + ChatColor.YELLOW + "IN PROGRESS!";
        }
        return message;
    }

    private int getTimeUntilMeetup() {
        return timerConfig.getMeetupTime() * SECONDS_IN_A_MINUTE - autoUHCTask.getElapsed();
    }

    private void readConfig() {
        saveDefaultConfig();

        FileConfiguration configuration = getConfig();

        int pvpTime = configuration.getInt("pvp");
        int permadayTime = configuration.getInt("permaday");
        int meetupTime = configuration.getInt("meetup");
        String delayedCommand = configuration.getString("delayedCommand");
        String startupCommand = configuration.getString("startupCommand");

        timerConfig = TimerConfig.newBuilder()
                .setDelayedCommand(delayedCommand)
                .setMeetupTime(meetupTime)
                .setPermadayTime(permadayTime)
                .setPvpTime(pvpTime)
                .setStartupCommand(startupCommand)
                .build();

    }

    private void registerEvents() {
        getServer().getPluginManager().registerEvents(new BlazePowderListener(this), this);
        getServer().getPluginManager().registerEvents(new GlowstoneListener(this), this);
        getServer().getPluginManager().registerEvents(new QuartzListener(this), this);
        getServer().getPluginManager().registerEvents(new HorseHealingListener(this), this);
        getServer().getPluginManager().registerEvents(new BookShelfListener(this), this);
        getServer().getPluginManager().registerEvents(new HorseListener(this), this);
    }

}
