package plus.misterplus.ivrench.common.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentDurability;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

import java.util.Random;

public class EnchantmentNoDurability extends Enchantment {

    public EnchantmentNoDurability(Enchantment.Rarity rarityIn, EntityEquipmentSlot... slots)
    {
        super(rarityIn, EnumEnchantmentType.BREAKABLE, slots);
    }

    /**
     * Returns the minimal value of enchantability needed on the enchantment level passed.
     */
    public int getMinEnchantability(int enchantmentLevel)
    {
        return 5 + (enchantmentLevel - 1) * 8;
    }

    /**
     * Returns the maximum value of enchantability nedded on the enchantment level passed.
     */
    public int getMaxEnchantability(int enchantmentLevel)
    {
        return super.getMinEnchantability(enchantmentLevel) + 50;
    }

    /**
     * Returns the maximum level that the enchantment can have.
     */
    public int getMaxLevel()
    {
        return 3;
    }

    /**
     * Determines if this enchantment can be applied to a specific ItemStack.
     */
    public boolean canApply(ItemStack stack)
    {
        return stack.isItemStackDamageable() || super.canApply(stack);
    }

    public boolean canApplyTogether(Enchantment ench) {
        if (ench instanceof EnchantmentDurability)
            return false;
        else
            return super.canApplyTogether(ench);
    }

    /**
     * Used by ItemStack.attemptDamageItem. Randomly determines if a point of damage should be negated using the
     * enchantment level (par1). If the ItemStack is Armor then there is a flat 60% chance for damage to be negated no
     * matter the enchantment level, otherwise there is a 1-(par/1) chance for damage to be negated.
     */
    public static boolean negateDamage(ItemStack stack, int level, Random rand)
    {
        if (stack.getItem() instanceof ItemArmor && rand.nextFloat() < 0.6F)
        {
            return false;
        }
        else
        {
            return rand.nextInt(level + 1) > 0;
        }
    }
}
