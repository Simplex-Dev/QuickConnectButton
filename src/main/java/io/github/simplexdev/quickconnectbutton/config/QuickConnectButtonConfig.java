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

    @Comment("The side to align the button to. ('LEFT'/'RIGHT')")
    public Alignment buttonAlign = Alignment.RIGHT;

    public enum Alignment {
        LEFT,
        RIGHT
    }

    @Comment("The width and height of the button.")
    @ConfigEntry.Gui.CollapsibleObject(startExpanded = true)
    public Dimensions dimensions = new Dimensions();

    public static class Dimensions {
        public int height = 20;
        public int width = 50;
    }

    @Comment("Determines what to do if the server has a resource pack. ('ALLOW'/'DENY'/'PROMPT').")
    public ResourcePackOption resourcePackPolicy = ResourcePackOption.PROMPT;

    public enum ResourcePackOption {
        ALLOW,
        DENY,
        PROMPT
    }
}
