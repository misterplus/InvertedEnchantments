package plus.misterplus.ivrench.common.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.init.Items;
import net.minecraft.item.ItemEnchantedBook;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import plus.misterplus.ivrench.InvertedEnchantments;

public class InvertedEnchantmentsCreativeTab extends CreativeTabs {

    public InvertedEnchantmentsCreativeTab() {
        super(InvertedEnchantments.MOD_ID);
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(Items.ENCHANTED_BOOK);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void displayAllRelevantItems(NonNullList<ItemStack> list) {
        for (Enchantment e : Enchantment.REGISTRY) {
            if ((InvertedEnchantments.MOD_ID.equals(e.getRegistryName().getNamespace()))) {
                ItemStack ebook = new ItemStack(Items.ENCHANTED_BOOK);
                ItemEnchantedBook.addEnchantment(ebook, new EnchantmentData(e, e.getMaxLevel()));
                ebook.setTranslatableName("item.ivrench.ivrench_book.name");
                list.add(ebook);
            }
        }
    }
}

