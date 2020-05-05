package plus.misterplus.ivrench.common.utils;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import plus.misterplus.ivrench.common.enchantment.IceMelterEnchantment;

import static net.minecraft.enchantment.EnchantmentHelper.getEnchantmentLevel;
import static plus.misterplus.ivrench.common.utils.InvertedEnchantmentHelper.getEnchantment;

public class MixinMethods {

    public static float injectApplyPotionDamageCalculations(DamageSource source, float damage, CallbackInfoReturnable<Float> cir, int l) {
        if (l < 0) {
            return damage * MathHelper.clamp(damage, -20.0F, 0.0F);
        }
        return damage;
    }

    public static void injectFrostWalk(LivingEntity entityLivingBase, BlockPos pos) {
        int j = EnchantmentHelper.getMaxEnchantmentLevel(getEnchantment("ice_melter"), entityLivingBase);
        if (j > 0) {
            IceMelterEnchantment.freezeNearby(entityLivingBase, entityLivingBase.world, pos, j);
        }
    }

}
