package io.github.simplexdev.quickconnectbutton.config;

import io.github.simplexdev.quickconnectbutton.QuickConnectButtonMain;
import net.fabricmc.loader.api.FabricLoader;

import java.io.*;

public class ConfigManager {

    private static QuickConnectConfig config;

    public static QuickConnectConfig getConfig() {
        return config;
    }

    public static void loadConfig() {
        File file = new File(FabricLoader.getInstance().getConfigDirectory(), QuickConnectButtonMain.MOD_ID + ".json");

        try {
            if (file.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(file));

                QuickConnectConfig parsed = QuickConnectButtonMain.GSON.fromJson(br, QuickConnectConfig.class);
                if (parsed != null) {
                    config = parsed;
                }
            } else {
                createConfig();
            }
        } catch (FileNotFoundException e) {
            System.err.println("Couldn't load QuickConnectButton configuration file; reverting to defaults");
            e.printStackTrace();
        }

        createConfig();
    }

    private static void createConfig() {
        if (config == null) {
            config = new QuickConnectConfig();
            saveConfig();
        }
    }

    public static void saveConfig() {
        File file = new File(FabricLoader.getInstance().getConfigDirectory(), QuickConnectButtonMain.MOD_ID + ".json");
        String jsonString = QuickConnectButtonMain.GSON.toJson(config);

        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(jsonString);
        } catch (IOException e) {
            System.err.println("Couldn't save QuickConnectButton configuration file");
            e.printStackTrace();
        }
    }
}
