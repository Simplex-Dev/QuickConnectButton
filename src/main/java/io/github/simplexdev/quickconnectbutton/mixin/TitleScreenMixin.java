package io.github.simplexdev.quickconnectbutton.mixin;

import io.github.simplexdev.quickconnectbutton.config.QuickConnectButtonConfig;
import me.shedaniel.autoconfig.AutoConfig;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.ComponentRenderUtils;
import net.minecraft.client.gui.screens.ConnectScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.multiplayer.resolver.ServerAddress;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Environment(EnvType.CLIENT)
@Mixin(TitleScreen.class)
public class TitleScreenMixin extends Screen {

    QuickConnectButtonConfig config = AutoConfig.getConfigHolder(QuickConnectButtonConfig.class).getConfig();

    protected TitleScreenMixin(Component title) {
        super(title);
    }

    @Inject(method = "createNormalMenuOptions", at = @At(value = "TAIL"))
    private void addCustomButton(int i, int j, CallbackInfoReturnable<Integer> cir) {
        int offsetX = 0;
        Component text = Component.literal(config.connectButton);
        int width = config.dimensions.width;

        switch (config.buttonAlign) {
            case LEFT -> offsetX = -128 - (width / 2);
            case RIGHT -> offsetX = 105;
        }

        ServerData info = new ServerData("Custom Server", config.address, ServerData.Type.OTHER);
        switch (config.resourcePackPolicy) {
            case ALLOW -> info.setResourcePackStatus(ServerData.ServerPackStatus.ENABLED);
            case DENY -> info.setResourcePackStatus(ServerData.ServerPackStatus.DISABLED);
            case PROMPT -> info.setResourcePackStatus(ServerData.ServerPackStatus.PROMPT);
        }

        var button = Button.builder(Component.literal(config.connectButton), (buttonWidget) -> {
            ConnectScreen.startConnecting(this, this.minecraft, ServerAddress.parseString(config.address), info, false, null);
            buttonWidget.playDownSound(this.minecraft.getSoundManager());
        }).bounds(this.width / 2 + offsetX, i - 2*j, config.dimensions.width, config.dimensions.height).build();

        this.addRenderableWidget(button);

    }
}
