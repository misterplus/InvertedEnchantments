package plus.misterplus.ivrench.mixins;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import static plus.misterplus.ivrench.common.utils.InvertedEnchantmentHelper.getEnchantment;

@Mixin(CrossbowItem.class)
public abstract class MixinCrossbowItem {


    @Inject(
            method = "getChargeTime",
            at = @At("HEAD"),
            cancellable = true
    )
    private static void injectGetChargeTime(ItemStack p_220026_0_, CallbackInfoReturnable<Integer> cir) {
        int l = EnchantmentHelper.getEnchantmentLevel(getEnchantment("slow_charge"), p_220026_0_);
        if (l > 0) {
            cir.setReturnValue(25 + 5 * l);
        }
    }

    @Inject(
            method = "createArrow",
            at = @At(value = "INVOKE"),
            cancellable = true,
            locals = LocalCapture.CAPTURE_FAILSOFT
    )
    private static void injectCreateArrow(World p_220024_0_, LivingEntity p_220024_1_, ItemStack p_220024_2_, ItemStack p_220024_3_, CallbackInfoReturnable<AbstractArrowEntity> cir, ArrowItem lvt_4_1_, AbstractArrowEntity lvt_5_1_) {
        int l = EnchantmentHelper.getEnchantmentLevel(getEnchantment("unpiercing"), p_220024_2_);
        if (l > 0) {
            lvt_5_1_.addTag("ivrench_fake_arrow");
        }
    }
}

