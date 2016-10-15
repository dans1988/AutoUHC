/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.dans.plugins.autouhc.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryType;
import pl.dans.plugins.autouhc.AutoUHC;

/**
 *
 * @author Dans
 */
public class BlazePowderListener implements Listener {

    private final AutoUHC autoUHC;

    public BlazePowderListener(AutoUHC autoUHC) {
        this.autoUHC = autoUHC;
    }

    @EventHandler(ignoreCancelled = true)
    public void onBlazePowderBrewing(final InventoryClickEvent event) {

        boolean cancel = false;

        if (event.getClick().isShiftClick()
                && event.getCurrentItem().getType().equals(Material.BLAZE_POWDER)
                && InventoryType.BREWING.equals(event.getView().getType())) {
            cancel = true;
        }
        if (event.getCursor().getType().equals(Material.BLAZE_POWDER)
                && event.getSlotType().equals(InventoryType.SlotType.FUEL)
                && InventoryType.BREWING.equals(event.getView().getType())) {
            cancel = true;
        }
        
        if (event.getCursor().getType().equals(Material.BLAZE_POWDER)
                && event.getSlotType().equals(InventoryType.SlotType.FUEL)
                && InventoryType.BREWING.equals(event.getView().getType())) {
            cancel = true;
        }
        
        if (event.getClick().isKeyboardClick()
                && event.getClick().equals(ClickType.NUMBER_KEY)
                && event.getSlotType().equals(InventoryType.SlotType.FUEL)
                && InventoryType.BREWING.equals(event.getView().getType())) {
            
            int number = event.getHotbarButton();
            
            Player player = Bukkit.getPlayer(event.getWhoClicked().getName());
            
            
            if (player.getInventory().getItem(number) != null
                    && player.getInventory().getItem(number).getType().equals(Material.BLAZE_POWDER)) {
                cancel = true;
            }
            
        }
        
        

        event.setCancelled(cancel);

        if (cancel) {

            Player player = Bukkit.getPlayer(event.getWhoClicked().getName());

            if (player != null) {
                player.sendMessage(ChatColor.RED + "No strength pots, you naughty person!");
            }
        }

    }
    
    
    @EventHandler(ignoreCancelled = true)
    public void onBlazePowderDrag(final InventoryDragEvent event) {
        
        boolean cancel = false;
        
        if (event.getOldCursor().getType().equals(Material.BLAZE_POWDER)
                && InventoryType.BREWING.equals(event.getView().getType())) {
            cancel = true;
        }
        
        event.setCancelled(cancel);

        if (cancel) {

            Player player = Bukkit.getPlayer(event.getWhoClicked().getName());

            if (player != null) {
                player.sendMessage(ChatColor.RED + "No strength pots, you naughty person!");
            }
        }
    }
}
