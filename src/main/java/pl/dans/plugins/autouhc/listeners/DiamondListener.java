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
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pl.dans.plugins.autouhc.AutoUHC;

/**
 *
 * @author Dans
 */
public class DiamondListener implements Listener {
    private final AutoUHC autoUHC;

    public DiamondListener(AutoUHC autoUHC) {
        this.autoUHC = autoUHC;
    }
    
    
    @EventHandler
    public void onPickUp(final PlayerPickupItemEvent playerPickupItemEvent) {


        if (playerPickupItemEvent.getItem().getItemStack().getType().equals(Material.DIAMOND)) {
            
            playerPickupItemEvent.setCancelled(true);
            playerPickupItemEvent.getItem().remove();

            Player player = playerPickupItemEvent.getPlayer();

            if (player != null) {
                player.sendMessage(ChatColor.RED + "No diamonds!");
            }
        }

    }
    
    @EventHandler
    public void onInventoryClick(final InventoryClickEvent inventoryClickEvent) {
        
        if (Material.DIAMOND.equals(inventoryClickEvent.getCurrentItem().getType())) {
            
            
            inventoryClickEvent.setCancelled(true);
            inventoryClickEvent.setCurrentItem(null);
            

            Player player = (Player)inventoryClickEvent.getWhoClicked();

            if (player != null) {
                player.sendMessage(ChatColor.RED + "No diamonds!");
            }
        }
        
    }
    
    
    @EventHandler
    public void onDiamondMine(final BlockBreakEvent event) {
        
        if (Material.DIAMOND_ORE.equals(event.getBlock().getType())) {
            
            event.setCancelled(true);
            
            ItemStack potato = new ItemStack(Material.POTATO_ITEM, 1);
            ItemMeta potatoMeta = potato.getItemMeta();
            potatoMeta.setDisplayName("Diamond Potato");
            potato.setItemMeta(potatoMeta);
            
            event.getBlock().getLocation().getWorld().dropItemNaturally(event.getBlock().getLocation(), potato);
            event.getBlock().setType(Material.AIR);
            
            

        }
        
    }
}
