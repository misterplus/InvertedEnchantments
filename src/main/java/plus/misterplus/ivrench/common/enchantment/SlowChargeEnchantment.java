package plus.misterplus.ivrench.common.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.enchantment.QuickChargeEnchantment;
import net.minecraft.enchantment.RespirationEnchantment;
import net.minecraft.inventory.EquipmentSlotType;

public class SlowChargeEnchantment extends InvertedEnchantmentBase {
    public SlowChargeEnchantment(Enchantment.Rarity rarityIn, EquipmentSlotType... slots) {
        super(rarityIn, EnchantmentType.CROSSBOW, slots);
    }

    /**
     * Returns the minimal value of enchantability needed on the enchantment level passed.
     */
    public int getMinEnchantability(int enchantmentLevel) {
        return 12 + enchantmentLevel * 20;
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
        return 3;
    }

    public boolean canApplyTogether(Enchantment ench) {
        return !(ench instanceof QuickChargeEnchantment) && super.canApplyTogether(ench);
    }
}
