
package com.tntmaninc.jram.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.world.World;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.block.BlockState;

import com.tntmaninc.jram.procedures.StrengthendDiamondItemIsCraftedsmeltedProcedure;
import com.tntmaninc.jram.procedures.StrengthendDiamondItemInHandTickProcedure;
import com.tntmaninc.jram.itemgroup.JARMCreativeTabItemGroup;
import com.tntmaninc.jram.JramModElements;

@JramModElements.ModElement.Tag
public class StrengthendDiamondItem extends JramModElements.ModElement {
	@ObjectHolder("jram:strengthend_diamond")
	public static final Item block = null;
	public StrengthendDiamondItem(JramModElements instance) {
		super(instance, 28);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}
	public static class ItemCustom extends Item {
		public ItemCustom() {
			super(new Item.Properties().group(JARMCreativeTabItemGroup.tab).maxStackSize(64));
			setRegistryName("strengthend_diamond");
		}

		@Override
		public int getItemEnchantability() {
			return 0;
		}

		@Override
		public int getUseDuration(ItemStack itemstack) {
			return 0;
		}

		@Override
		public float getDestroySpeed(ItemStack par1ItemStack, BlockState par2Block) {
			return 1F;
		}

		@Override
		public void onCreated(ItemStack itemstack, World world, PlayerEntity entity) {
			super.onCreated(itemstack, world, entity);
			int x = (int) entity.getPosX();
			int y = (int) entity.getPosY();
			int z = (int) entity.getPosZ();
			{
				java.util.HashMap<String, Object> $_dependencies = new java.util.HashMap<>();
				$_dependencies.put("entity", entity);
				StrengthendDiamondItemIsCraftedsmeltedProcedure.executeProcedure($_dependencies);
			}
		}

		@Override
		public void inventoryTick(ItemStack itemstack, World world, Entity entity, int slot, boolean selected) {
			super.inventoryTick(itemstack, world, entity, slot, selected);
			int x = (int) entity.getPosX();
			int y = (int) entity.getPosY();
			int z = (int) entity.getPosZ();
			if (selected) {
				java.util.HashMap<String, Object> $_dependencies = new java.util.HashMap<>();
				$_dependencies.put("entity", entity);
				StrengthendDiamondItemInHandTickProcedure.executeProcedure($_dependencies);
			}
		}
	}
}
