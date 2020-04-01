package plus.misterplus.ivrench.config;

import net.minecraftforge.common.config.Config;
import plus.misterplus.ivrench.InvertedEnchantments;

@Config(modid = InvertedEnchantments.MOD_ID, name = InvertedEnchantments.MOD_NAME)
public class Configs {
    @Config.Comment("Easter Egg Settings")
    public static EasterEggSettings easterEggSettings = new EasterEggSettings();

    public static class EasterEggSettings {
        @Config.Comment("Force the mod to use april fools mode")
        public boolean forceAprilFools = false;
    }
}
