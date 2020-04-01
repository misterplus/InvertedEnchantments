package plus.misterplus.ivrench;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import plus.misterplus.ivrench.common.command.GetAllEnchantments;
import plus.misterplus.ivrench.common.item.InvertedEnchantmentsCreativeTab;

import java.time.LocalDate;
import java.time.Month;

import static net.minecraft.inventory.EntityEquipmentSlot.*;
import static plus.misterplus.ivrench.config.Configs.easterEggSettings;

@Mod(modid = InvertedEnchantments.MOD_ID, name = InvertedEnchantments.MOD_NAME, version = InvertedEnchantments.VERSION, certificateFingerprint = "@FINGERPRINT@")
public class InvertedEnchantments {
    public static final String MOD_ID = "ivrench";
    public static final String MOD_NAME = "Inverted Enchantments";
    public static final String VERSION = "1.0.7";

    public final static InvertedEnchantmentsCreativeTab MOD_TAB = new InvertedEnchantmentsCreativeTab();
    public static EntityEquipmentSlot[] aentityequipmentslot = new EntityEquipmentSlot[]{HEAD, CHEST, LEGS, FEET};
    public static boolean APRIL_FOOLS;
    private static Logger logger = LogManager.getLogger(MOD_NAME);

    private static boolean isAprilFools() {
        LocalDate date = LocalDate.now();
        return date.getMonth() == Month.APRIL && date.getDayOfMonth() == 1;
    }

    @Mod.EventHandler
    public void onServerStarting(FMLServerStartingEvent event) {
        event.registerServerCommand(new GetAllEnchantments());
        APRIL_FOOLS = easterEggSettings.forceAprilFools || isAprilFools();
    }
}
