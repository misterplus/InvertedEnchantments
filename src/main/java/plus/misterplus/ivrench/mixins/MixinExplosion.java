package plus.misterplus.ivrench.mixins;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Explosion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import plus.misterplus.ivrench.common.enchantment.EnchantmentNoProtection;

import java.util.List;
import java.util.Set;

@Mixin(Explosion.class)
public abstract class MixinExplosion {

    public Entity entity;
    public double d10;

    @Inject(
            method = "doExplosionA",
            at = @At(
                    value = "INVOKE_ASSIGN",
                    target = "net/minecraft/enchantment/EnchantmentProtection.getBlastDamageReduction(Lnet/minecraft/entity/EntityLivingBase;D)D"
            ),
            locals = LocalCapture.CAPTURE_FAILHARD
    )
    private void injectDoExplosionA(CallbackInfo ci, Set set, int i, float f3, int k1, int l1, int i2, int i1, int j2, int j1, List list, Vec3d vec3d, int k2, Entity entity, double d12, double d5, double d7, double d9, double d13, double d14, double d10, double d11 ) {
        this.entity = entity;
        this.d10 = d10;
    }

    @ModifyVariable(
            method = "doExplosionA",
            at = @At(
                    value = "INVOKE_ASSIGN",
                    target = "net/minecraft/enchantment/EnchantmentProtection.getBlastDamageReduction(Lnet/minecraft/entity/EntityLivingBase;D)D"
            ),
            name = "d11"
    )
    private double modify_d11(double d11) {
        return EnchantmentNoProtection.getBlastDamageReduction((EntityLivingBase)this.entity, d10);
    }
}
