package plus.misterplus.ivrench.common.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import plus.misterplus.ivrench.InvertedEnchantments;

import static plus.misterplus.ivrench.InvertedEnchantments.APRIL_FOOLS;

@SuppressWarnings("ALL")
public class InvertedEnchantmentBase extends Enchantment {

    protected String fakename;

    protected InvertedEnchantmentBase(Rarity rarityIn, EnchantmentType typeIn, EquipmentSlotType[] slots) {
        super(rarityIn, typeIn, slots);

    }

    @Override
    public String getName() {
        return APRIL_FOOLS ?  "enchantment.minecraft." + this.fakename : "enchantment." + InvertedEnchantments.MOD_ID + "." + this.name ;
    }

    public InvertedEnchantmentBase setName(String enchName) {
        this.name = enchName;
        return this;
    }

    public InvertedEnchantmentBase setFakeName(String originalName) {
        this.fakename = originalName;
        return this;
    }
}
