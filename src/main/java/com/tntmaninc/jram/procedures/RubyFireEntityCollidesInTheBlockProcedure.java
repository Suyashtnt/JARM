package com.tntmaninc.jram.procedures;

import net.minecraft.entity.Entity;

import com.tntmaninc.jram.JramModElements;

@JramModElements.ModElement.Tag
public class RubyFireEntityCollidesInTheBlockProcedure extends JramModElements.ModElement {
	public RubyFireEntityCollidesInTheBlockProcedure(JramModElements instance) {
		super(instance, 57);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure RubyFireEntityCollidesInTheBlock!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		entity.setFire((int) 15);
	}
}
