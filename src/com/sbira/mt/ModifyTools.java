package com.sbira.mt;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class ModifyTools extends JavaPlugin implements Listener {
	
	public final Logger log = Logger.getLogger("Minecraft");
	private List<Material> axes = new ArrayList<Material>();
	private List<Material> pickaxes = new ArrayList<Material>();
	private List<Material> spades = new ArrayList<Material>();
	private List<Material> ores = new ArrayList<Material>();
	private List<Material> logs = new ArrayList<Material>();
	private List<Material> dirts = new ArrayList<Material>();
	
	@Override
	public void onEnable() {
		
		addMaterials();
		getServer().getPluginManager().registerEvents(this, this);
		
	}
	
	@Override
	public void onDisable() {
		
		
	}
	
	private void addMaterials() {
		
		axes.add(Material.WOOD_AXE);
		axes.add(Material.STONE_AXE);
		axes.add(Material.IRON_AXE);
		axes.add(Material.GOLD_AXE);
		axes.add(Material.DIAMOND_AXE);
		
		pickaxes.add(Material.WOOD_PICKAXE);
		pickaxes.add(Material.STONE_PICKAXE);
		pickaxes.add(Material.IRON_PICKAXE);
		pickaxes.add(Material.GOLD_PICKAXE);
		pickaxes.add(Material.DIAMOND_PICKAXE);
		
		spades.add(Material.WOOD_SPADE);
		spades.add(Material.STONE_SPADE);
		spades.add(Material.IRON_SPADE);
		spades.add(Material.GOLD_SPADE);
		spades.add(Material.DIAMOND_SPADE);
		
		ores.add(Material.COAL_ORE);
		ores.add(Material.IRON_ORE);
		ores.add(Material.GOLD_ORE);
		ores.add(Material.DIAMOND_ORE);
		ores.add(Material.REDSTONE_ORE);
		ores.add(Material.LAPIS_ORE);
		ores.add(Material.OBSIDIAN);
		
		logs.add(Material.LOG);
		
		dirts.add(Material.DIRT);
		dirts.add(Material.GRASS);
		dirts.add(Material.GRAVEL);
		dirts.add(Material.MYCEL);
		dirts.add(Material.SAND);
		dirts.add(Material.SNOW);
		dirts.add(Material.CLAY);
		dirts.add(Material.SOUL_SAND);
		
	}
	
	private void addSlow(Player player, int level) {
		removeSlow(player);
		player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 400, level));
	}
	
	private void removeSlow(Player player) {
		player.removePotionEffect(PotionEffectType.SLOW_DIGGING);
	}
	
	@EventHandler
	private void slowDigging(PlayerInteractEvent e) {
		
		Player p = e.getPlayer();
		Material holdItem = p.getItemInHand().getType();
		
		if(axes.contains(holdItem) && logs.contains(e.getClickedBlock().getType())) addSlow(p, 3);
    	else if(pickaxes.contains(holdItem) && ores.contains(e.getClickedBlock().getType())) addSlow(p, 2);
    	else if(spades.contains(holdItem) && dirts.contains(e.getClickedBlock().getType())) addSlow(p, 1);
    	else if(holdItem == Material.AIR && dirts.contains(e.getClickedBlock().getType())) addSlow(p, 3);
    	else removeSlow(p);
    	
	}
	
	@EventHandler
	private void onBlockBreak(BlockBreakEvent e) {
		
		Player p = e.getPlayer();
		
		if(e.getBlock().getType() == Material.LOG && !axes.contains(p.getItemInHand().getType())) {
			e.setCancelled(true);
			p.sendMessage("You Need Axe!");
		}
	}
}