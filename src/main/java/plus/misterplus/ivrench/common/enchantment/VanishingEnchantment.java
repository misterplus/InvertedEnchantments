package plus.misterplus.ivrench.common.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.enchantment.VanishingCurseEnchantment;
import net.minecraft.inventory.EquipmentSlotType;

public class VanishingEnchantment extends InvertedEnchantmentBase {
    public VanishingEnchantment(Enchantment.Rarity p_i47252_1_, EquipmentSlotType... p_i47252_2_) {
        super(p_i47252_1_, EnchantmentType.ALL, p_i47252_2_);
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
        if (ench instanceof VanishingCurseEnchantment)
            return false;
        else
            return super.canApplyTogether(ench);
    }
}
