
package com.tntmaninc.jram.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.world.World;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.entity.Entity;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.block.BlockState;

import java.util.List;

import com.tntmaninc.jram.procedures.RubyNuggetItemInHandTickProcedure;
import com.tntmaninc.jram.itemgroup.JARMCreativeTabItemGroup;
import com.tntmaninc.jram.JramModElements;

@JramModElements.ModElement.Tag
public class RubyNuggetItem extends JramModElements.ModElement {
	@ObjectHolder("jram:ruby_nugget")
	public static final Item block = null;
	public RubyNuggetItem(JramModElements instance) {
		super(instance, 38);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}
	public static class ItemCustom extends Item {
		public ItemCustom() {
			super(new Item.Properties().group(JARMCreativeTabItemGroup.tab).maxStackSize(64));
			setRegistryName("ruby_nugget");
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
		public void addInformation(ItemStack itemstack, World world, List<ITextComponent> list, ITooltipFlag flag) {
			super.addInformation(itemstack, world, list, flag);
			list.add(new StringTextComponent("seems to have weakening properties"));
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
				RubyNuggetItemInHandTickProcedure.executeProcedure($_dependencies);
			}
		}
	}
}
