package io.github.simplexdev.quickconnectbutton;

import io.github.simplexdev.quickconnectbutton.config.QuickConnectButtonConfig;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.fabricmc.api.ModInitializer;

public class QuickConnectButtonMain implements ModInitializer {

    @Override
    public void onInitialize() {
        AutoConfig.register(QuickConnectButtonConfig.class, JanksonConfigSerializer::new);
    }
}
