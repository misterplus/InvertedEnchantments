package plus.misterplus.ivrench.mixins;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static plus.misterplus.ivrench.common.utils.InvertedEnchantmentHelper.getEnchantment;

@Mixin(EntityXPOrb.class)
public abstract class MixinEntityXPOrb {

    @Shadow
    public int xpValue;

    private static int roundAverage(float value) {
        double floor = Math.floor(value);
        return (int) floor + (Math.random() < value - floor ? 1 : 0);
    }

    @Inject(
            method = "onCollideWithPlayer",
            at = @At(
                    value = "INVOKE_ASSIGN",
                    target = "net/minecraft/enchantment/EnchantmentHelper.getEnchantedItem(Lnet/minecraft/enchantment/Enchantment;Lnet/minecraft/entity/EntityLivingBase;)Lnet/minecraft/item/ItemStack;"
            )
    )
    private void injectOnCollideWithPlayer(EntityPlayer entityIn, CallbackInfo ci) {
        ItemStack itemstack1 = EnchantmentHelper.getEnchantedItem(getEnchantment("unmending"), entityIn);

        if (!itemstack1.isEmpty() && itemstack1.isItemDamaged()) {
            float ratio = itemstack1.getItem().getXpRepairRatio(itemstack1);
            int i = Math.min(roundAverage(this.xpValue * ratio), itemstack1.getItemDamage());
            this.xpValue -= roundAverage(i / ratio);
            itemstack1.setItemDamage(itemstack1.getItemDamage() + i);
        }
    }
}
