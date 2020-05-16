package plus.misterplus.ivrench.common.utils;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;

public class ItemsHelper {
    public static void clearProjectiles(ItemStack item) {
        CompoundNBT compoundNBT = item.getTag();
        if (compoundNBT != null) {
            ListNBT chargedProjectiles = compoundNBT.getList("ChargedProjectiles", 9);
            chargedProjectiles.clear();
            compoundNBT.put("ChargedProjectiles", chargedProjectiles);
        }
    }
}
