package plus.misterplus.ivrench.mixins;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import static net.minecraft.enchantment.EnchantmentHelper.getMaxEnchantmentLevel;
import static plus.misterplus.ivrench.common.utils.InvertedEnchantmentHelper.getEnchantment;

@Mixin(PlayerEntity.class)
public abstract class MixinEntityPlayer {
    @ModifyVariable(
            method = "attackTargetEntityWithCurrentItem",
            at = @At(
                    value = "INVOKE_ASSIGN",
                    target = "net/minecraft/entity/Entity.attackEntityFrom(Lnet/minecraft/util/DamageSource;F)Z"
            ),
            name = "flag3"
    )
    private boolean modify_flag3(boolean flag3) {
        ItemStack itemstack = ((LivingEntity) (Object) this).getHeldItem(Hand.MAIN_HAND);
        if (itemstack.getItem() instanceof SwordItem) {
            int level = getMaxEnchantmentLevel(getEnchantment("edgeless"), ((LivingEntity) (Object) this));
            if (level > 0) {
                return ((LivingEntity) (Object) this).getEntityWorld().rand.nextInt(level + 1) == 0;
            }
        }
        return flag3;
    }
}
