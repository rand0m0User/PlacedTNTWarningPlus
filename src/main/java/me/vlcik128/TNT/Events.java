package me.vlcik128.TNT;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.TNTPrimeEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class Events implements Listener {

    private final ChatColor a = ChatColor.AQUA;
    private final ChatColor g = ChatColor.GOLD;
    private final ChatColor r = ChatColor.RED;
    private final ChatColor R = ChatColor.RED;

    private final List<Material> rails = new ArrayList<>(Arrays.asList(Material.ACTIVATOR_RAIL, Material.DETECTOR_RAIL, Material.POWERED_RAIL, Material.RAIL));
    private final String prefix = R + "[" + r + "T" + ChatColor.WHITE + "N" + r + "T" + R + "] ";

    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        Block b = e.getBlock();
        if (b.getType() == Material.TNT && b.getType() != Material.AIR) {
            Bukkit.broadcastMessage(String.format("%s%sPlayer %s%s %splaced a %sTNT %sblock!", prefix, g, a, e.getPlayer().getName(), g, r, g));
        }
    }

    @EventHandler
    public void onExplode(ExplosionPrimeEvent e) {
        if (e.getEntity().getType() == EntityType.MINECART_TNT) {
            Bukkit.broadcastMessage(String.format("%s%sthe %sTNT minecart %swas activated!", prefix, g, a, g));
        }
        if (e.getEntity().getType() == EntityType.ENDER_CRYSTAL) {
            Bukkit.broadcastMessage(String.format("%s%sthe %sEnd Crystal %swas activated!", prefix, g, a, g));
        }
    }

    @EventHandler
    public void onTNTPrime(TNTPrimeEvent e) { //migrate old message to TNTPrimeEvent
        switch (e.getCause()) {
            case PLAYER:
                //if a player is trying to light tnt
                if (e.getPrimingEntity().getType() == EntityType.PLAYER) {
                    Player p = (Player) e.getPrimingEntity();
                    Bukkit.broadcastMessage(String.format("%s%sPlayer %s%s %sactivated the TNT!", prefix, g, a, p.getName(), g));
                }
                break;
            case REDSTONE:
                Bukkit.broadcastMessage(String.format("%s%sa %sRedstone signal %sactivated the TNT!", prefix, g, a, g));
                break;
            case PROJECTILE:
                Bukkit.broadcastMessage(String.format("%s%sa %sFlaming arrow %sactivated the TNT!", prefix, g, a, g));
        }
    }

    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        Block b = e.getClickedBlock();
        ItemStack is = e.getItem();
        if (is != null && b != null) { //null checks
            if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                Material mat = is.getType();
                //if player is trying to place a tnt minecart on rails
                if (mat == Material.TNT_MINECART && rails.contains(b.getType())) {
                    Bukkit.broadcastMessage(String.format("%s%sPlayer %s%s %splaced a %sTNT minecart%s!", prefix, g, a, e.getPlayer().getName(), g, r, g));
                }
                //if player is trying to place an end crystal
                if (mat == Material.END_CRYSTAL && (b.getType() == Material.OBSIDIAN || b.getType() == Material.BEDROCK)) {
                    Bukkit.broadcastMessage(String.format("%s%sPlayer %s%s %splaced a %sEnd Crystal%s!", prefix, g, a, e.getPlayer().getName(), g, r, g));

                }
            }
        }
    }
}
