package plus.misterplus.ivrench;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import plus.misterplus.ivrench.common.utils.command.FastRandom;
import plus.misterplus.ivrench.common.utils.items.CreativeTab;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = InvertedEnchantments.MOD_ID, name = InvertedEnchantments.MOD_NAME, version = InvertedEnchantments.VERSION)
public class InvertedEnchantments {
    public static final String MOD_ID = "ivrench";
    public static final String MOD_NAME = "Inverted Enchantments";
    public static final String VERSION = "1.0.0";

    private static Logger logger = LogManager.getLogger(MOD_NAME);
    public final static CreativeTab TAB = new CreativeTab();

    public static EntityEquipmentSlot[] aentityequipmentslot = new EntityEquipmentSlot[] {EntityEquipmentSlot.HEAD, EntityEquipmentSlot.CHEST, EntityEquipmentSlot.LEGS, EntityEquipmentSlot.FEET};
    
    @Mod.EventHandler
    public void onServerStarting(FMLServerStartingEvent event) {
        event.registerServerCommand(new FastRandom());
    }
}
