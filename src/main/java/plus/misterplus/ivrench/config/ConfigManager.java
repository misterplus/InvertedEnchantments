package plus.misterplus.ivrench.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import org.apache.commons.lang3.tuple.Pair;

public class ConfigManager {

    public static class Common {
        public final BooleanValue forceAprilFools;
        public final BooleanValue waterRemove;
        public final BooleanValue debugCommand;
        public final BooleanValue inGameSwitchApril;
        public final BooleanValue inGameSwitchWater;

        public Common(ForgeConfigSpec.Builder builder) {
            forceAprilFools = builder
                    .comment("Force Enable April Fools")
                    .define("forceAprilFools.enabled", false);
            waterRemove = builder
                    .comment("Allow Frost Melter break Packed Ice ,Blue Ice and Water Source")
                    .define("waterRemove.enabled", false);

            //debug
            debugCommand = builder
                    .comment("Debug Command Main Switch")
                    .define("debug.command", false);

            inGameSwitchApril = builder
                    .comment("Ingame Switch April Fools -- Need open Debug")
                    .define("forceAprilFools.ingame", false);

            inGameSwitchWater = builder
                    .comment("Ingame Switch Water Remove -- Need open Debug ")
                    .define("waterRemove.ingame", false);
        }
    }

    public static final Common COMMON;
    public static final ForgeConfigSpec COMMON_SPEC;
    static {
        final Pair<Common, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Common::new);
        COMMON_SPEC = specPair.getRight();
        COMMON = specPair.getLeft();
    }
}
