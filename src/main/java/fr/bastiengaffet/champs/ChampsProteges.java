package fr.bastiengaffet.champs;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

/** Empeche de transformer une terre labouree en terre en sautant dessus. */
public final class ChampsProteges extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    // Joueur qui saute ou atterrit sur un champ.
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onPlayerTrample(PlayerInteractEvent e) {
        if (e.getAction() == Action.PHYSICAL && e.getClickedBlock() != null
                && e.getClickedBlock().getType() == Material.FARMLAND) {
            e.setCancelled(true);
        }
    }

    // Mob qui saute ou atterrit sur un champ.
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onMobTrample(EntityInteractEvent e) {
        if (e.getBlock().getType() == Material.FARMLAND) {
            e.setCancelled(true);
        }
    }

    // Filet de securite : toute entite qui changerait un champ en terre.
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onEntityChange(EntityChangeBlockEvent e) {
        if (e.getBlock().getType() == Material.FARMLAND && e.getTo() == Material.DIRT) {
            e.setCancelled(true);
        }
    }
}
