package plus.misterplus.ivrench.common.enchantment;

import net.minecraft.enchantment.*;
import net.minecraft.inventory.EquipmentSlotType;

public class BounceEnchantment extends InvertedEnchantmentBase   {
    public BounceEnchantment(Rarity p_i48786_1_, EquipmentSlotType... p_i48786_2_) {
        super(p_i48786_1_, EnchantmentType.TRIDENT, p_i48786_2_);
    }

    public int getMinEnchantability(int p_77321_1_) {
        return 10 + p_77321_1_ * 7;
    }

    public int getMaxEnchantability(int p_223551_1_) {
        return 50;
    }

    public int getMaxLevel() {
        return 3;
    }

    public boolean canApplyTogether(Enchantment ench) {
        if (ench instanceof RiptideEnchantment || ench instanceof LoyaltyEnchantment ||ench instanceof BetrayEnchantment || ench instanceof ChannelingEnchantment ||ench instanceof SelfChannelingEnchantment )
            return false;
        else
            return super.canApplyTogether(ench);
    }
}
