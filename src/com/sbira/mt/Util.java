package com.sbira.mt;

import org.bukkit.Material;
import org.bukkit.entity.Player;

public class Util {
	public static boolean permCheck(Player p, Material i) {
		
		if(!p.hasPermission("sbicraft.use.stone_pickaxe") && i == Material.STONE_PICKAXE) return false;
		else if(!p.hasPermission("sbicraft.use.iron_pickaxe") && i == Material.IRON_PICKAXE) return false;
		else if(!p.hasPermission("sbicraft.use.gold_pickaxe") && i == Material.GOLD_PICKAXE)	return false;
		else if(!p.hasPermission("sbicraft.use.diamond_pickaxe") && i == Material.DIAMOND_PICKAXE) return false;
		else if(!p.hasPermission("sbicraft.use.stone_axe") && i == Material.STONE_AXE) return false;
		else if(!p.hasPermission("sbicraft.use.iron_axe") && i == Material.IRON_AXE) return false;
		else if(!p.hasPermission("sbicraft.use.gold_axe") && i == Material.GOLD_AXE) return false;
		else if(!p.hasPermission("sbicraft.use.diamond_axe") && i == Material.DIAMOND_AXE) return false;
		else if(!p.hasPermission("sbicraft.use.stone_spade") && i == Material.STONE_SPADE) return false;
		else if(!p.hasPermission("sbicraft.use.iron_spade") && i == Material.IRON_SPADE) return false;
		else if(!p.hasPermission("sbicraft.use.gold_spade") && i == Material.GOLD_SPADE) return false;
		else if(!p.hasPermission("sbicraft.use.diamond_spade") && i == Material.DIAMOND_SPADE) return false;
		else if(!p.hasPermission("sbicraft.use.stone_hoe") && i == Material.STONE_HOE) return false;
		else if(!p.hasPermission("sbicraft.use.iron_hoe") && i == Material.IRON_HOE) return false;
		else if(!p.hasPermission("sbicraft.use.gold_hoe") && i == Material.GOLD_HOE) return false;
		else if(!p.hasPermission("sbicraft.use.diamond_hoe") && i == Material.DIAMOND_HOE) return false;
		else if(!p.hasPermission("sbicraft.use.stone_sword") && i == Material.STONE_SWORD) return false;
		else if(!p.hasPermission("sbicraft.use.iron_sword") && i == Material.IRON_SWORD) return false;
		else if(!p.hasPermission("sbicraft.use.gold_sword") && i == Material.GOLD_SWORD) return false;
		else if(!p.hasPermission("sbicraft.use.diamond_sword") && i == Material.DIAMOND_SWORD) return false;
		
		else if(!p.hasPermission("sbicraft.smelt.pork") && i == Material.PORK) return false;
		else if(!p.hasPermission("sbicraft.smelt.chicken") && i == Material.RAW_CHICKEN) return false;
		else if(!p.hasPermission("sbicraft.smelt.fish") && i == Material.RAW_FISH) return false;
		else if(!p.hasPermission("sbicraft.smelt.beef") && i == Material.RAW_BEEF) return false;
		else if(!p.hasPermission("sbicraft.smelt.potato") && i == Material.POTATO) return false;
		else return true;
		
	}
}
