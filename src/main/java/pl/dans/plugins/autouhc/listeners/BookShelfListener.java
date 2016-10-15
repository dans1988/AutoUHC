/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.dans.plugins.autouhc.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import pl.dans.plugins.autouhc.AutoUHC;

/**
 *
 * @author Dans
 */
public class BookShelfListener implements Listener {
    
    private final AutoUHC autoUHC;

    public BookShelfListener(AutoUHC autoUHC) {
        this.autoUHC = autoUHC;
    }
    
    @EventHandler(ignoreCancelled = true)
    public void onCraftBookshelf(final CraftItemEvent event) {
        
        if (Material.BOOKSHELF.equals(event.getCurrentItem().getType())) {
            
            Player player = (Player) event.getWhoClicked();
            
            player.sendMessage(ChatColor.RED + "Crafting bookselves is disabled!");
            event.setCancelled(true);
            
        }
    }
    
}
