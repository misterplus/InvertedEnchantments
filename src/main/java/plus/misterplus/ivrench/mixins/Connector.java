package plus.misterplus.ivrench.mixins;

import org.spongepowered.asm.mixin.Mixins;
import org.spongepowered.asm.mixin.connect.IMixinConnector;


public class Connector implements IMixinConnector {

    @Override
    public void connect() {
        Mixins.addConfiguration("assets/ivrench/mixins.ivrench.json");
    }

}