package plus.misterplus.ivrench.common.utils;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import plus.misterplus.ivrench.common.enchantment.IceMelterEnchantment;

import java.util.Map;

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
    public static int injectOnCollideWithPlayer(PlayerEntity entityIn, int xpValue){
        Map.Entry<EquipmentSlotType, ItemStack> entry = EnchantmentHelper.getRandomItemWithEnchantment(getEnchantment("unmending"), entityIn);

        if (entry != null) {
            ItemStack itemstack = entry.getValue();
            if (!itemstack.isEmpty()) {
                int i = Math.min((int)((float)xpValue * itemstack.getXpRepairRatio()), itemstack.getDamage());
                if (itemstack.getDamage() + i > itemstack.getMaxDamage()) {
                    itemstack.setCount(0);
                } else{
                    itemstack.setDamage(itemstack.getDamage() + (i == 0 ? 1 : i));
                }
                return i/2;
            }
        }
    return 0;
    }
}
