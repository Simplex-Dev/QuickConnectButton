package io.github.simplexdev.quickconnectbutton.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

@Config(name = "quickconnectbutton")
public class QuickConnectButtonConfig implements ConfigData {
    @Comment("The address to connect to when the Connect button is pressed.")
    public String address = "localhost";
}
