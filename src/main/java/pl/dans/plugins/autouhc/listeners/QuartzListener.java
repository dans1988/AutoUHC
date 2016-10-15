/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.dans.plugins.autouhc.listeners;

import java.util.Date;
import java.util.Random;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import pl.dans.plugins.autouhc.AutoUHC;

/**
 *
 * @author Dans
 */
public class QuartzListener implements Listener {
    private static final int XP_DROP_CHANCE = 30;
    private static final int MAX_CHANCE = 100;
    
    private final AutoUHC autoUHC;
    private final Random random;
    
    public QuartzListener(AutoUHC autoUHC){
        random = new Random(new Date().getTime());
        this.autoUHC = autoUHC;
    }
    
    @EventHandler(ignoreCancelled = true)
    public void onBreakQuartz(final BlockBreakEvent event) {
        
        if(Material.QUARTZ_ORE.equals(event.getBlock().getType())) {
            
            int randomInt = random.nextInt(MAX_CHANCE);
            if (randomInt > XP_DROP_CHANCE) {
                event.setExpToDrop(0);
            }
            
        }
        
    }
}
