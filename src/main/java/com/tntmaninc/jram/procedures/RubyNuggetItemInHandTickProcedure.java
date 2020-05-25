package com.tntmaninc.jram.procedures;

import net.minecraft.potion.Effects;
import net.minecraft.potion.EffectInstance;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import com.tntmaninc.jram.JramModElements;

@JramModElements.ModElement.Tag
public class RubyNuggetItemInHandTickProcedure extends JramModElements.ModElement {
	public RubyNuggetItemInHandTickProcedure(JramModElements instance) {
		super(instance, 41);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure RubyNuggetItemInHandTick!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (entity instanceof LivingEntity)
			((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.WEAKNESS, (int) 1, (int) 1));
	}
}
