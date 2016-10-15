package pl.dans.plugins.autouhc.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;
import pl.dans.plugins.autouhc.AutoUHC;


public class HorseHealingListener implements Listener {
    private final AutoUHC autoUHC;

    public HorseHealingListener(AutoUHC autoUHC) {
        this.autoUHC = autoUHC;
    }
    
    @EventHandler(ignoreCancelled = true)
    public void onHorseHeal(final PlayerInteractEntityEvent event) {
        
        if (!EntityType.HORSE.equals(event.getRightClicked().getType())) {
            return;
        }
        
        ItemStack itemInHand = event.getPlayer().getItemInHand();
        if (Material.SUGAR.equals(itemInHand.getType())
                || Material.BREAD.equals(itemInHand.getType())
                || Material.WHEAT.equals(itemInHand.getType())
                || Material.GOLDEN_APPLE.equals(itemInHand.getType())
                || Material.GOLDEN_CARROT.equals(itemInHand.getType())
                || Material.APPLE.equals(itemInHand.getType())
                || Material.HAY_BLOCK.equals(itemInHand.getType())) {
            event.getPlayer().sendMessage(ChatColor.RED + "No horse healing, you bad person!");
            
            event.setCancelled(true);
        }
        

    }
}
