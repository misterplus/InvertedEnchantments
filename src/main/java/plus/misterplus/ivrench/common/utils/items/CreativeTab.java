package plus.misterplus.ivrench.common.utils.items;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemEnchantedBook;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import plus.misterplus.ivrench.InvertedEnchantments;


public class CreativeTab extends CreativeTabs  {
	Item tabItem = null;
	Comparator<ItemStack> comparator = new Comparator<ItemStack>() {

		@Override
		public int compare(final ItemStack first, final ItemStack second) {
			return first.getDisplayName().compareTo(second.getDisplayName());
		}
	};

	public CreativeTab() {
		super(InvertedEnchantments.MOD_ID);
	}

	@Override
	public ItemStack createIcon() {
		return new ItemStack(Items.ENCHANTED_BOOK);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void displayAllRelevantItems(NonNullList<ItemStack> list) {
		super.displayAllRelevantItems(list);
		Iterator<ItemStack> i = list.iterator();
		while (i.hasNext()) {
			Item s = i.next().getItem();
			if (s == Items.ENCHANTED_BOOK) {
				i.remove();
			}
		}
		Collections.sort(list, comparator);
		for (Enchantment e : Enchantment.REGISTRY) {
			if((e.getRegistryName().getNamespace().equalsIgnoreCase("ivrench"))){
				ItemStack ebook = new ItemStack(Items.ENCHANTED_BOOK);
				ItemEnchantedBook.addEnchantment(ebook, new EnchantmentData(e, e.getMaxLevel()));
				ebook.setTranslatableName("item.ivrench.ivrench_book.name");
				list.add(ebook);
			}
		}

	}

	public void setTabItemIfNull(Item i) {
		if (tabItem == null)
			tabItem = i;
	}
}

