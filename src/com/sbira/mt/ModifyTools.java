package com.sbira.mt;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
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
	private FileConfiguration config;
	private List<Material> axes = new ArrayList<Material>();
	private List<Material> pickaxes = new ArrayList<Material>();
	private List<Material> spades = new ArrayList<Material>();
	private List<Material> ores = new ArrayList<Material>();
	private List<Material> logs = new ArrayList<Material>();
	private List<Material> dirts = new ArrayList<Material>();
	
	@Override
	public void onEnable() {
		
		initConfig();
		addMaterials();
		getServer().getPluginManager().registerEvents(this, this);
		
	}
	
	@Override
	public void onDisable() {
		
		
	}
	
	private void initConfig() {
		
		config = this.getConfig();
		File file = new File("plugins" + File.separator + this.getDescription().getName() + File.separator + "config.yml");
		
		file.mkdir();
		
		if(!config.contains("Message.Axe")) config.set("Message.Axe", "You Need Axe!");
		if(!config.contains("Message.Spade")) config.set("Message.Spade", "You Need Spade!");
		if(!config.contains("Slow.Axe")) config.set("Slow.Axe", 4);
		if(!config.contains("Slow.PickAxe")) config.set("Slow.PickAxe", 3);
		if(!config.contains("Slow.Spade")) config.set("Slow.Spade", 3);
		
		saveConfig();

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
		player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 72000, level - 1));
	}
	
	private void removeSlow(Player player) {
		player.removePotionEffect(PotionEffectType.SLOW_DIGGING);
	}
	
	private void preventBreak(BlockBreakEvent e, String msg) {
		Player p = e.getPlayer();
		
		e.setCancelled(true);
		p.sendMessage(msg);
	}
	
	@EventHandler
	private void slowDigging(PlayerInteractEvent e) {
		
		Player p = e.getPlayer();
		Material holdItem = p.getItemInHand().getType();
		Material block = e.getClickedBlock().getType();
		
		if(axes.contains(holdItem) && logs.contains(block)) addSlow(p, config.getInt("Slow.Axe"));
    	else if(pickaxes.contains(holdItem) && ores.contains(block)) addSlow(p, config.getInt("Slow.PickAxe"));
    	else if(spades.contains(holdItem) && dirts.contains(block)) addSlow(p, config.getInt("Slow.Spade"));
    	else removeSlow(p);
    	
	}
	
	@EventHandler
	private void onBlockBreak(BlockBreakEvent e) {
		
		Player p = e.getPlayer();
		Material holdItem = p.getItemInHand().getType();
		Material block = e.getBlock().getType();
		
		if(logs.contains(block) && !axes.contains(holdItem)) preventBreak(e, config.getString("Message.Axe"));
		if(dirts.contains(block) && !spades.contains(holdItem)) preventBreak(e, config.getString("Message.Spade"));

	}
	
}