package pl.dans.plugins.autouhc;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.scheduler.BukkitRunnable;

public class AutoUHCTask extends BukkitRunnable {

    private int elapsed;
    private final CommandSender sender;
    private final TimerConfig timerConfig;

    public AutoUHCTask(int elapsed, CommandSender sender, TimerConfig timerConfig) {
        this.elapsed = elapsed;
        this.sender = sender;
        this.timerConfig = timerConfig;
    }

    

    public void run() {

        if (elapsed == -6) {
            Bukkit.getServer().broadcastMessage(getMessageStart() + "Starting in...");
            Bukkit.getServer().getWorld("world").setTime(0);
            Bukkit.getServer().dispatchCommand(sender, "pvp global off");
            Bukkit.getServer().dispatchCommand(sender, "freeze");
            Bukkit.getServer().getWorld("world").setGameRuleValue("doMobSpawning", "false");
        } else if (elapsed == -5) {
            Bukkit.getServer().broadcastMessage(getMessageStart() + "5");
            Bukkit.getServer().dispatchCommand(sender, "feed *");
        } else if (elapsed == -4) {
            Bukkit.getServer().broadcastMessage(getMessageStart() + "4");
            Bukkit.getServer().dispatchCommand(sender, "heal *");
        } else if (elapsed == -3) {
            Bukkit.getServer().broadcastMessage(getMessageStart() + "3");
            Bukkit.getServer().dispatchCommand(sender, "ci *");
        } else if (elapsed == -2) {
            Bukkit.getServer().broadcastMessage(getMessageStart() + "2");
            Bukkit.getServer().dispatchCommand(sender, "butcher");
        } else if (elapsed == -1) {
            Bukkit.getServer().getWorld("world").setGameRuleValue("doDaylightCycle", "true");
            Bukkit.getServer().broadcastMessage(getMessageStart() + "1");
        } else if (elapsed == 0) {
            Bukkit.getServer().broadcastMessage(getMessageStart() + "GO!");
            Bukkit.getServer().getWorld("world").setTime(0);
            //Bukkit.getServer().dispatchCommand(sender, "worldborder set 2500");
            Bukkit.getServer().dispatchCommand(sender, timerConfig.getStartupCommand());
            
        } else if (elapsed == 5) {
            Bukkit.getServer().broadcastMessage(getMessageStart() + "To see how much time is left, use the /timeleft command");
        } else if (elapsed == 6) {
            Bukkit.getServer().dispatchCommand(sender, "butcher");
            Bukkit.getServer().getWorld("world").setGameRuleValue("doMobSpawning", "true");
        } else if (elapsed == 10) {
            Bukkit.getServer().broadcastMessage(getMessageStart() + "Final heal!");
            Bukkit.getServer().dispatchCommand(sender, "heal *");
            Bukkit.getServer().dispatchCommand(sender, timerConfig.getDelayedCommand());
        } else if (elapsed == minutesToSeconds(10)) {
            Bukkit.getServer().broadcastMessage(getMessageStart() + "Setting moles in 5 minutes!");
            Bukkit.getServer().broadcastMessage(getMessageStart() + "Remember to clear the top row of your inventory!");   
        } else if (elapsed == minutesToSeconds(14)) {
            Bukkit.getServer().broadcastMessage(getMessageStart() + "Setting moles in 1 minute!");
            Bukkit.getServer().broadcastMessage(getMessageStart() + "Remember to clear the top row of your inventory!");   
        }  else if (elapsed == minutesToSeconds(timerConfig.getPvpTime())) {
            Bukkit.getServer().broadcastMessage(getMessageStart() + "PvP ON!");
            Bukkit.getServer().dispatchCommand(sender, "pvp global on");
            Bukkit.getServer().dispatchCommand(sender, "setMoles");
            
        } else if (elapsed == minutesToSeconds(timerConfig.getPermadayTime())) {
            Bukkit.getServer().broadcastMessage(getMessageStart() + "Permaday!");
            Bukkit.getServer().getWorld("world").setGameRuleValue("doDaylightCycle", "false");
            Bukkit.getServer().getWorld("world").setTime(6000);
            
        }
        else if (elapsed == minutesToSeconds(timerConfig.getMeetupTime()) - minutesToSeconds(5)) {
            Bukkit.getServer().broadcastMessage(getMessageStart() + "Meetup in 5 minutes!");
            //Bukkit.getServer().broadcastMessage(getMessageStart() + "The border will shrink to a 200x200 area in the span of 15 minutes as soon as the meetup is called!");
            //Bukkit.getServer().broadcastMessage(getMessageStart() + "If you are close to the border, consider moving closer to 0.0 now.");

        } else if (elapsed == minutesToSeconds(timerConfig.getMeetupTime())) {

            Bukkit.getServer().broadcastMessage(getMessageStart() + "Meetup!");
            Bukkit.getServer().broadcastMessage(getMessageStart() + "Go to x = 0, z = 0 now! Don't stop for anythig except for battles!");
            Bukkit.getServer().broadcastMessage(getMessageStart() + "You will be disqualified if you constantly run away from a 200x200 area around 0.0!");
            //Bukkit.getServer().broadcastMessage(getMessageStart() + "During the next 15 minutes, the border will shrink into a 200x200 area!");
            //Bukkit.getServer().broadcastMessage(getMessageStart() + "You don't have to go to 0.0 immediately - the border is your limit, just stay on the surface!");
            //Bukkit.getServer().dispatchCommand(sender, "worldborder set 200 900");

        }

        elapsed++;
    }

    private String getMessageStart() {
        return ChatColor.RED + "[" + ChatColor.LIGHT_PURPLE + "Sperlo's UHC"
                + ChatColor.RED + "] " + ChatColor.YELLOW;
    }

    private int minutesToSeconds(int minutes) {
        return minutes * 60;
    }

    public int getElapsed() {
        return elapsed;
    }

    public void setElapsed(int elapsed) {
        this.elapsed = elapsed;
    }
    



    
}
