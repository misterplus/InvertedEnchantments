package plus.misterplus.ivrench.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import org.apache.commons.lang3.tuple.Pair;

public class ConfigManager {

    public static class Common {
        public final BooleanValue forceAprilFools;

        public Common(ForgeConfigSpec.Builder builder) {
            forceAprilFools = builder
                    .comment("Force Enable April Fools?")
                    .define("forceAprilFools.enabled", false);
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
