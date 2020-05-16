package plus.misterplus.ivrench;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import plus.misterplus.ivrench.common.command.MainCommand;
import plus.misterplus.ivrench.common.item.InvertedEnchantmentsItemGroup;
import plus.misterplus.ivrench.config.ConfigManager;
import plus.misterplus.ivrench.event.GenericEventHandler;

import java.time.LocalDate;
import java.time.Month;

import static net.minecraft.inventory.EquipmentSlotType.*;

@Mod(InvertedEnchantments.MOD_ID)
public class InvertedEnchantments {
    public static final String MOD_ID = "ivrench";
    public static final String MOD_NAME = "Inverted Enchantments";

    public final static InvertedEnchantmentsItemGroup MOD_TAB = new InvertedEnchantmentsItemGroup();
    public static final EquipmentSlotType[] aEquipmentSlotType = new EquipmentSlotType[]{HEAD, CHEST, LEGS, FEET};
    public static boolean APRIL_FOOLS;
    private static final Logger logger = LogManager.getLogger(MOD_NAME);

    public InvertedEnchantments() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ConfigManager.COMMON_SPEC);
        APRIL_FOOLS = ConfigManager.COMMON.forceAprilFools.get() || isAprilFools();
        MinecraftForge.EVENT_BUS.addListener(this::serverStarting);
        logger.info(MOD_ID+" Loaded.");

    }

    private static boolean isAprilFools() {
        LocalDate date = LocalDate.now();
        return date.getMonth() == Month.APRIL && date.getDayOfMonth() == 1;
    }
    private void serverStarting(FMLServerStartingEvent event) {
        MainCommand.register(event.getCommandDispatcher());
        MinecraftForge.EVENT_BUS.register(GenericEventHandler.class);
    }

    public static Logger getLogger() {
        return logger;
    }
}
