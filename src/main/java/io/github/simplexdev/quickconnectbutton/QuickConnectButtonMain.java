package io.github.simplexdev.quickconnectbutton;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.github.simplexdev.quickconnectbutton.config.ConfigManager;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class QuickConnectButtonMain implements ClientModInitializer {

    public static final String MOD_ID = "quickconnectbutton";
    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public void onInitializeClient() {
        ConfigManager.loadConfig();
    }
}
