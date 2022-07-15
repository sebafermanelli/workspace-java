package net.diverse.ffa.villager.sections;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.diverse.ffa.Core;
import net.diverse.ffa.villager.Merchant;
import net.diverse.ffa.villager.MerchantOffer;

public class Food {
	
	public static void foodShop(Player p) {
		Merchant m = new Merchant();
		m.setTitle(Core.Color("&aComida"));
		
		ItemStack zanahoria = new ItemStack(Material.CARROT_ITEM, 5);
		ItemMeta zanahoriameta = zanahoria.getItemMeta();
		zanahoria.setItemMeta(zanahoriameta);
	    MerchantOffer offerzanahoria = new MerchantOffer(new ItemStack(Material.IRON_INGOT, 1), zanahoria);
	    m.addOffer(offerzanahoria);
	    
	    ItemStack papa = new ItemStack(Material.BAKED_POTATO, 5);
	    ItemMeta papameta = papa.getItemMeta();
	    papa.setItemMeta(papameta);
	    MerchantOffer offerpapa = new MerchantOffer(new ItemStack(Material.IRON_INGOT, 2), papa);
	    m.addOffer(offerpapa);
	    
	    ItemStack bife = new ItemStack(Material.COOKED_BEEF, 5);
	    ItemMeta bifemeta = bife.getItemMeta();
	    bife.setItemMeta(bifemeta);
	    MerchantOffer offerbife = new MerchantOffer(new ItemStack(Material.IRON_INGOT, 4), bife);
	    m.addOffer(offerbife);
	    
	    ItemStack manzanadorada = new ItemStack(Material.GOLDEN_APPLE, 1);
	    ItemMeta manzanadoradameta = manzanadorada.getItemMeta();
	    manzanadorada.setItemMeta(manzanadoradameta);
	    MerchantOffer offermanzanadorada = new MerchantOffer(new ItemStack(Material.GOLD_INGOT, 2), manzanadorada);
	    m.addOffer(offermanzanadorada);
	    
	    ItemStack manzanadoradaencantada = new ItemStack(Material.GOLDEN_APPLE, 1, (short)1);
	    ItemMeta manzanadoradaencantadameta = manzanadoradaencantada.getItemMeta();
	    manzanadoradaencantada.setItemMeta(manzanadoradaencantadameta);
	    MerchantOffer offermanzanadoradaencantada = new MerchantOffer(new ItemStack(Material.EMERALD, 16), manzanadoradaencantada);
	    m.addOffer(offermanzanadoradaencantada);
	    
	    m.openTrading(p);
	}
}
