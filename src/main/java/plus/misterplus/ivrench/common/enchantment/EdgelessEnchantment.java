package plus.misterplus.ivrench.common.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.enchantment.SweepingEnchantment;
import net.minecraft.inventory.EquipmentSlotType;

public class EdgelessEnchantment extends InvertedEnchantmentBase {
    public EdgelessEnchantment(Enchantment.Rarity p_i47366_1_, EquipmentSlotType... p_i47366_2_) {
        super(p_i47366_1_, EnchantmentType.WEAPON, p_i47366_2_);
    }


    /**
     * Returns the minimal value of enchantability needed on the enchantment level passed.
     */
    public int getMinEnchantability(int enchantmentLevel) {
        return 5 + (enchantmentLevel - 1) * 9;
    }

    /**
     * Returns the maximum value of enchantability nedded on the enchantment level passed.
     */
    public int getMaxEnchantability(int enchantmentLevel) {
        return this.getMinEnchantability(enchantmentLevel) + 15;
    }

    /**
     * Returns the maximum level that the enchantment can have.
     */
    public int getMaxLevel() {
        return 3;
    }

    public boolean canApplyTogether(Enchantment ench) {
        if (ench instanceof SweepingEnchantment)
            return false;
        else
            return super.canApplyTogether(ench);
    }
}
