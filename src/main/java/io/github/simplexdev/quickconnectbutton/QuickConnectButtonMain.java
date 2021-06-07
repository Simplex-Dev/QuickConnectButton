package io.github.simplexdev.quickconnectbutton;

import io.github.simplexdev.quickconnectbutton.config.QuickConnectButtonConfig;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class QuickConnectButtonMain implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        AutoConfig.register(QuickConnectButtonConfig.class, JanksonConfigSerializer::new);
    }
}
