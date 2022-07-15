package net.diverse.report.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class ItemBuildAPI {
	
	public static void createItem(Material material, int amount, int data, Inventory inv, int Slot, String name, List<String> lore) {
		ItemStack item = new ItemStack(material, amount, (short) data);
	    ItemMeta meta = item.getItemMeta();
	    meta.setDisplayName(name);
	    meta.setLore(lore);
	    item.setItemMeta(meta);
	    
	    inv.setItem(Slot, item);
	}
	public static void createItem(Material material, int amount, int data, Inventory inv, int Slot, String name) {
	    ItemStack item = new ItemStack(material, amount, (short) data);
	    ItemMeta meta = item.getItemMeta();
	    meta.setDisplayName(name);
	    item.setItemMeta(meta);
	    
	    inv.setItem(Slot, item);
	}
	
	public static void createItem(ItemStack material, Inventory inv, int Slot) {
	    inv.setItem(Slot, material);
	}
	
	public static void createSkull(String owner, Inventory inv, int Slot, String name, List<String> lore) {
	    ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short)SkullType.PLAYER.ordinal());
	    SkullMeta skullmeta = (SkullMeta)skull.getItemMeta();
	    skullmeta.setDisplayName(name);
	    skullmeta.setLore(lore);
	    skullmeta.setOwner(owner);
	    skull.setItemMeta(skullmeta);
	    
	    inv.setItem(Slot, skull);
	}
	
	public static void createSkull(String owner, Inventory inv, int Slot, String name) {
	    ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short)SkullType.PLAYER.ordinal());
	    SkullMeta skullmeta = (SkullMeta)skull.getItemMeta();
	    skullmeta.setDisplayName(name);
	    skullmeta.setOwner(owner);
	    skull.setItemMeta(skullmeta);
	    
	    inv.setItem(Slot, skull);
	}
	
	public static String ConvertSecondToHHMMString(int secondtTime) {
	    TimeZone tz = TimeZone.getTimeZone("UTC");
	    SimpleDateFormat df = new SimpleDateFormat("mm:ss");
	    df.setTimeZone(tz);
	    String time = df.format(new Date(secondtTime * 1000L));
	    
	    return time;
	}
}
