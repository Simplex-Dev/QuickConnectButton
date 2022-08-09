package io.github.simplexdev.quickconnectbutton.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

@Config(name = "quickconnectbutton")
public class QuickConnectButtonConfig implements ConfigData {
    @Comment("The address to connect to when the button is pressed.")
    public String address = "localhost";

    @Comment("The name of the button.")
    public String connectButton = "Connect";

    @Comment("Whether the button should appear on the left or not, in case another mod has already put a button on the right.")
    public boolean showLeft = false;

    @Comment("The width and height of the Connect button")
    @ConfigEntry.Gui.CollapsibleObject(startExpanded = true)
    public Dimensions dimensions = new Dimensions();

    public static class Dimensions {
        public int height = 20;
        public int width = 50;
    }
}
