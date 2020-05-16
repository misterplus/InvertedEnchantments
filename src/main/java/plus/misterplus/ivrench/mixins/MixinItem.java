package plus.misterplus.ivrench.mixins;

import net.minecraft.entity.Entity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.extensions.IForgeItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.minecraft.enchantment.EnchantmentHelper.getEnchantmentLevel;
import static plus.misterplus.ivrench.common.utils.InvertedEnchantmentHelper.getEnchantment;

@Mixin(Item.class)
public abstract class MixinItem implements IForgeItem {

    @Override
    public boolean canEquip(ItemStack stack, EquipmentSlotType armorType, Entity entity) {
        if (getEnchantmentLevel(getEnchantment("unbinding_curse"), stack) > 0) {
            return false;
        }
        return MobEntity.getSlotForItemStack(stack) == armorType;
    }

    @Inject(
            method = "inventoryTick",
            at = @At("HEAD")
    )
    private void injectOnUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected, CallbackInfo ci) {
        if (entityIn instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) entityIn;
            if (getEnchantmentLevel(getEnchantment("vanishing"), stack) > 0)
                player.inventory.removeStackFromSlot(itemSlot);
        }
    }

}
