package net.diverse.ffa.utils;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Kit {
	
	public static void addKit(Player p) {
		p.getInventory().clear();
		
		ItemStack sword = new ItemStack(Material.IRON_SWORD, 1);
		ItemStack rod = new ItemStack(Material.FISHING_ROD, 1);
		ItemStack bow = new ItemStack(Material.BOW, 1);
		rod.setDurability((short)60);
		ItemStack flint_and_steel = new ItemStack(Material.FLINT_AND_STEEL, 1);
		flint_and_steel.setDurability((short)63);
		ItemStack food = new ItemStack(Material.COOKED_BEEF, 8);
		ItemStack arrows = new ItemStack(Material.ARROW, 12);
		
		ItemStack helmet = new ItemStack(Material.IRON_HELMET, 1);
		ItemStack chestplate = new ItemStack(Material.IRON_CHESTPLATE, 1);
		ItemStack leggings = new ItemStack(Material.IRON_LEGGINGS, 1);
		ItemStack boots = new ItemStack(Material.IRON_BOOTS, 1);
		
		p.getInventory().addItem(new ItemStack[] { sword });
		p.getInventory().addItem(new ItemStack[] { rod });
		p.getInventory().addItem(new ItemStack[] { bow });
		p.getInventory().addItem(new ItemStack[] { flint_and_steel });
		p.getInventory().addItem(new ItemStack[] { food });
		p.getInventory().addItem(new ItemStack[] { arrows });
		
		p.getInventory().setHelmet(helmet);
		p.getInventory().setChestplate(chestplate);
		p.getInventory().setLeggings(leggings);
		p.getInventory().setBoots(boots);
	}
}
