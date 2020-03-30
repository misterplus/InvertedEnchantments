package plus.misterplus.ivrench;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = InvertedEnchantments.MOD_ID, name = InvertedEnchantments.MOD_NAME, version = InvertedEnchantments.VERSION)
public class InvertedEnchantments {
    public static final String MOD_ID = "ivrench";
    public static final String MOD_NAME = "Inverted Enchantments";
    public static final String VERSION = "1.0.2";

    private static Logger logger = LogManager.getLogger(MOD_NAME);

    public static EntityEquipmentSlot[] aentityequipmentslot = new EntityEquipmentSlot[] {EntityEquipmentSlot.HEAD, EntityEquipmentSlot.CHEST, EntityEquipmentSlot.LEGS, EntityEquipmentSlot.FEET};
}
