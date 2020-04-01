package plus.misterplus.ivrench.common.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;

import static plus.misterplus.ivrench.InvertedEnchantments.APRIL_FOOLS;

public class EnchantmentInverted extends Enchantment {

    protected String fakename;

    protected EnchantmentInverted(Rarity rarityIn, EnumEnchantmentType typeIn, EntityEquipmentSlot[] slots) {
        super(rarityIn, typeIn, slots);
    }

    @Override
    public String getName()
    {
        return "enchantment." + (APRIL_FOOLS ? this.fakename : this.name);
    }

    @Override
    public EnchantmentInverted setName(String enchName)
    {
        this.name = enchName;
        return this;
    }

    public EnchantmentInverted setFakeName(String originalName)
    {
        this.fakename = originalName;
        return this;
    }
}
