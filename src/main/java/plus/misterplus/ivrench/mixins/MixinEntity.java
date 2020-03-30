package plus.misterplus.ivrench.mixins;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import plus.misterplus.ivrench.common.enchantment.EnchantmentNoProtection;

@Mixin(Entity.class)
public abstract class MixinEntity {

    @ModifyVariable(
            method = "setFire",
            at = @At(
                    value = "INVOKE_ASSIGN",
                    target = "net/minecraft/enchantment/EnchantmentProtection.getFireTimeForEntity(Lnet/minecraft/entity/EntityLivingBase;I)I"
            ),
            name = "i"
    )
    private int modify_i(int i) {
        return EnchantmentNoProtection.getFireTimeForEntity((EntityLivingBase) (Object) this, i);
    }
}
