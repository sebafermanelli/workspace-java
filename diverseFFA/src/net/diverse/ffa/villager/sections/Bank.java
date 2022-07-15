package net.diverse.ffa.villager.sections;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.diverse.ffa.Core;
import net.diverse.ffa.villager.Merchant;
import net.diverse.ffa.villager.MerchantOffer;

public class Bank {
	
	public static void bankShop(Player p) {
		Merchant m = new Merchant();
		m.setTitle(Core.Color("&aBanco"));
		
		ItemStack cambio1 = new ItemStack(Material.GOLD_INGOT, 1);
		ItemMeta cambio1meta = cambio1.getItemMeta();
		cambio1.setItemMeta(cambio1meta);
	    MerchantOffer offercambio1 = new MerchantOffer(new ItemStack(Material.IRON_INGOT, 2), cambio1);
	    m.addOffer(offercambio1);
	    
	    ItemStack cambio2 = new ItemStack(Material.IRON_INGOT, 2);
		ItemMeta cambio2meta = cambio2.getItemMeta();
		cambio2.setItemMeta(cambio2meta);
	    MerchantOffer offercambio2 = new MerchantOffer(new ItemStack(Material.GOLD_INGOT, 1), cambio2);
	    m.addOffer(offercambio2);
	    
	    ItemStack cambio3 = new ItemStack(Material.DIAMOND, 1);
		ItemMeta cambio3meta = cambio3.getItemMeta();
		cambio3.setItemMeta(cambio3meta);
	    MerchantOffer offercambio3 = new MerchantOffer(new ItemStack(Material.GOLD_INGOT, 2), cambio3);
	    m.addOffer(offercambio3);
	    
	    ItemStack cambio4 = new ItemStack(Material.GOLD_INGOT, 2);
		ItemMeta cambio4meta = cambio4.getItemMeta();
		cambio4.setItemMeta(cambio4meta);
	    MerchantOffer offercambio4 = new MerchantOffer(new ItemStack(Material.DIAMOND, 1), cambio4);
	    m.addOffer(offercambio4);
	    
	    ItemStack cambio5 = new ItemStack(Material.EMERALD, 1);
		ItemMeta cambio5meta = cambio5.getItemMeta();
		cambio5.setItemMeta(cambio5meta);
	    MerchantOffer offercambio5 = new MerchantOffer(new ItemStack(Material.DIAMOND, 2), cambio5);
	    m.addOffer(offercambio5);
	    
	    ItemStack cambio6 = new ItemStack(Material.DIAMOND, 2);
		ItemMeta cambio6meta = cambio6.getItemMeta();
		cambio6.setItemMeta(cambio6meta);
	    MerchantOffer offercambio6 = new MerchantOffer(new ItemStack(Material.EMERALD, 1), cambio6);
	    m.addOffer(offercambio6);
	    
	    m.openTrading(p);
	}
}
