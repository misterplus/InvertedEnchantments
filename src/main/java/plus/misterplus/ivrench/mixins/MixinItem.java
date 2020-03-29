package plus.misterplus.ivrench.mixins;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.minecraft.enchantment.EnchantmentHelper.getEnchantmentLevel;
import static plus.misterplus.ivrench.common.utils.InvertedEnchantmentHelper.getEnchantment;

@Mixin(Item.class)
public abstract class MixinItem {
    @Inject(
            method = "isValidArmor",
            at = @At("TAIL"),
            remap = false,
            cancellable = true
    )
    private void injectIsValidArmor(ItemStack stack, EntityEquipmentSlot armorType, Entity entity, CallbackInfoReturnable<Boolean> cir) {
        if (EnchantmentHelper.getEnchantmentLevel(getEnchantment("unbinding_curse"), stack) > 0) {
            cir.setReturnValue(false);
        }
    }

    @Inject(
            method = "onUpdate",
            at = @At("HEAD")
    )
    private void injectOnUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected, CallbackInfo ci) {
        if (entityIn instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) entityIn;
            if (getEnchantmentLevel(getEnchantment("vanishing"), stack) > 0)
                player.inventory.removeStackFromSlot(itemSlot);
        }
    }
}

