package plus.misterplus.ivrench.mixins;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import plus.misterplus.ivrench.common.utils.MixinMethods;


@Mixin(LivingEntity.class)
public abstract class MixinLivingEntity extends Entity {
    public MixinLivingEntity(EntityType<?> p_i48580_1_, World p_i48580_2_) {
        super(p_i48580_1_, p_i48580_2_);
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
            at = @At("HEAD"),
            cancellable = true
    )
    private void injectFrostWalk(BlockPos pos, CallbackInfo ci) {
        MixinMethods.injectFrostWalk((LivingEntity) (Object) this, pos);
    }
}
