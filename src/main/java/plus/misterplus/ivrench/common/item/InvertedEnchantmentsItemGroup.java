package plus.misterplus.ivrench.common.item;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.item.*;

import net.minecraft.util.NonNullList;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.OnlyIn;
import plus.misterplus.ivrench.InvertedEnchantments;

import javax.annotation.Nonnull;


public class InvertedEnchantmentsItemGroup extends ItemGroup {

    public InvertedEnchantmentsItemGroup() {
        super(InvertedEnchantments.MOD_ID);
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(Items.ENCHANTED_BOOK);
    }

    @Override
    public void fill(@Nonnull NonNullList<ItemStack> list) {
        for (Enchantment e : Registry.ENCHANTMENT ) {
            if ((InvertedEnchantments.MOD_ID.equals(e.getRegistryName().getNamespace()))) {
                ItemStack ebook = new ItemStack(Items.ENCHANTED_BOOK);
                EnchantedBookItem.addEnchantment(ebook, new EnchantmentData(e, e.getMaxLevel()));
                ebook.setDisplayName(new TranslationTextComponent("item.ivrench.ivrench_book.name" ));
                list.add(ebook);
            }
        }
    }
}

