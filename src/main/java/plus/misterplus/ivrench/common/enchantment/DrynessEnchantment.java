package plus.misterplus.ivrench.common.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.enchantment.ImpalingEnchantment;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.inventory.EquipmentSlotType;

public class DrynessEnchantment extends InvertedEnchantmentBase {
    public DrynessEnchantment(Enchantment.Rarity p_i47366_1_, EquipmentSlotType... p_i47366_2_) {
        super(p_i47366_1_, EnchantmentType.WEAPON, p_i47366_2_);
    }

    public int getMinEnchantability(int p_77321_1_) {
        return 1 + (p_77321_1_ - 1) * 8;
    }

    public int getMaxEnchantability(int p_223551_1_) {
        return this.getMinEnchantability(p_223551_1_) + 20;
    }

    public int getMaxLevel() {
        return 5;
    }

    public boolean canApplyTogether(Enchantment ench) {
        if (ench instanceof ImpalingEnchantment)
            return false;
        else
            return super.canApplyTogether(ench);
    }

    public float calcDamageByCreature(int p_152376_1_, CreatureAttribute p_152376_2_) {
        return p_152376_2_ == CreatureAttribute.WATER ? (float)p_152376_1_ * -2.5F : 0.0F;
    }
}
