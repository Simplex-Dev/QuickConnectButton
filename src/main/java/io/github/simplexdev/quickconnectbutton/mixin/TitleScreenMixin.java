package io.github.simplexdev.quickconnectbutton.mixin;

import io.github.simplexdev.quickconnectbutton.config.ConfigManager;
import net.minecraft.client.gui.screen.ConnectScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.network.ServerInfo;
import net.minecraft.client.resource.language.I18n;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public class TitleScreenMixin extends Screen {

    private final int QuickConnectButtonId = 53;

    protected TitleScreenMixin() {
        super();
    }

    @Inject(method = "buttonClicked", at = @At("HEAD"))
    protected void buttonClicked(ButtonWidget button, CallbackInfo ci) {
        if (button.id == QuickConnectButtonId) {
            String address = ConfigManager.getConfig().address;
            this.client.openScreen(new ConnectScreen(this, this.client, new ServerInfo(I18n.translate("selectServer.defaultName"), address, false)));
        }
    }

    @Inject(method = "initWidgetsNormal", at = @At("TAIL"))
    private void initWidgetsNormal(int y, int spacingY, CallbackInfo ci) {
        this.buttons.add(new ButtonWidget(QuickConnectButtonId, this.width / 2 - 100 + 205, y + spacingY, 50, 20, "Connect"));
    }
}
