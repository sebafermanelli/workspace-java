package net.diverse.ffa.villager.sections;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.diverse.ffa.Core;
import net.diverse.ffa.villager.Merchant;
import net.diverse.ffa.villager.MerchantOffer;

public class Armours {
	
	public static void armoursShop(Player p) {
		Merchant m = new Merchant();
		m.setTitle(Core.Color("&aArmaduras"));
		
		/* Iron Armours */
	    
	    ItemStack cascohierroprote2 = new ItemStack(Material.IRON_HELMET, 1);
		ItemMeta cascohierroprote2meta = cascohierroprote2.getItemMeta();
		cascohierroprote2meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
		cascohierroprote2.setItemMeta(cascohierroprote2meta);
	    MerchantOffer offercascohierroprote2 = new MerchantOffer(new ItemStack(Material.DIAMOND, 12), cascohierroprote2);
	    m.addOffer(offercascohierroprote2);
	    
	    ItemStack pecherahierroprote2 = new ItemStack(Material.IRON_CHESTPLATE, 1);
		ItemMeta pecherahierroprote2meta = pecherahierroprote2.getItemMeta();
		pecherahierroprote2meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
		pecherahierroprote2.setItemMeta(pecherahierroprote2meta);
	    MerchantOffer offerpecherahierroprote2 = new MerchantOffer(new ItemStack(Material.DIAMOND, 12), pecherahierroprote2);
	    m.addOffer(offerpecherahierroprote2);
	    
	    ItemStack pantalonhierroprote2 = new ItemStack(Material.IRON_LEGGINGS, 1);
		ItemMeta pantalonhierroprote2meta = pantalonhierroprote2.getItemMeta();
		pantalonhierroprote2meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
		pantalonhierroprote2.setItemMeta(pantalonhierroprote2meta);
	    MerchantOffer offerpantalonhierroprote2 = new MerchantOffer(new ItemStack(Material.DIAMOND, 12), pantalonhierroprote2);
	    m.addOffer(offerpantalonhierroprote2);
	    
	    ItemStack botashierroprote2 = new ItemStack(Material.IRON_BOOTS, 1);
		ItemMeta botashierroprote2meta = botashierroprote2.getItemMeta();
		botashierroprote2meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
		botashierroprote2.setItemMeta(botashierroprote2meta);
	    MerchantOffer offerbotashierroprote2 = new MerchantOffer(new ItemStack(Material.DIAMOND, 12), botashierroprote2);
	    m.addOffer(offerbotashierroprote2);
	    
	    ItemStack cascohierroprote4 = new ItemStack(Material.IRON_HELMET, 1);
		ItemMeta cascohierroprote4meta = cascohierroprote4.getItemMeta();
		cascohierroprote4meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
		cascohierroprote4.setItemMeta(cascohierroprote4meta);
	    MerchantOffer offercascohierroprote4 = new MerchantOffer(new ItemStack(Material.EMERALD, 12), cascohierroprote4);
	    m.addOffer(offercascohierroprote4);
	    
	    ItemStack pecherahierroprote4 = new ItemStack(Material.IRON_CHESTPLATE, 1);
		ItemMeta pecherahierroprote4meta = pecherahierroprote4.getItemMeta();
		pecherahierroprote4meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
		pecherahierroprote4.setItemMeta(pecherahierroprote4meta);
	    MerchantOffer offerpecherahierroprote4 = new MerchantOffer(new ItemStack(Material.EMERALD, 12), pecherahierroprote4);
	    m.addOffer(offerpecherahierroprote4);
	    
	    ItemStack pantalonhierroprote4 = new ItemStack(Material.IRON_LEGGINGS, 1);
		ItemMeta pantalonhierroprote4meta = pantalonhierroprote4.getItemMeta();
		pantalonhierroprote4meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
		pantalonhierroprote4.setItemMeta(pantalonhierroprote4meta);
	    MerchantOffer offerpantalonhierroprote4 = new MerchantOffer(new ItemStack(Material.EMERALD, 12), pantalonhierroprote4);
	    m.addOffer(offerpantalonhierroprote4);
	    
	    ItemStack botashierroprote4 = new ItemStack(Material.IRON_BOOTS, 1);
		ItemMeta botashierroprote4meta = botashierroprote4.getItemMeta();
		botashierroprote4meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
		botashierroprote4.setItemMeta(botashierroprote4meta);
	    MerchantOffer offerbotashierroprote4 = new MerchantOffer(new ItemStack(Material.EMERALD, 12), botashierroprote4);
	    m.addOffer(offerbotashierroprote4);
	    
		/* Diamond Armours */
	    
	    ItemStack cascodiamante = new ItemStack(Material.DIAMOND_HELMET, 1);
		ItemMeta cascodiamantemeta = cascodiamante.getItemMeta();
		cascodiamante.setItemMeta(cascodiamantemeta);
	    MerchantOffer offercascodiamante = new MerchantOffer(new ItemStack(Material.GOLD_INGOT, 12), cascodiamante);
	    m.addOffer(offercascodiamante);
	    
	    ItemStack pecheradiamante = new ItemStack(Material.DIAMOND_CHESTPLATE, 1);
		ItemMeta pecheradiamantemeta = pecheradiamante.getItemMeta();
		pecheradiamante.setItemMeta(pecheradiamantemeta);
	    MerchantOffer offerpecheradiamante = new MerchantOffer(new ItemStack(Material.GOLD_INGOT, 12), pecheradiamante);
	    m.addOffer(offerpecheradiamante);
	    
	    ItemStack pantalondiamante = new ItemStack(Material.DIAMOND_LEGGINGS, 1);
		ItemMeta pantalondiamantemeta = pantalondiamante.getItemMeta();
		pantalondiamante.setItemMeta(pantalondiamantemeta);
	    MerchantOffer offerpantalondiamante = new MerchantOffer(new ItemStack(Material.GOLD_INGOT, 12), pantalondiamante);
	    m.addOffer(offerpantalondiamante);
	    
	    ItemStack botasdiamante = new ItemStack(Material.DIAMOND_BOOTS, 1);
		ItemMeta botasdiamantemeta = botasdiamante.getItemMeta();
		botasdiamante.setItemMeta(botasdiamantemeta);
	    MerchantOffer offerbotasdiamante = new MerchantOffer(new ItemStack(Material.GOLD_INGOT, 12), botasdiamante);
	    m.addOffer(offerbotasdiamante);
	    
	    ItemStack cascodiamanteprote2 = new ItemStack(Material.DIAMOND_HELMET, 1);
		ItemMeta cascodiamanteprote2meta = cascodiamanteprote2.getItemMeta();
		cascodiamanteprote2meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
		cascodiamanteprote2.setItemMeta(cascodiamanteprote2meta);
	    MerchantOffer offercascodiamanteprote2 = new MerchantOffer(new ItemStack(Material.DIAMOND, 24), cascodiamanteprote2);
	    m.addOffer(offercascodiamanteprote2);
	    
	    ItemStack pecheradiamanteprote2 = new ItemStack(Material.DIAMOND_CHESTPLATE, 1);
		ItemMeta pecheradiamanteprote2meta = pecheradiamanteprote2.getItemMeta();
		pecheradiamanteprote2meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
		pecheradiamanteprote2.setItemMeta(pecheradiamanteprote2meta);
	    MerchantOffer offerpecheradiamanteprote2 = new MerchantOffer(new ItemStack(Material.DIAMOND, 24), pecheradiamanteprote2);
	    m.addOffer(offerpecheradiamanteprote2);
	    
	    ItemStack pantalondiamanteprote2 = new ItemStack(Material.DIAMOND_LEGGINGS, 1);
		ItemMeta pantalondiamanteprote2meta = pantalondiamanteprote2.getItemMeta();
		pantalondiamanteprote2meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
		pantalondiamanteprote2.setItemMeta(pantalondiamanteprote2meta);
	    MerchantOffer offerpantalondiamanteprote2 = new MerchantOffer(new ItemStack(Material.DIAMOND, 24), pantalondiamanteprote2);
	    m.addOffer(offerpantalondiamanteprote2);
	    
	    ItemStack botasdiamanteprote2 = new ItemStack(Material.DIAMOND_BOOTS, 1);
		ItemMeta botasdiamanteprote2meta = botasdiamanteprote2.getItemMeta();
		botasdiamanteprote2meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
		botasdiamanteprote2.setItemMeta(botasdiamanteprote2meta);
	    MerchantOffer offerbotasdiamanteprote2 = new MerchantOffer(new ItemStack(Material.DIAMOND, 24), botasdiamanteprote2);
	    m.addOffer(offerbotasdiamanteprote2);
	    
	    ItemStack cascodiamanteprote4 = new ItemStack(Material.DIAMOND_HELMET, 1);
		ItemMeta cascodiamanteprote4meta = cascodiamanteprote4.getItemMeta();
		cascodiamanteprote4meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
		cascodiamanteprote4.setItemMeta(cascodiamanteprote4meta);
	    MerchantOffer offercascodiamanteprote4 = new MerchantOffer(new ItemStack(Material.EMERALD, 24), cascodiamanteprote4);
	    m.addOffer(offercascodiamanteprote4);
	    
	    ItemStack pecheradiamanteprote4 = new ItemStack(Material.DIAMOND_CHESTPLATE, 1);
		ItemMeta pecheradiamanteprote4meta = pecheradiamanteprote4.getItemMeta();
		pecheradiamanteprote4meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
		pecheradiamanteprote4.setItemMeta(pecheradiamanteprote4meta);
	    MerchantOffer offerpecheradiamanteprote4 = new MerchantOffer(new ItemStack(Material.EMERALD, 24), pecheradiamanteprote4);
	    m.addOffer(offerpecheradiamanteprote4);
	    
	    ItemStack pantalondiamanteprote4 = new ItemStack(Material.DIAMOND_LEGGINGS, 1);
		ItemMeta pantalondiamanteprote4meta = pantalondiamanteprote4.getItemMeta();
		pantalondiamanteprote4meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
		pantalondiamanteprote4.setItemMeta(pantalondiamanteprote4meta);
	    MerchantOffer offerpantalondiamanteprote4 = new MerchantOffer(new ItemStack(Material.EMERALD, 24), pantalondiamanteprote4);
	    m.addOffer(offerpantalondiamanteprote4);
	    
	    ItemStack botasdiamanteprote4 = new ItemStack(Material.DIAMOND_BOOTS, 1);
		ItemMeta botasdiamanteprote4meta = botasdiamanteprote4.getItemMeta();
		botasdiamanteprote4meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
		botasdiamanteprote4.setItemMeta(botasdiamanteprote4meta);
	    MerchantOffer offerbotasdiamanteprote4 = new MerchantOffer(new ItemStack(Material.EMERALD, 24), botasdiamanteprote4);
	    m.addOffer(offerbotasdiamanteprote4);
	    
	    m.openTrading(p);
	}
}
