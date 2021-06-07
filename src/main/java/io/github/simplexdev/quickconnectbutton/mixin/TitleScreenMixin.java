package io.github.simplexdev.quickconnectbutton.mixin;

import io.github.simplexdev.quickconnectbutton.config.QuickConnectButtonConfig;
import me.shedaniel.autoconfig.AutoConfig;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.ConnectScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.network.ServerInfo;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.text.LiteralText;
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
        this.addButton(new ButtonWidget(this.width / 2 - 100 + 205, y + spacingY, 50, 20, new LiteralText("Connect"), (buttonWidget) -> {
            this.client.openScreen(new ConnectScreen(this, this.client, new ServerInfo(I18n.translate("selectServer.defaultName"), config.address, false)));
        }));
    }
}
