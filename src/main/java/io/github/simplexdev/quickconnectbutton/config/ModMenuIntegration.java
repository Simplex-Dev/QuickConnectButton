package io.github.simplexdev.quickconnectbutton.config;

import io.github.prospector.modmenu.api.ModMenuApi;
import io.github.simplexdev.quickconnectbutton.QuickConnectButtonMain;
import net.minecraft.client.gui.screen.Screen;

import java.util.function.Function;

public class ModMenuIntegration implements ModMenuApi {
    @Override
    public Function<Screen, ? extends Screen> getConfigScreenFactory() {
        return OptionScreen::new;
    }

    @Override
    public String getModId() {
        return QuickConnectButtonMain.MOD_ID;
    }
}
