package net.diverse.ffa.villager.sections;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.diverse.ffa.Core;
import net.diverse.ffa.villager.Merchant;
import net.diverse.ffa.villager.MerchantOffer;

public class Archery {

	public static void archeryShop(Player p) {
		Merchant m = new Merchant();
		m.setTitle(Core.Color("&aArquería"));
	    
	    ItemStack arcoempuje1 = new ItemStack(Material.BOW, 1);
		ItemMeta arcoempuje1meta = arcoempuje1.getItemMeta();
		arcoempuje1meta.addEnchant(Enchantment.ARROW_KNOCKBACK, 1, true);
		arcoempuje1.setItemMeta(arcoempuje1meta);
	    MerchantOffer offerarcoempuje1 = new MerchantOffer(new ItemStack(Material.EMERALD, 12), arcoempuje1);
	    m.addOffer(offerarcoempuje1);
	    
	    ItemStack arcopoder1 = new ItemStack(Material.BOW, 1);
		ItemMeta arcopoder1meta = arcopoder1.getItemMeta();
		arcopoder1meta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
		arcopoder1.setItemMeta(arcopoder1meta);
	    MerchantOffer offerarcopoder1 = new MerchantOffer(new ItemStack(Material.EMERALD, 12), arcopoder1);
	    m.addOffer(offerarcopoder1);
	    
	    ItemStack arcollama1 = new ItemStack(Material.BOW, 1);
		ItemMeta arcollama1meta = arcollama1.getItemMeta();
		arcollama1meta.addEnchant(Enchantment.ARROW_FIRE, 1, true);
		arcollama1.setItemMeta(arcollama1meta);
	    MerchantOffer offerarcollama1 = new MerchantOffer(new ItemStack(Material.EMERALD, 12), arcollama1);
	    m.addOffer(offerarcollama1);
	    
	    ItemStack flechas = new ItemStack(Material.ARROW, 16);
		ItemMeta flechasmeta = flechas.getItemMeta();
		flechas.setItemMeta(flechasmeta);
	    MerchantOffer offerflechas = new MerchantOffer(new ItemStack(Material.IRON_INGOT, 6), flechas);
	    m.addOffer(offerflechas);
	    
	    m.openTrading(p);
	}
}
