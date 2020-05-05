package plus.misterplus.ivrench.mixins;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

import static plus.misterplus.ivrench.common.utils.InvertedEnchantmentHelper.getEnchantment;

@Mixin(ExperienceOrbEntity.class)
public abstract class MixinExperienceOrbEntity {

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
                    target = "Lnet/minecraft/enchantment/EnchantmentHelper;getRandomItemWithEnchantment(Lnet/minecraft/enchantment/Enchantment;Lnet/minecraft/entity/LivingEntity;)Ljava/util/Map$Entry;"
            )
    )
    private void injectOnCollideWithPlayer(PlayerEntity entityIn, CallbackInfo ci) {
        Map.Entry<EquipmentSlotType, ItemStack> entry = EnchantmentHelper.getRandomItemWithEnchantment(getEnchantment("unmending"), entityIn);

        if (entry != null) {
            ItemStack itemstack = (ItemStack)entry.getValue();
            if (!itemstack.isEmpty() && itemstack.isDamaged()) {
                int i = Math.min((int)((float)this.xpValue * itemstack.getXpRepairRatio()), itemstack.getDamage());
                this.xpValue -= i / 2;
                itemstack.setDamage(itemstack.getDamage() - i);
            }
        }


    }
}
