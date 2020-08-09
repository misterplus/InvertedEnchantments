package plus.misterplus.ivrench.common.utils;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import plus.misterplus.ivrench.common.enchantment.IceMelterEnchantment;

import java.util.List;
import java.util.Map;
import java.util.Random;

import static plus.misterplus.ivrench.common.utils.InvertedEnchantmentHelper.getEnchantment;

public class MixinMethods {

    public static float injectApplyPotionDamageCalculations(DamageSource source, float damage, CallbackInfoReturnable<Float> cir, int l) {
        if (l < 0) {
            return damage + Math.abs(l);
        }
        return damage;
    }

    public static void injectFrostWalk(LivingEntity entityLivingBase, BlockPos pos) {
        int j = EnchantmentHelper.getMaxEnchantmentLevel(getEnchantment("ice_melter"), entityLivingBase);
        if (j > 0) {
            IceMelterEnchantment.freezeNearby(entityLivingBase, entityLivingBase.world, pos, j);
        }
    }

    public static int injectOnCollideWithPlayer(PlayerEntity entityIn, int xpValue) {
        Map.Entry<EquipmentSlotType, ItemStack> entry = EnchantmentHelper.getRandomItemWithEnchantment(getEnchantment("unmending"), entityIn);

        if (entry != null) {
            ItemStack itemstack = entry.getValue();
            if (!itemstack.isEmpty()) {
                int i = Math.min((int) ((float) xpValue * itemstack.getXpRepairRatio()), itemstack.getDamage());
                if (itemstack.getDamage() + i > itemstack.getMaxDamage()) {
                    itemstack.setCount(0);
                } else {
                    itemstack.setDamage(itemstack.getDamage() + (i == 0 ? 1 : i));
                }
                return i / 2;
            }
        }
        return 0;
    }

    public static List<ItemStack> injectBlockDrop(Entity p_220077_4_, List<ItemStack> returnValue) {
        if (p_220077_4_ != null && !(p_220077_4_ instanceof ArrowEntity)) {
            int l = EnchantmentHelper.getMaxEnchantmentLevel(getEnchantment("loot_less_digger"), (LivingEntity) p_220077_4_);
            if (l > 0) {
                for (ItemStack itemStack : returnValue) {
                    int count = itemStack.getCount();
                    Random r = new Random();
                    for (int i = 1; i <= l; i++) {
                        if (r.nextInt(i) >= 1) {
                            count = count / i;
                        }
                    }
                    itemStack.setCount(Math.max(1, count));
                }
            }
        }
        return returnValue;
    }
}
