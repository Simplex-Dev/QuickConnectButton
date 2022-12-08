package io.github.simplexdev.quickconnectbutton.mixin;

import io.github.simplexdev.quickconnectbutton.config.QuickConnectButtonConfig;
import me.shedaniel.autoconfig.AutoConfig;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.ConnectScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.network.ServerAddress;
import net.minecraft.client.network.ServerInfo;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(TitleScreen.class)
public class TitleScreenMixin extends Screen {

    QuickConnectButtonConfig config = AutoConfig.getConfigHolder(QuickConnectButtonConfig.class).getConfig();

    protected TitleScreenMixin(Text title) {
        super(title);
    }

    @Inject(method = "initWidgetsNormal", at = @At(value = "TAIL"))
    private void addCustomButton(int y, int spacingY, CallbackInfo ci) {
        int offsetX = 0;

        switch (config.buttonAlign) {
            case LEFT -> offsetX = -128 - (config.dimensions.width / 2);
            case RIGHT -> offsetX = 105;
        }

        ServerInfo info = new ServerInfo(I18n.translate("selectServer.defaultName"), config.address, false);

        switch (config.resourcePackPolicy) {
            case ALLOW -> info.setResourcePackPolicy(ServerInfo.ResourcePackPolicy.ENABLED);
            case DENY -> info.setResourcePackPolicy(ServerInfo.ResourcePackPolicy.DISABLED);
            case PROMPT -> info.setResourcePackPolicy(ServerInfo.ResourcePackPolicy.PROMPT);
        }

        var button = new ButtonWidget.Builder(Text.literal(config.connectButton), (buttonWidget) -> {
            ConnectScreen.connect(this, this.client, ServerAddress.parse(config.address), info);
            buttonWidget.playDownSound(this.client.getSoundManager());
        }).dimensions(this.width / 2 + offsetX, y, config.dimensions.width, config.dimensions.height).build();

        this.addDrawableChild(button);
    }
}
