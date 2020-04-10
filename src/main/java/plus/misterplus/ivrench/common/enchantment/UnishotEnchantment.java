package plus.misterplus.ivrench.common.enchantment;

import net.minecraft.enchantment.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class UnishotEnchantment extends InvertedEnchantmentBase {
    public UnishotEnchantment(Rarity rarityIn, EquipmentSlotType[] slots) {
        super(rarityIn, EnchantmentType.CROSSBOW, slots);
        MinecraftForge.EVENT_BUS.register(this);
    }

    public int getMinEnchantability(int enchantmentLevel) {
        return 20;
    }

    public int getMaxEnchantability(int enchantmentLevel) {
        return 50;
    }

    public int getMaxLevel() {
        return 1;
    }

    public boolean canApplyTogether(Enchantment ench) {
        return !(ench instanceof MultishotEnchantment || ench instanceof PiercingEnchantment || ench instanceof NoPiercingEnchantment ) && super.canApplyTogether(ench);
    }

    @SubscribeEvent
    public void onCharged(LivingEntityUseItemEvent event) {
        if (event.getEntity() instanceof PlayerEntity) {
            if (((PlayerEntity) event.getEntity()).getHeldItemMainhand().getItem() == Items.CROSSBOW) {
                ItemStack item = ((PlayerEntity) event.getEntity()).getHeldItemMainhand();
                if (getCurrentLevelTool(item) == 1 && CrossbowItem.isCharged(item)) {
                    clearProjectiles(item);
                }
            } else if (((PlayerEntity) event.getEntity()).getHeldItemOffhand().getItem() == Items.CROSSBOW) {
                ItemStack item = ((PlayerEntity) event.getEntity()).getHeldItemOffhand();
                if (getCurrentLevelTool(item) == 1 && CrossbowItem.isCharged(item)) {
                    clearProjectiles(item);
                }
            }
        }
    }
    protected int getCurrentLevelTool(ItemStack stack) {
        if (!stack.isEmpty() && EnchantmentHelper.getEnchantments(stack).containsKey(this))
            return EnchantmentHelper.getEnchantments(stack).get(this);
        return -1;
    }

    private static void clearProjectiles(ItemStack item) {
        CompoundNBT compoundNBT = item.getTag();
        if (compoundNBT != null) {
            ListNBT chargedProjectiles = compoundNBT.getList("ChargedProjectiles", 9);
            chargedProjectiles.clear();
            compoundNBT.put("ChargedProjectiles", chargedProjectiles);
        }
    }
}
