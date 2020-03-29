package plus.misterplus.ivrench.common.utils;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.util.ResourceLocation;

public class InvertedEnchantmentHelper {
    public static Enchantment getEnchantment(String name) {
        return Enchantment.REGISTRY.getObject(new ResourceLocation("ivrench", name));
    }
}
