package plus.misterplus.ivrench.mixins;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import plus.misterplus.ivrench.common.enchantment.NoProtectionEnchantment;

@Mixin(Entity.class)
public abstract class MixinEntity {

    @ModifyVariable(
            method = "setFire",
            at = @At(
                    value = "INVOKE_ASSIGN",
                    target = "Lnet/minecraft/enchantment/ProtectionEnchantment;getFireTimeForEntity(Lnet/minecraft/entity/LivingEntity;I)I"
            ),
            name = "i"
    )
    private int modify_i(int i) {
        return NoProtectionEnchantment.getFireTimeForEntity((LivingEntity) (Object) this, i);
    }
}
