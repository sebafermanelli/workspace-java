package net.diverse.ffa.villager.sections;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.diverse.ffa.Core;
import net.diverse.ffa.villager.Merchant;
import net.diverse.ffa.villager.MerchantOffer;

public class Laboratory {

	public static void laboratoryShop(Player p) {
		Merchant m = new Merchant();
		m.setTitle(Core.Color("&aLaboratorio"));
		
		/* Potions */
		
	    ItemStack pocion1 = new ItemStack(Material.EXP_BOTTLE, 4);
		ItemMeta pocion1meta = pocion1.getItemMeta();
		pocion1.setItemMeta(pocion1meta);
	    MerchantOffer offerpocion1 = new MerchantOffer(new ItemStack(Material.GOLD_INGOT, 1), pocion1);
	    m.addOffer(offerpocion1);
	    
	    ItemStack pocion2 = new ItemStack(Material.POTION, 1,(short)16421);
		ItemMeta pocion2meta = pocion2.getItemMeta();
		pocion2.setItemMeta(pocion2meta);
	    MerchantOffer offerpocion2 = new MerchantOffer(new ItemStack(Material.GOLD_INGOT, 1), pocion2);
	    m.addOffer(offerpocion2);
	    
	    ItemStack pocion3 = new ItemStack(Material.POTION, 1,(short)16449);
		ItemMeta pocion3meta = pocion3.getItemMeta();
		pocion3.setItemMeta(pocion3meta);
	    MerchantOffer offerpocion3 = new MerchantOffer(new ItemStack(Material.GOLD_INGOT, 8), pocion3);
	    m.addOffer(offerpocion3);
	    
	    ItemStack pocion4 = new ItemStack(Material.POTION, 1,(short)8257);
		ItemMeta pocion4meta = pocion4.getItemMeta();
		pocion4.setItemMeta(pocion4meta);
	    MerchantOffer offerpocion4 = new MerchantOffer(new ItemStack(Material.GOLD_INGOT, 6), pocion4);
	    m.addOffer(offerpocion4);
	    
	    ItemStack pocion5 = new ItemStack(Material.POTION, 1,(short)16450);
		ItemMeta pocion5meta = pocion5.getItemMeta();
		pocion5.setItemMeta(pocion5meta);
	    MerchantOffer offerpocion5 = new MerchantOffer(new ItemStack(Material.GOLD_INGOT, 8), pocion5);
	    m.addOffer(offerpocion5);
	    
	    ItemStack pocion6 = new ItemStack(Material.POTION, 1,(short)8258);
		ItemMeta pocion6meta = pocion6.getItemMeta();
		pocion6.setItemMeta(pocion6meta);
	    MerchantOffer offerpocion6 = new MerchantOffer(new ItemStack(Material.GOLD_INGOT, 6), pocion6);
	    m.addOffer(offerpocion6);
	    
	    ItemStack pocion7 = new ItemStack(Material.POTION, 1,(short)16451);
		ItemMeta pocion7meta = pocion7.getItemMeta();
		pocion7.setItemMeta(pocion7meta);
	    MerchantOffer offerpocion7 = new MerchantOffer(new ItemStack(Material.GOLD_INGOT, 8), pocion7);
	    m.addOffer(offerpocion7);
	    
	    ItemStack pocion8 = new ItemStack(Material.POTION, 1,(short)8259);
		ItemMeta pocion8meta = pocion8.getItemMeta();
		pocion8.setItemMeta(pocion8meta);
	    MerchantOffer offerpocion8 = new MerchantOffer(new ItemStack(Material.GOLD_INGOT, 6), pocion8);
	    m.addOffer(offerpocion8);
	    
	    ItemStack pocion10 = new ItemStack(Material.POTION, 1,(short)8265);
		ItemMeta pocion10meta = pocion10.getItemMeta();
		pocion10.setItemMeta(pocion10meta);
	    MerchantOffer offerpocion10 = new MerchantOffer(new ItemStack(Material.GOLD_INGOT, 10), pocion10);
	    m.addOffer(offerpocion10);
	    
	    m.openTrading(p);
	}
}
