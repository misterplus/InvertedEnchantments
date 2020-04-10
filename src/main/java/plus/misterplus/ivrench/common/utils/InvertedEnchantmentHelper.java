package plus.misterplus.ivrench.common.utils;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import plus.misterplus.ivrench.InvertedEnchantments;

public class InvertedEnchantmentHelper {
    public static Enchantment getEnchantment(String name) {
        return Registry.ENCHANTMENT.getOrDefault(new ResourceLocation(InvertedEnchantments.MOD_ID, name));
//        return Enchantment.REGISTRY.getObject(new ResourceLocation("ivrench", name));
    }

    public static int getCurrentLevelTool(ItemStack stack,Enchantment ench) {
        if (!stack.isEmpty() && EnchantmentHelper.getEnchantments(stack).containsKey(ench))
            return EnchantmentHelper.getEnchantments(stack).get(ench);
        return -1;
    }

}
