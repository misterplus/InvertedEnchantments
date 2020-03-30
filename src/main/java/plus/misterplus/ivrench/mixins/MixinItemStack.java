package plus.misterplus.ivrench.mixins;

import net.minecraft.enchantment.EnchantmentDurability;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

import static plus.misterplus.ivrench.common.utils.InvertedEnchantmentHelper.getEnchantment;

@Mixin(ItemStack.class)
public abstract class MixinItemStack {

    private Random rand;

    @Inject(
            method = "attemptDamageItem",
            at = @At(
                    value = "INVOKE_ASSIGN",
                    target = "net/minecraft/enchantment/EnchantmentHelper.getEnchantmentLevel(Lnet/minecraft/enchantment/Enchantment;Lnet/minecraft/item/ItemStack;)I"
            )
    )
    private void injectAttemptDamageItem(int amount, Random rand, EntityPlayerMP damager, CallbackInfoReturnable<Boolean> cir) {
        this.rand = rand;
    }

    @ModifyVariable(
            method = "attemptDamageItem",
            at = @At(
                    value = "INVOKE_ASSIGN",
                    target = "net/minecraft/enchantment/EnchantmentHelper.getEnchantmentLevel(Lnet/minecraft/enchantment/Enchantment;Lnet/minecraft/item/ItemStack;)I"
            ),
            name = "amount"
    )
    private int modify_amout(int amount) {
        int i1 = EnchantmentHelper.getEnchantmentLevel(getEnchantment("breaking"), (ItemStack) (Object) this);
        int j1 = 0;

        for (int k = 0; i1 > 0 && k < amount; ++k) {
            if (EnchantmentDurability.negateDamage((ItemStack) (Object) this, i1, rand)) {
                j1 += i1;
            }
        }

        amount += j1;
        return amount;
    }
}
