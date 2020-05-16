package plus.misterplus.ivrench.mixins;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.UnbreakingEnchantment;
import net.minecraft.entity.player.ServerPlayerEntity;
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
                    target = "Lnet/minecraft/enchantment/EnchantmentHelper;getEnchantmentLevel(Lnet/minecraft/enchantment/Enchantment;Lnet/minecraft/item/ItemStack;)I"
            )
    )
    private void injectAttemptDamageItem(int amount, Random rand, ServerPlayerEntity damager, CallbackInfoReturnable<Boolean> cir) {
        this.rand = rand;
    }

    @ModifyVariable(
            method = "attemptDamageItem",
            at = @At(
                    value = "INVOKE_ASSIGN",
                    target = "Lnet/minecraft/enchantment/EnchantmentHelper;getEnchantmentLevel(Lnet/minecraft/enchantment/Enchantment;Lnet/minecraft/item/ItemStack;)I"
            ),
            name = "p_96631_1_"
    )
    private int modify_amout(int p_96631_1_) {
        int i1 = EnchantmentHelper.getEnchantmentLevel(getEnchantment("breaking"), (ItemStack) (Object) this);
        int j1 = 0;

        for (int k = 0; i1 > 0 && k < p_96631_1_; ++k) {
            if (UnbreakingEnchantment.negateDamage((ItemStack) (Object) this, i1, rand)) {
                j1 += i1;
            }
        }

        p_96631_1_ += j1;
        return p_96631_1_;
    }
}
