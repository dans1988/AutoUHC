/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.dans.plugins.autouhc.listeners;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

/**
 *
 * @author Dans
 */
public class PigmenSpawnListener implements Listener {
    
    @EventHandler
    public void onZombiePigmanSpawn(final CreatureSpawnEvent event) {
        if (EntityType.PIG_ZOMBIE.equals(event.getEntityType())) {
            event.setCancelled(true);
            World world = event.getEntity().getLocation().getWorld();
            
            Location newLocation = event.getEntity().getLocation();
            newLocation.setY(newLocation.getBlockY() + 2);
            
            world.spawnEntity(event.getEntity().getLocation(), EntityType.GHAST);
        }
    }
}
