package plus.misterplus.ivrench.mixins;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import plus.misterplus.ivrench.common.utils.MixinMethods;

@Mixin(ItemFishingRod.class)
public abstract class MixinItemFishingRod {
    @Inject(
            method = "onItemRightClick",
            at = @At(
                    value = "INVOKE_ASSIGN",
                    target = "net/minecraft/enchantment/EnchantmentHelper.getFishingLuckBonus(Lnet/minecraft/item/ItemStack;)I"
            ),
            locals = LocalCapture.CAPTURE_FAILHARD
    )
    private void injectOnItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn, CallbackInfoReturnable<ActionResult<ItemStack>> cir, ItemStack itemstack, EntityFishHook entityfishhook) {
        MixinMethods.injectOnItemRightClick(itemstack, entityfishhook);
    }
}
