package pl.dans.plugins.autouhc.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.VehicleEnterEvent;
import pl.dans.plugins.autouhc.AutoUHC;


public class HorseListener implements Listener {

    private final AutoUHC autoUHC;

    public HorseListener(AutoUHC autoUHC) {
        this.autoUHC = autoUHC;
    }

    @EventHandler
    public void onMount(final VehicleEnterEvent e) {

        if (((e.getVehicle() instanceof Horse)) && ((e.getEntered() instanceof Player))) {
            
            ((Player) e.getEntered()).sendMessage(ChatColor.RED + "Horse mounting disabled!!");
            e.setCancelled(true);
        }
    }
}
