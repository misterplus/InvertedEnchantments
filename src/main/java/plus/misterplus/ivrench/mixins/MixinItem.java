package plus.misterplus.ivrench.mixins;

import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Item.class)
public abstract class MixinItem {
//    @Inject(
//            method = "isValidArmor",
//            at = @At("TAIL"),
//            remap = false,
//            cancellable = true
//    )
//    private void injectIsValidArmor(ItemStack stack, EquipmentSlotType armorType, Entity entity, CallbackInfoReturnable<Boolean> cir) {
//        if (EnchantmentHelper.getEnchantmentLevel(getEnchantment("unbinding_curse"), stack) > 0) {
//            cir.setReturnValue(false);
//        }
//    }
//
//    @Inject(
//            method = "onUpdate",
//            at = @At("HEAD")
//    )
//    private void injectOnUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected, CallbackInfo ci) {
//        if (entityIn instanceof EntityPlayer) {
//            EntityPlayer player = (EntityPlayer) entityIn;
//            if (getEnchantmentLevel(getEnchantment("vanishing"), stack) > 0)
//                player.inventory.removeStackFromSlot(itemSlot);
//        }
//    }
}

