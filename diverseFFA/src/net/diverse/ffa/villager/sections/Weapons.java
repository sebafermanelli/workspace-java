package net.diverse.ffa.villager.sections;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.diverse.ffa.Core;
import net.diverse.ffa.villager.Merchant;
import net.diverse.ffa.villager.MerchantOffer;

public class Weapons {
	
	public static void weaponsShop(Player p) {
		Merchant m = new Merchant();
		m.setTitle(Core.Color("&aArmas"));
	    
	    ItemStack espadahierrofilo2 = new ItemStack(Material.IRON_SWORD, 1);
		ItemMeta espadahierrofilo2meta = espadahierrofilo2.getItemMeta();
		espadahierrofilo2meta.addEnchant(Enchantment.DAMAGE_ALL, 2, true);
		espadahierrofilo2.setItemMeta(espadahierrofilo2meta);
	    MerchantOffer offerespadahierrofilo2 = new MerchantOffer(new ItemStack(Material.DIAMOND, 12), espadahierrofilo2);
	    m.addOffer(offerespadahierrofilo2);
	    
	    ItemStack espadahierrofilo2igneo1 = new ItemStack(Material.IRON_SWORD, 1);
		ItemMeta espadahierrofilo2igneo1meta = espadahierrofilo2igneo1.getItemMeta();
		espadahierrofilo2igneo1meta.addEnchant(Enchantment.DAMAGE_ALL, 2, true);
		espadahierrofilo2igneo1meta.addEnchant(Enchantment.FIRE_ASPECT, 1, true);
		espadahierrofilo2igneo1.setItemMeta(espadahierrofilo2igneo1meta);
	    MerchantOffer offerespadahierrofilo2igneo1 = new MerchantOffer(new ItemStack(Material.DIAMOND, 24), espadahierrofilo2igneo1);
	    m.addOffer(offerespadahierrofilo2igneo1);
	    
	    ItemStack espadahierrofilo4 = new ItemStack(Material.IRON_SWORD, 1);
		ItemMeta espadahierrofilo4meta = espadahierrofilo4.getItemMeta();
		espadahierrofilo4meta.addEnchant(Enchantment.DAMAGE_ALL, 4, true);
		espadahierrofilo4.setItemMeta(espadahierrofilo4meta);
	    MerchantOffer offerespadahierrofilo4 = new MerchantOffer(new ItemStack(Material.EMERALD, 32), espadahierrofilo4);
	    m.addOffer(offerespadahierrofilo4);
	    
	    ItemStack espadahierrofilo4igneo1 = new ItemStack(Material.IRON_SWORD, 1);
		ItemMeta espadahierrofilo4igneo1meta = espadahierrofilo4igneo1.getItemMeta();
		espadahierrofilo4igneo1meta.addEnchant(Enchantment.DAMAGE_ALL, 4, true);
		espadahierrofilo4igneo1meta.addEnchant(Enchantment.FIRE_ASPECT, 1, true);
		espadahierrofilo4igneo1.setItemMeta(espadahierrofilo4igneo1meta);
	    MerchantOffer offerespadahierrofilo4igneo1 = new MerchantOffer(new ItemStack(Material.EMERALD, 48), espadahierrofilo4igneo1);
	    m.addOffer(offerespadahierrofilo4igneo1);
	    
		ItemStack espadadiamante = new ItemStack(Material.DIAMOND_SWORD, 1);
		ItemMeta espadadiamantemeta = espadadiamante.getItemMeta();
		espadadiamante.setItemMeta(espadadiamantemeta);
	    MerchantOffer offerespadadiamante = new MerchantOffer(new ItemStack(Material.GOLD_INGOT, 24), espadadiamante);
	    m.addOffer(offerespadadiamante);
	    
	    ItemStack espadadiamantefilo2 = new ItemStack(Material.DIAMOND_SWORD, 1);
		ItemMeta espadadiamantefilo2meta = espadadiamantefilo2.getItemMeta();
		espadadiamantefilo2meta.addEnchant(Enchantment.DAMAGE_ALL, 2, true);
		espadadiamantefilo2.setItemMeta(espadadiamantefilo2meta);
	    MerchantOffer offerespadadiamantefilo2 = new MerchantOffer(new ItemStack(Material.DIAMOND, 32), espadadiamantefilo2);
	    m.addOffer(offerespadadiamantefilo2);
	    
	    ItemStack espadadiamantefilo2igneo1 = new ItemStack(Material.DIAMOND_SWORD, 1);
		ItemMeta espadadiamantefilo2igneo1meta = espadadiamantefilo2igneo1.getItemMeta();
		espadadiamantefilo2igneo1meta.addEnchant(Enchantment.DAMAGE_ALL, 2, true);
		espadadiamantefilo2igneo1meta.addEnchant(Enchantment.FIRE_ASPECT, 1, true);
		espadadiamantefilo2igneo1.setItemMeta(espadadiamantefilo2igneo1meta);
	    MerchantOffer offerespadadiamantefilo2igneo1 = new MerchantOffer(new ItemStack(Material.DIAMOND, 48), espadadiamantefilo2igneo1);
	    m.addOffer(offerespadadiamantefilo2igneo1);
	    
	    ItemStack espadadiamantefilo4 = new ItemStack(Material.DIAMOND_SWORD, 1);
		ItemMeta espadadiamantefilo4meta = espadadiamantefilo4.getItemMeta();
		espadadiamantefilo4meta.addEnchant(Enchantment.DAMAGE_ALL, 4, true);
		espadadiamantefilo4.setItemMeta(espadadiamantefilo4meta);
	    MerchantOffer offerespadadiamantefilo4 = new MerchantOffer(new ItemStack(Material.EMERALD, 64), espadadiamantefilo4);
	    m.addOffer(offerespadadiamantefilo4);
	    
	    ItemStack espadadiamantefilo4igneo1 = new ItemStack(Material.DIAMOND_SWORD, 1);
		ItemMeta espadadiamantefilo4igneo1meta = espadadiamantefilo4igneo1.getItemMeta();
		espadadiamantefilo4igneo1meta.addEnchant(Enchantment.DAMAGE_ALL, 4, true);
		espadadiamantefilo4igneo1meta.addEnchant(Enchantment.FIRE_ASPECT, 1, true);
		espadadiamantefilo4igneo1.setItemMeta(espadadiamantefilo4igneo1meta);
	    MerchantOffer offerespadadiamantefilo4igneo1 = new MerchantOffer(new ItemStack(Material.EMERALD, 64), new ItemStack(Material.EMERALD, 16), espadadiamantefilo4igneo1);
	    m.addOffer(offerespadadiamantefilo4igneo1);
	    
	    m.openTrading(p);
	}
}
