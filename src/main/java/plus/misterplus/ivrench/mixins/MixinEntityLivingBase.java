package plus.misterplus.ivrench.mixins;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import plus.misterplus.ivrench.common.utils.MixinMethods;

@Mixin(EntityLivingBase.class)
public abstract class MixinEntityLivingBase extends Entity {

    public MixinEntityLivingBase(World worldIn) {
        super(worldIn);
    }

    @Inject(
            method = "decreaseAirSupply",
            at = @At("HEAD"),
            cancellable = true
    )
    private void injectDecreaseAirSupply(int air, CallbackInfoReturnable<Integer> cir) {
        MixinMethods.injectDecreaseAirSupply(air, cir, this.rand, (EntityLivingBase) (Object) this);
    }

    @Inject(
            method = "applyPotionDamageCalculations",
            at = @At(
                    value = "RETURN",
                    ordinal = 2
            )
    )
    private void injectApplyPotionDamageCalculations(DamageSource source, float damage, CallbackInfoReturnable<Float> cir) {
        damage = MixinMethods.injectApplyPotionDamageCalculations(source, damage, cir, EnchantmentHelper.getEnchantmentModifierDamage(this.getArmorInventoryList(), source));
    }

    @Inject(
            method = "frostWalk",
            at = @At("HEAD")
    )
    private void injectFrostWalk(BlockPos pos, CallbackInfo ci) {
        MixinMethods.injectFrostWalk((EntityLivingBase) (Object) this, pos);
    }
}
