package com.tntmaninc.jram.procedures;

import net.minecraft.potion.Effects;
import net.minecraft.potion.EffectInstance;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import com.tntmaninc.jram.JramModElements;

@JramModElements.ModElement.Tag
public class StrengthendDiamondItemInHandTickProcedure extends JramModElements.ModElement {
	public StrengthendDiamondItemInHandTickProcedure(JramModElements instance) {
		super(instance, 65);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure StrengthendDiamondItemInHandTick!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (entity instanceof LivingEntity)
			((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.SLOWNESS, (int) 1, (int) 2));
	}
}
