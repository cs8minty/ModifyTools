package com.sbira.mt;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.google.common.base.Objects;

public class ModifyTools extends JavaPlugin implements Listener {
	
	public final Logger log = Logger.getLogger("Minecraft");
	private FileConfiguration config;
	private List<Material> axes = new ArrayList<Material>();
	private List<Material> pickaxes = new ArrayList<Material>();
	private List<Material> spades = new ArrayList<Material>();
	private List<Material> hoes = new ArrayList<Material>();
	private List<Material> ores = new ArrayList<Material>();
	private List<Material> logs = new ArrayList<Material>();
	private List<Material> dirts = new ArrayList<Material>();
	private List<Material> plants = new ArrayList<Material>();
	
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
		if(!config.contains("Message.PickAxe")) config.set("Message.PickAxe", "You Need PickAxe!");
		if(!config.contains("Message.Spade")) config.set("Message.Spade", "You Need Spade!");
		if(!config.contains("Message.Hoe")) config.set("Message.Hoe", "You Need Hoe!");
		
		if(!config.contains("Message.Cooking")) config.set("Message.Cooking", "Not Enough Cooking Skills!");
		
		if(!config.contains("Slow.Axe")) config.set("Slow.Axe", 4);
		if(!config.contains("Slow.PickAxe")) config.set("Slow.PickAxe", 3);
		if(!config.contains("Slow.Spade")) config.set("Slow.Spade", 3);
		if(!config.contains("Slow.Hoe")) config.set("Slow.Hoe", 4);
		
		// Mining Exp
		if(!config.contains("Exp.Coal")) config.set("Exp.Coal", 1);
		if(!config.contains("Exp.Iron")) config.set("Exp.Iron", 2);
		if(!config.contains("Exp.Gold")) config.set("Exp.Gold", 15);
		if(!config.contains("Exp.Diamond")) config.set("Exp.Diamond", 40);
		if(!config.contains("Exp.Emerald")) config.set("Exp.Emerald", 17);
		if(!config.contains("Exp.Lapis")) config.set("Exp.Lapis", 8);
		if(!config.contains("Exp.Redstone")) config.set("Exp.Redstone", 1);
		if(!config.contains("Exp.Quartz")) config.set("Exp.Quartz", 1);
		
		// Felling Exp
		if(!config.contains("Exp.Log")) config.set("Exp.Log", 1);
		
		// Cooking Exp - Smelt
		if(!config.contains("Exp.Pork")) config.set("Exp.Pork", 1);
		if(!config.contains("Exp.Chicken")) config.set("Exp.Chicken", 1);
		if(!config.contains("Exp.Fish")) config.set("Exp.Fish", 1);
		if(!config.contains("Exp.Steak")) config.set("Exp.Steak", 1);
		if(!config.contains("Exp.Potato")) config.set("Exp.Potato", 1);
		// Cooking Exp - Crafting
		if(!config.contains("Exp.Bread")) config.set("Exp.Bread", 1);
		if(!config.contains("Exp.Cookie")) config.set("Exp.Cookie", 1);
		if(!config.contains("Exp.Soup")) config.set("Exp.Soup", 1);
		if(!config.contains("Exp.Cake")) config.set("Exp.Cake", 1);
		if(!config.contains("Exp.PumpkinPie")) config.set("Exp.PumpkinPie", 1);
		
		saveConfig();

	}
	
	private void addMaterials() {
		
		axes.add(Material.WOOD_AXE);
		axes.add(Material.STONE_AXE);
		axes.add(Material.IRON_AXE);
		axes.add(Material.DIAMOND_AXE);
		
		pickaxes.add(Material.WOOD_PICKAXE);
		pickaxes.add(Material.STONE_PICKAXE);
		pickaxes.add(Material.IRON_PICKAXE);
		pickaxes.add(Material.DIAMOND_PICKAXE);
		
		spades.add(Material.WOOD_SPADE);
		spades.add(Material.STONE_SPADE);
		spades.add(Material.IRON_SPADE);
		spades.add(Material.DIAMOND_SPADE);
		
		hoes.add(Material.WOOD_HOE);
		hoes.add(Material.STONE_HOE);
		hoes.add(Material.IRON_HOE);
		hoes.add(Material.DIAMOND_HOE);
		
		ores.add(Material.COAL_ORE);
		ores.add(Material.IRON_ORE);
		ores.add(Material.GOLD_ORE);
		ores.add(Material.DIAMOND_ORE);
		ores.add(Material.REDSTONE_ORE);
		ores.add(Material.LAPIS_ORE);
		ores.add(Material.OBSIDIAN);
		
		logs.add(Material.LOG);
		logs.add(Material.WOOD);
		logs.add(Material.WORKBENCH);
		logs.add(Material.WOOD_STAIRS);
		logs.add(Material.WOOD_STEP);
		logs.add(Material.WOOD_DOUBLE_STEP);
		logs.add(Material.WOOD_PLATE);
		logs.add(Material.FENCE);
		logs.add(Material.FENCE_GATE);
		
		dirts.add(Material.DIRT);
		dirts.add(Material.SOIL);
		dirts.add(Material.GRASS);
		dirts.add(Material.GRAVEL);
		dirts.add(Material.MYCEL);
		dirts.add(Material.SAND);
		dirts.add(Material.SNOW);
		dirts.add(Material.CLAY);
		dirts.add(Material.SOUL_SAND);
		
		plants.add(Material.CROPS);
		plants.add(Material.MELON_BLOCK);
		plants.add(Material.MELON_STEM);
		plants.add(Material.CACTUS);
		plants.add(Material.CARROT);
		plants.add(Material.POTATO);
		plants.add(Material.PUMPKIN);
		plants.add(Material.PUMPKIN_STEM);
		plants.add(Material.COCOA);
		plants.add(Material.SUGAR_CANE_BLOCK);
		
	}
	
	private void addSlow(Player player, int level) {
		removeSlow(player);
		player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 72000, level - 1));
	}
	
	private void removeSlow(Player player) {
		player.removePotionEffect(PotionEffectType.SLOW_DIGGING);
	}
	
	public static void preventBreak(BlockBreakEvent e, String msg) {
		Player p = e.getPlayer();
		
		e.setCancelled(true);
		p.sendMessage(msg);
	}
	
	private void slowDigging(Player p, Material holditem) {
		
		if(axes.contains(holditem)) addSlow(p, config.getInt("Slow.Axe"));
		else if(pickaxes.contains(holditem)) addSlow(p, config.getInt("Slow.PickAxe"));
		else if(spades.contains(holditem)) addSlow(p, config.getInt("Slow.Spade"));
		else if(hoes.contains(holditem)) addSlow(p, config.getInt("Slow.Hoe"));
		else removeSlow(p);
		
	}
	
	@EventHandler
	private void slowDigging(PlayerItemHeldEvent e) {
		
		Player p = e.getPlayer();
		
		try {
			Material holditem = p.getInventory().getItem(e.getNewSlot()).getType();
			slowDigging(p, holditem);
		} catch(Exception e1) {
			removeSlow(p);
		}
    	
	}
	
	@EventHandler
	private void slowDigging(PlayerInteractEvent e) {
		
		Player p = e.getPlayer();
		Material holditem = p.getItemInHand().getType();
		slowDigging(p, holditem);
		
	}
	
	@EventHandler
	private void onBlockBreak(BlockBreakEvent e) {
		
		Player p = e.getPlayer();
		Material holdItem = p.getItemInHand().getType();
		Material block = e.getBlock().getType();
		
		// Permission Check for Tools
		if(!Util.permCheck(p, holdItem)) {
			preventBreak(e, "You can use this tool!");
			return;
		}
		
		// Check that You're Using the Correct Tools
		if(ores.contains(block) && !pickaxes.contains(holdItem) && holdItem != Material.GOLD_PICKAXE) {
			preventBreak(e, config.getString("Message.PickAxe"));
			return;
		}
		if(logs.contains(block) && !axes.contains(holdItem) && holdItem != Material.GOLD_AXE) {
			preventBreak(e, config.getString("Message.Axe"));
			return;
		}
		if(dirts.contains(block) && !spades.contains(holdItem) && holdItem != Material.GOLD_SPADE) {
			preventBreak(e, config.getString("Message.Spade"));
			return;
		}
		if(plants.contains(block) && !hoes.contains(holdItem) && holdItem != Material.GOLD_HOE) {
			preventBreak(e, config.getString("Message.Hoe"));
			return;
		}
		
		// Mining Gain Exp
		if(block == Material.COAL_ORE) gainExp(p, "Mining", config.getInt("Exp.Coal"));
		if(block == Material.QUARTZ_ORE) gainExp(p, "Mining", config.getInt("Exp.Quartz"));
		if(block == Material.LAPIS_ORE) gainExp(p, "Mining", config.getInt("Exp.Lapis"));
		if(block == Material.REDSTONE_ORE) gainExp(p, "Mining", config.getInt("Exp.Redstone"));
		if(block == Material.DIAMOND_ORE) gainExp(p, "Mining", config.getInt("Exp.Diamond"));
		if(block == Material.EMERALD_ORE) gainExp(p, "Mining", config.getInt("Exp.Emerald"));
		
		// Felling Gain Exp
		if(block == Material.LOG && axes.contains(holdItem)) gainExp(p, "Felling", config.getInt("Exp.LOG"));

	}
	
	@EventHandler
	private void onSmeltFood(InventoryClickEvent e) {
		
		Player p = (Player) e.getWhoClicked();
		
		if(e.getInventory().getType() == InventoryType.FURNACE && e.getRawSlot() == 0) {
			
			Material i = e.getWhoClicked().getItemOnCursor().getType();
			
			if(!Util.permCheck(p, i)) {
				p.sendMessage(config.getString("Message.Cooking"));
				e.setCancelled(true);
			}
			
		}
		
		if(e.isShiftClick() && e.getView().getTopInventory().getType().toString() == "FURNACE") {
			
			Material i = e.getCurrentItem().getType();
			
			if(!Util.permCheck(p, i)) {
				p.sendMessage(config.getString("Message.Cooking"));
				e.setCancelled(true);
			}
		}
		
	}
	
	@EventHandler
	private void onSmeltOre(InventoryClickEvent e) {
		
		if(!(e.getWhoClicked() instanceof Player)) return;
		if(e.getInventory().getType() != InventoryType.FURNACE || e.getRawSlot() != 2) return;
		
		Player p = (Player) e.getWhoClicked();
		Material i = e.getCurrentItem().getType();
		int exp = 0;
		
		// Mining
		if(i == Material.IRON_INGOT) {
			exp = config.getInt("Exp.Iron") * e.getCurrentItem().getAmount();
			gainExp(p, "Mining", exp);
		}
		
		if(i == Material.GOLD_INGOT) {
			exp = config.getInt("Exp.Gold") * e.getCurrentItem().getAmount();
			gainExp(p, "Mining", exp);
		}
		
		// Cooking
		if(i == Material.GRILLED_PORK) {
			exp = config.getInt("Exp.Pork") * e.getCurrentItem().getAmount();
			gainExp(p, "Cooking", exp);
		}
		
		if(i == Material.COOKED_CHICKEN) {
			exp = config.getInt("Exp.Chicken") * e.getCurrentItem().getAmount();
			gainExp(p, "Cooking", exp);
		}
		
		if(i == Material.COOKED_FISH) {
			exp = config.getInt("Exp.Fish") * e.getCurrentItem().getAmount();
			gainExp(p, "Cooking", exp);
		}
		
		if(i == Material.COOKED_BEEF) {
			exp = config.getInt("Exp.Steak") * e.getCurrentItem().getAmount();
			gainExp(p, "Cooking", exp);
		}
		
		if(i == Material.BAKED_POTATO) {
			exp = config.getInt("Exp.Potato") * e.getCurrentItem().getAmount();
			gainExp(p, "Cooking", exp);
		}
		
	}
	
	@EventHandler
	private void onCraft(InventoryClickEvent e) {
		
		if(e.getInventory() != null && e.getSlotType() == SlotType.RESULT) {
			switch (e.getInventory().getType()) {
			case CRAFTING:
			case WORKBENCH:
				handleCrafting(e);
				break;
			default:
				break;
			}
		}
		
	}
	
	private boolean hasItems(ItemStack stack) {
		
		return stack != null && stack.getAmount() > 0;
		
	}
	
	private void handleCrafting(InventoryClickEvent e) {
		
		HumanEntity p = e.getWhoClicked();
		ItemStack toCraft = e.getCurrentItem();
		ItemStack toStore = e.getCursor();
		
		if(p != null && hasItems(toCraft)) {
			
			if(e.isShiftClick()) schedulePostDetection(p, toCraft);
			else{
				if(isStackSumLegal(toCraft, toStore)) {
					int newItemsCount = toCraft.getAmount();
					
					CraftExp((Player) p, toCraft.getType(), newItemsCount);
				}
			}
		}
		
	}
	
	@SuppressWarnings("deprecation")
	private void schedulePostDetection(final HumanEntity p, final ItemStack compareItem) {
		
		final ItemStack[] preInv = p.getInventory().getContents();
		final int ticks = 1;
		
		for (int i = 0; i < preInv.length; i++) {
			preInv[i] = preInv[i] != null ? preInv[i].clone() : null;
		}
		
		Bukkit.getScheduler().scheduleAsyncDelayedTask(this, new Runnable() {
			@Override
			public void run() {
				final ItemStack[] postInv = p.getInventory().getContents();
				int newItemsCount = 0;
				
				for (int i = 0; i < preInv.length; i++) {
					ItemStack pre = preInv[i];
					ItemStack post = postInv[i];
					
					if (hasSameItem(compareItem, post) && (hasSameItem(compareItem, pre) || pre == null)) {
						newItemsCount += post.getAmount() - (pre != null ? pre.getAmount() : 0);
					}
				}
				
				if (newItemsCount > 0) {
					CraftExp((Player) p, compareItem.getType(), newItemsCount);
				}
			}
		}, ticks);
		
	}
	
	private boolean hasSameItem(ItemStack a, ItemStack b) {
		
		if(a == null) return b == null;
		else if (b == null) return a == null;
		
		return a.getTypeId() == b.getTypeId() &&
				a.getDurability() == b.getDurability() &&
				Objects.equal(a.getData(), b.getData()) &&
				Objects.equal(a.getEnchantments(), b.getEnchantments());
		
	}
	
	private boolean isStackSumLegal(ItemStack a, ItemStack b) {
		
		if (a == null || b == null) return true;
		else return a.getAmount() + b.getAmount() <= a.getType().getMaxStackSize();
		
	}
	
	private void CraftExp(Player player, Material item, int amount) {
		
		int exp = 0;
		
		if(item == Material.BREAD) {
			exp = config.getInt("Exp.Bread") * amount;
			gainExp(player, "Cooking", exp);
		}
		
		if(item == Material.COOKIE) {
			exp = config.getInt("Exp.Cookie") * amount;
			gainExp(player, "Cooking", exp);
		}
		
		if(item == Material.MUSHROOM_SOUP) {
			exp = config.getInt("Exp.Soup") * amount;
			gainExp(player, "Cooking", exp);
		}
		
		if(item == Material.CAKE) {
			exp = config.getInt("Exp.Cake") * amount;
			gainExp(player, "Cooking", exp);
		}
		
		if(item == Material.PUMPKIN_PIE) {
			exp = config.getInt("Exp.PumpkinPie") * amount;
			gainExp(player, "Cooking", exp);
		}
		
	}
	
	
	private void gainExp(Player p, String job, int exp) {
		getServer().dispatchCommand(getServer().getConsoleSender(), "cpa " + p.getName() + " " + job + " " + exp);
	}
	
}