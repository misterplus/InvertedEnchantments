package plus.misterplus.ivrench.mixins;

import net.minecraft.dispenser.IBlockSource;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.minecraft.enchantment.EnchantmentHelper.getEnchantmentLevel;
import static plus.misterplus.ivrench.common.utils.InvertedEnchantmentHelper.getEnchantment;

@Mixin(ItemArmor.class)
public abstract class MixinItemArmor {
    @Inject(
            method = "dispenseArmor",
            at = @At("HEAD"),
            cancellable = true
    )
    private static void injectDispenseArmor(IBlockSource blockSource, ItemStack stack, CallbackInfoReturnable<ItemStack> cir) {
        if (getEnchantmentLevel(getEnchantment("unbinding_curse"), stack) > 0) {
            cir.setReturnValue(ItemStack.EMPTY);
        }
    }

    @Inject(
            method = "onItemRightClick",
            at = @At("HEAD"),
            cancellable = true
    )
    private void injectOnItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn, CallbackInfoReturnable<ActionResult<ItemStack>> cir) {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        if (getEnchantmentLevel(getEnchantment("unbinding_curse"), itemstack) > 0) {
            cir.setReturnValue(new ActionResult<ItemStack>(EnumActionResult.FAIL, itemstack));
        }
    }
}
