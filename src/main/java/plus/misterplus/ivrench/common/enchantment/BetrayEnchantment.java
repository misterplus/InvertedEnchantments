package plus.misterplus.ivrench.common.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;


public class BetrayEnchantment extends InvertedEnchantmentBase {
    public BetrayEnchantment(Rarity p_i48785_1_, EquipmentSlotType... p_i48785_2_) {
        super(p_i48785_1_, EnchantmentType.TRIDENT, p_i48785_2_);
    }

    public int getMinEnchantability(int p_77321_1_) {
        return 5 + p_77321_1_ * 7;
    }

    public int getMaxEnchantability(int p_223551_1_) {
        return 50;
    }

    public int getMaxLevel() {
        return 3;
    }

    public boolean canApplyTogether(Enchantment p_77326_1_) {
        return super.canApplyTogether(p_77326_1_);
    }
}
