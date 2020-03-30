package plus.misterplus.ivrench.mixins;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.EnumHand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import static net.minecraft.enchantment.EnchantmentHelper.getMaxEnchantmentLevel;
import static plus.misterplus.ivrench.common.utils.InvertedEnchantmentHelper.getEnchantment;

@Mixin(EntityPlayer.class)
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
        ItemStack itemstack = ((EntityLivingBase) (Object) this).getHeldItem(EnumHand.MAIN_HAND);
        if (itemstack.getItem() instanceof ItemSword) {
            int level = getMaxEnchantmentLevel(getEnchantment("edgeless"), ((EntityLivingBase) (Object) this));
            if (level > 0) {
                return ((EntityLivingBase) (Object) this).getEntityWorld().rand.nextInt(level + 1) == 0;
            }
        }
        return flag3;
    }
}
