package plus.misterplus.ivrench.common.utils;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import plus.misterplus.ivrench.common.enchantment.EnchantmentIceMelter;

import java.util.Random;

import static net.minecraft.enchantment.EnchantmentHelper.getEnchantmentLevel;
import static net.minecraft.enchantment.EnchantmentHelper.getMaxEnchantmentLevel;
import static plus.misterplus.ivrench.common.utils.InvertedEnchantmentHelper.getEnchantment;

public class MixinMethods {
    public static void injectDecreaseAirSupply(int air, CallbackInfoReturnable<Integer> cir, Random rand, EntityLivingBase entityLivingBase) {
        int j = getMaxEnchantmentLevel(getEnchantment("suffocation"), entityLivingBase);
        if (j > 0) {
            cir.setReturnValue(rand.nextInt(j + 1) > 0 ? air - j - 1 : air - 1);
        }
    }

    public static float injectApplyPotionDamageCalculations(DamageSource source, float damage, CallbackInfoReturnable<Float> cir, int l) {
        if (l < 0)
        {
            return damage * MathHelper.clamp(damage, -20.0F, 0.0F);
        }
        return damage;
    }

    public static void injectFrostWalk(EntityLivingBase entityLivingBase, BlockPos pos) {
        int j = EnchantmentHelper.getMaxEnchantmentLevel(getEnchantment("ice_melter"), entityLivingBase);

        if (j > 0)
        {
            EnchantmentIceMelter.freezeNearby(entityLivingBase, entityLivingBase.world, pos, j);
        }
    }

    public static void injectOnItemRightClick(ItemStack itemstack, EntityFishHook entityfishhook) {
        int l = getEnchantmentLevel(getEnchantment("lootLessFishing"), itemstack);

        if (l > 0)
        {
            entityfishhook.setLuck(-l);
        }

        int j = getEnchantmentLevel(getEnchantment("lureless"), itemstack);

        if (j > 0)
        {
            entityfishhook.setLureSpeed(-j);
        }
    }
}
