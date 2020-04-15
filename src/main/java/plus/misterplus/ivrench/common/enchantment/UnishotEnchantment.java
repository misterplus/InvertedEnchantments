package plus.misterplus.ivrench.common.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.enchantment.MultishotEnchantment;
import net.minecraft.enchantment.PiercingEnchantment;
import net.minecraft.inventory.EquipmentSlotType;

public class UnishotEnchantment extends InvertedEnchantmentBase {
    public UnishotEnchantment(Rarity rarityIn, EquipmentSlotType[] slots) {
        super(rarityIn, EnchantmentType.CROSSBOW, slots);
    }

    public int getMinEnchantability(int enchantmentLevel) {
        return 20;
    }

    public int getMaxEnchantability(int enchantmentLevel) {
        return 50;
    }

    public int getMaxLevel() {
        return 1;
    }

    public boolean canApplyTogether(Enchantment ench) {
        return !(ench instanceof MultishotEnchantment || ench instanceof PiercingEnchantment || ench instanceof NoPiercingEnchantment ) && super.canApplyTogether(ench);
    }

}
