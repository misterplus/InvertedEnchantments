package plus.misterplus.ivrench.common.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentProtection;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;

import static plus.misterplus.ivrench.InvertedEnchantments.APRIL_FOOLS;
import static plus.misterplus.ivrench.common.utils.InvertedEnchantmentHelper.getEnchantment;

public class EnchantmentNoProtection extends EnchantmentInverted {
    /**
     * Defines the type of protection of the enchantment, 0 = all, 1 = fire, 2 = fall (feather fall), 3 = explosion and
     * 4 = projectile.
     */
    public final EnchantmentProtection.Type protectionType;

    public EnchantmentNoProtection(Enchantment.Rarity rarityIn, EnchantmentProtection.Type protectionTypeIn, EntityEquipmentSlot... slots) {
        super(rarityIn, EnumEnchantmentType.ARMOR, slots);
        this.protectionType = protectionTypeIn;

        if (protectionTypeIn == EnchantmentProtection.Type.FALL) {
            this.type = EnumEnchantmentType.ARMOR_FEET;
        }
    }

    /**
     * Gets the amount of ticks an entity should be set fire, adjusted for fire protection.
     */
    public static int getFireTimeForEntity(EntityLivingBase p_92093_0_, int p_92093_1_) {
        int i = EnchantmentHelper.getMaxEnchantmentLevel(getEnchantment("fire_noprotection"), p_92093_0_);
        if (i > 0) {
            p_92093_1_ += MathHelper.floor((float) p_92093_1_ * (float) i * 0.15F);
        }
        return p_92093_1_;
    }

    public static double getBlastDamageReduction(EntityLivingBase entityLivingBaseIn, double damage) {
        int i = EnchantmentHelper.getMaxEnchantmentLevel(getEnchantment("blast_noprotection"), entityLivingBaseIn);

        if (i > 0) {
            damage += MathHelper.floor(damage * (double) ((float) i * 0.15F));
        }

        return damage;
    }

    /**
     * Returns the minimal value of enchantability needed on the enchantment level passed.
     */
    public int getMinEnchantability(int enchantmentLevel) {
        return this.protectionType.getMinimalEnchantability() + (enchantmentLevel - 1) * this.protectionType.getEnchantIncreasePerLevel();
    }

    /**
     * Returns the maximum value of enchantability nedded on the enchantment level passed.
     */
    public int getMaxEnchantability(int enchantmentLevel) {
        return this.getMinEnchantability(enchantmentLevel) + this.protectionType.getEnchantIncreasePerLevel();
    }

    /**
     * Returns the maximum level that the enchantment can have.
     */
    public int getMaxLevel() {
        return 4;
    }

    /**
     * Calculates the damage protection of the enchantment based on level and damage source passed.
     */
    public int calcModifierDamage(int level, DamageSource source) {
        if (source.canHarmInCreative()) {
            return 0;
        } else if (this.protectionType == EnchantmentProtection.Type.ALL) {
            return level * -1;
        } else if (this.protectionType == EnchantmentProtection.Type.FIRE && source.isFireDamage()) {
            return level * -2;
        } else if (this.protectionType == EnchantmentProtection.Type.FALL && source == DamageSource.FALL) {
            return level * -3;
        } else if (this.protectionType == EnchantmentProtection.Type.EXPLOSION && source.isExplosion()) {
            return level * -2;
        } else {
            return this.protectionType == EnchantmentProtection.Type.PROJECTILE && source.isProjectile() ? level * -2 : 0;
        }
    }

    /**
     * Return the name of key in translation table of this enchantment.
     */
    public String getName() {
        return APRIL_FOOLS ? "enchantment.protect." + this.protectionType.getTypeName() : "enchantment.noprotect." + this.protectionType.getTypeName();
    }

    /**
     * Determines if the enchantment passed can be applyied together with this enchantment.
     */
    public boolean canApplyTogether(Enchantment ench) {
        if (ench instanceof EnchantmentNoProtection) {
            EnchantmentNoProtection enchantmentnoprotection = (EnchantmentNoProtection) ench;

            if (this.protectionType == enchantmentnoprotection.protectionType) {
                return false;
            } else {
                return this.protectionType == EnchantmentProtection.Type.FALL || enchantmentnoprotection.protectionType == EnchantmentProtection.Type.FALL;
            }
        } else if (ench instanceof EnchantmentProtection) {
            EnchantmentProtection enchantmentprotection = (EnchantmentProtection) ench;

            if (this.protectionType == enchantmentprotection.protectionType) {
                return false;
            } else {
                return this.protectionType == EnchantmentProtection.Type.FALL || enchantmentprotection.protectionType == EnchantmentProtection.Type.FALL;
            }
        } else {
            return super.canApplyTogether(ench);
        }
    }
}
