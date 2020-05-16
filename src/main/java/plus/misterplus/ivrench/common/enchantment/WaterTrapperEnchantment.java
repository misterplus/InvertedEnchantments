package plus.misterplus.ivrench.common.enchantment;

import net.minecraft.enchantment.*;
import net.minecraft.inventory.EquipmentSlotType;

public class WaterTrapperEnchantment extends InvertedEnchantmentBase {
    public WaterTrapperEnchantment(Enchantment.Rarity rarityIn, EquipmentSlotType... slots) {
        super(rarityIn, EnchantmentType.ARMOR_FEET, slots);
    }

    /**
     * Returns the minimal value of enchantability needed on the enchantment level passed.
     */
    public int getMinEnchantability(int enchantmentLevel) {
        return enchantmentLevel * 10;
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

    /**
     * Determines if the enchantment passed can be applyied together with this enchantment.
     */
    public boolean canApplyTogether(Enchantment ench) {
        if (ench instanceof DepthStriderEnchantment || ench instanceof IceMelterEnchantment || ench instanceof FrostWalkerEnchantment)
            return false;
        else
            return super.canApplyTogether(ench);
    }
}
