package plus.misterplus.ivrench.common.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentVanishingCurse;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;

public class EnchantmentVanishing extends EnchantmentInverted {
    public EnchantmentVanishing(Enchantment.Rarity p_i47252_1_, EntityEquipmentSlot... p_i47252_2_) {
        super(p_i47252_1_, EnumEnchantmentType.ALL, p_i47252_2_);
    }

    /**
     * Returns the minimal value of enchantability needed on the enchantment level passed.
     */
    public int getMinEnchantability(int enchantmentLevel) {
        return 25;
    }

    /**
     * Returns the maximum value of enchantability nedded on the enchantment level passed.
     */
    public int getMaxEnchantability(int enchantmentLevel) {
        return 50;
    }

    /**
     * Returns the maximum level that the enchantment can have.
     */
    public int getMaxLevel() {
        return 1;
    }

    public boolean isTreasureEnchantment() {
        return true;
    }

    public boolean isCurse() {
        return true;
    }

    public boolean canApplyTogether(Enchantment ench) {
        if (ench instanceof EnchantmentVanishingCurse)
            return false;
        else
            return super.canApplyTogether(ench);
    }
}
