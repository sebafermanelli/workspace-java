package net.diverse.ffa.villager;

import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.entity.Entity;

import net.minecraft.server.v1_8_R3.NBTTagCompound;

public class NoAi {
	
	public static void setNoAI(Entity entity) {
		net.minecraft.server.v1_8_R3.Entity nmsEnt = ((CraftEntity)entity).getHandle();
	    NBTTagCompound tag = new NBTTagCompound();
	    nmsEnt.c(tag);
	    tag.setInt("NoAI", 1);
	    nmsEnt.f(tag);
	}
}
