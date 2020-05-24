
package com.tntmaninc.jram.itemgroup;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;

import com.tntmaninc.jram.item.RubyItem;
import com.tntmaninc.jram.JramModElements;

@JramModElements.ModElement.Tag
public class JARMCreativeTabItemGroup extends JramModElements.ModElement {
	public JARMCreativeTabItemGroup(JramModElements instance) {
		super(instance, 24);
	}

	@Override
	public void initElements() {
		tab = new ItemGroup("tabjarm_creative_tab") {
			@OnlyIn(Dist.CLIENT)
			@Override
			public ItemStack createIcon() {
				return new ItemStack(RubyItem.block, (int) (1));
			}

			@OnlyIn(Dist.CLIENT)
			public boolean hasSearchBar() {
				return false;
			}
		};
	}
	public static ItemGroup tab;
}
