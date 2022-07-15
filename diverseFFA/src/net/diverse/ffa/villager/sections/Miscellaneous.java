package net.diverse.ffa.villager.sections;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.diverse.ffa.Core;
import net.diverse.ffa.villager.Merchant;
import net.diverse.ffa.villager.MerchantOffer;

public class Miscellaneous {
	
	public static void miscellaneousShop(Player p) {
		Merchant m = new Merchant();
		m.setTitle(Core.Color("&aMiscel�neo"));
		
		ItemStack lapislazuli = new ItemStack(Material.INK_SACK, 2, (short)4);
		ItemMeta lapislazulimeta = lapislazuli.getItemMeta();
		lapislazuli.setItemMeta(lapislazulimeta);
	    MerchantOffer offerlapislazuli = new MerchantOffer(new ItemStack(Material.IRON_INGOT, 1), lapislazuli);
	    m.addOffer(offerlapislazuli);
		
		ItemStack encendedor = new ItemStack(Material.FLINT_AND_STEEL, 1);
		ItemMeta encendedormeta = encendedor.getItemMeta();
		encendedor.setDurability((short)60);
		encendedor.setItemMeta(encendedormeta);
	    MerchantOffer offerencendedor = new MerchantOffer(new ItemStack(Material.IRON_INGOT, 6), encendedor);
	    m.addOffer(offerencendedor);
		
	    ItemStack ca�a = new ItemStack(Material.FISHING_ROD, 1);
		ItemMeta ca�ameta = ca�a.getItemMeta();
		ca�a.setDurability((short)55);
		ca�a.setItemMeta(ca�ameta);
	    MerchantOffer offerca�a = new MerchantOffer(new ItemStack(Material.IRON_INGOT, 2), ca�a);
	    m.addOffer(offerca�a);
	    
	    ItemStack enderpearl = new ItemStack(Material.ENDER_PEARL, 1);
		ItemMeta enderpearlmeta = enderpearl.getItemMeta();
		enderpearl.setItemMeta(enderpearlmeta);
	    MerchantOffer offerenderpearl = new MerchantOffer(new ItemStack(Material.IRON_INGOT, 8), enderpearl);
	    m.addOffer(offerenderpearl);
	    
	    m.openTrading(p);
	}
}
