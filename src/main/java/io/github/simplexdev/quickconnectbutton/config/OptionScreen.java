package io.github.simplexdev.quickconnectbutton.config;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.resource.language.I18n;
import org.lwjgl.input.Keyboard;

public class OptionScreen extends Screen {

    private final Screen parent;
    private final String title = "QuickConnectButton Config";
    private TextFieldWidget addressField;

    public OptionScreen(Screen screen) {
        this.parent = screen;
    }

    public void init() {
        Keyboard.enableRepeatEvents(true);
        this.buttons.add(new ButtonWidget(1, this.width / 2 - 152, this.height - 30, 150, 20, I18n.translate("gui.cancel")));
        this.buttons.add(new ButtonWidget(2, this.width / 2 + 2, this.height - 30, 150, 20, I18n.translate("gui.done")));

        this.addressField = new TextFieldWidget(3, this.textRenderer, this.width / 2 - 100, this.height / 2, 200, 20);
        this.addressField.setMaxLength(128);
        this.addressField.setFocused(true);
        this.addressField.setText(ConfigManager.getConfig().address);
        this.buttons.get(1).active = this.addressField.getText().length() > 0;
    }

    public void handleMouse() {
        super.handleMouse();
    }

    protected void buttonClicked(ButtonWidget button) {
        if (!button.active) return;

        switch (button.id) {
            case 1:
                this.client.openScreen(this.parent);
                break;
            case 2:
                this.client.openScreen(this.parent);
                ConfigManager.getConfig().address = addressField.getText();
                ConfigManager.saveConfig();
                break;
        }
    }

    public void tick() {
        this.addressField.tick();
        super.tick();
    }

    protected void keyPressed(char character, int code) {
        this.addressField.keyPressed(character, code);
        if (code == 15) {
            this.addressField.setFocused(!this.addressField.isFocused());
        }

        if (code == 28 || code == 156) {
            this.buttonClicked(this.buttons.get(1));
        }

        this.buttons.get(1).active = this.addressField.getText().length() > 0;
    }

    protected void mouseClicked(int mouseX, int mouseY, int button) {
        super.mouseClicked(mouseX, mouseY, button);
        this.addressField.mouseClicked(mouseX, mouseY, button);
    }

    public void render(int mouseX, int mouseY, float tickDelta) {
        this.renderBackground();
        this.drawCenteredString(this.textRenderer, this.title, this.width / 2, 8, 16777215);
        this.addressField.render();
        super.render(mouseX, mouseY, tickDelta);
    }
}
