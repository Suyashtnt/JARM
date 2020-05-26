
package com.tntmaninc.jram.item;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.Capability;

import net.minecraft.world.World;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.Hand;
import net.minecraft.util.Direction;
import net.minecraft.util.ActionResult;
import net.minecraft.network.PacketBuffer;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.inventory.container.Container;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.block.BlockState;

import javax.annotation.Nullable;
import javax.annotation.Nonnull;

import java.util.List;

import io.netty.buffer.Unpooled;

import com.tntmaninc.jram.itemgroup.JARMCreativeTabItemGroup;
import com.tntmaninc.jram.gui.RubyToolkitGUIGui;
import com.tntmaninc.jram.JramModElements;

@JramModElements.ModElement.Tag
public class RubyToolkitItem extends JramModElements.ModElement {
	@ObjectHolder("jram:ruby_toolkit")
	public static final Item block = null;
	public RubyToolkitItem(JramModElements instance) {
		super(instance, 64);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}
	public static class ItemCustom extends Item {
		public ItemCustom() {
			super(new Item.Properties().group(JARMCreativeTabItemGroup.tab).maxStackSize(1));
			setRegistryName("ruby_toolkit");
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
			list.add(new StringTextComponent("Holds all your ruby items"));
		}

		@Override
		public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity entity, Hand hand) {
			ActionResult<ItemStack> ar = super.onItemRightClick(world, entity, hand);
			ItemStack itemstack = ar.getResult();
			int x = (int) entity.getPosX();
			int y = (int) entity.getPosY();
			int z = (int) entity.getPosZ();
			if (entity instanceof ServerPlayerEntity) {
				NetworkHooks.openGui((ServerPlayerEntity) entity, new INamedContainerProvider() {
					@Override
					public ITextComponent getDisplayName() {
						return new StringTextComponent("Ruby Toolkit");
					}

					@Override
					public Container createMenu(int id, PlayerInventory inventory, PlayerEntity player) {
						PacketBuffer packetBuffer = new PacketBuffer(Unpooled.buffer());
						packetBuffer.writeBlockPos(new BlockPos(x, y, z));
						packetBuffer.writeByte(hand == Hand.MAIN_HAND ? 0 : 1);
						return new RubyToolkitGUIGui.GuiContainerMod(id, inventory, packetBuffer);
					}
				}, buf -> {
					buf.writeBlockPos(new BlockPos(x, y, z));
					buf.writeByte(hand == Hand.MAIN_HAND ? 0 : 1);
				});
			}
			return ar;
		}

		@Override
		public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundNBT compound) {
			return new InventoryCapability();
		}
	}

	private static class InventoryCapability implements ICapabilitySerializable<CompoundNBT> {
		private final LazyOptional<ItemStackHandler> inventory = LazyOptional.of(this::createItemHandler);
		@Override
		public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> capability, @Nullable Direction side) {
			return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY ? this.inventory.cast() : LazyOptional.empty();
		}

		@Override
		public CompoundNBT serializeNBT() {
			return getItemHandler().serializeNBT();
		}

		@Override
		public void deserializeNBT(CompoundNBT nbt) {
			getItemHandler().deserializeNBT(nbt);
		}

		private ItemStackHandler createItemHandler() {
			return new ItemStackHandler(6) {
				@Override
				public int getSlotLimit(int slot) {
					return 64;
				}

				@Override
				public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
					return stack.getItem() != block;
				}
			};
		}

		private ItemStackHandler getItemHandler() {
			return inventory.orElseThrow(RuntimeException::new);
		}
	}
}
