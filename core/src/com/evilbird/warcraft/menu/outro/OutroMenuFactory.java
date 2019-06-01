/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.menu.outro;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar.ProgressBarStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.I18NBundle;
import com.evilbird.engine.common.inject.IdentifiedAssetProvider;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.device.DeviceDisplay;
import com.evilbird.engine.menu.Menu;
import org.apache.commons.lang3.Validate;

import javax.inject.Inject;

import static com.evilbird.engine.common.assets.AssetUtilities.fontSize;
import static com.evilbird.engine.common.graphics.TextureUtils.getDrawable;
import static com.evilbird.warcraft.menu.main.MainMenuType.Home;

/**
 * Instances of this factory create {@link OutroMenu OutroMenus}, menus shown
 * when a scenario is completed, or failed.
 *
 * @author Blair Butterworth
 */
public class OutroMenuFactory implements IdentifiedAssetProvider<Menu>
{
    private static final String FONT = "data/fonts/philosopher.ttf";
    private static final String FONT_LARGE = "data/fonts/philosopher-large.ttf";
    private static final String BUTTON = "data/textures/menu/button.png";
    private static final String STRINGS = "data/strings/outro";
    private static final String BACKGROUND_VICTORY = "data/textures/human/menu/victory.png";
    private static final String BACKGROUND_DEFEAT = "data/textures/human/menu/defeat.png";
    private static final String PROGRESS_BACKGROUND = "data/textures/neutral/perennial/stats_progress_background.png";
    private static final String PROGRESS_FILL = "data/textures/neutral/perennial/stats_progress_fill.png";

    private AssetManager assets;
    private DeviceDisplay display;

    @Inject
    public OutroMenuFactory(Device device) {
        this.assets = device.getAssetStorage();
        this.display = device.getDeviceDisplay();
    }

    @Override
    public void load() {
        assets.load(FONT, BitmapFont.class, fontSize(18));
        assets.load(FONT_LARGE, BitmapFont.class, fontSize(36));
        assets.load(BUTTON, Texture.class);
        assets.load(BACKGROUND_VICTORY, Texture.class);
        assets.load(BACKGROUND_DEFEAT, Texture.class);
        assets.load(PROGRESS_FILL, Texture.class);
        assets.load(PROGRESS_BACKGROUND, Texture.class);
        assets.load(STRINGS, I18NBundle.class);
    }

    @Override
    public Menu get(Identifier identifier) {
        Validate.isInstanceOf(OutroMenuType.class, identifier);
        return getMenu((OutroMenuType)identifier);
    }

    private Menu getMenu(OutroMenuType type) {
        OutroMenu menu = new OutroMenu(display, getSkin());
        menu.setType(type);
        menu.setLabelBundle(getStrings());
        menu.setButtonAction(() -> menu.showMenu(Home));
        return menu;
    }

    private Skin getSkin() {
        Skin skin = new Skin();
        addLabelStyle(skin);
        addButtonStyle(skin);
        addBackgroundStyle(skin);
        addProgressBarStyle(skin);
        return skin;
    }

    private void addBackgroundStyle(Skin skin) {
        skin.add("background-defeat", getDrawable(assets, BACKGROUND_DEFEAT), Drawable.class);
        skin.add("background-victory", getDrawable(assets, BACKGROUND_VICTORY), Drawable.class);
    }

    private void addLabelStyle(Skin skin) {
        LabelStyle labelStyle = new LabelStyle();
        labelStyle.font =  assets.get(FONT, BitmapFont.class);
        labelStyle.fontColor = Color.WHITE;
        skin.add("default", labelStyle);
        skin.add("progress-outro", labelStyle);

        LabelStyle largeStyle = new LabelStyle();
        largeStyle.font = assets.get(FONT_LARGE, BitmapFont.class);
        largeStyle.fontColor = Color.WHITE;
        skin.add("font-large", largeStyle);
    }

    private void addButtonStyle(Skin skin) {
        TextButtonStyle textButtonStyle = new TextButtonStyle();
        textButtonStyle.font = assets.get(FONT, BitmapFont.class);
        textButtonStyle.fontColor = Color.WHITE;
        textButtonStyle.up = getDrawable(assets, BUTTON, 0, 0, 225, 30);;
        textButtonStyle.over = textButtonStyle.up;
        textButtonStyle.checked = textButtonStyle.up;
        textButtonStyle.checkedOver = textButtonStyle.up;
        textButtonStyle.disabled = getDrawable(assets, BUTTON, 0, 60, 225, 30);
        textButtonStyle.down = getDrawable(assets, BUTTON, 0, 30, 225, 30);
        skin.add("default", textButtonStyle);
    }

    private void addProgressBarStyle(Skin skin) {
        ProgressBarStyle style = new ProgressBarStyle();
        style.background = getDrawable(assets, PROGRESS_BACKGROUND);
        style.knob = getDrawable(assets, PROGRESS_FILL);
        style.knobBefore = style.knob;

        skin.add("progress-outro", style);
    }

    private OutroMenuStrings getStrings() {
        I18NBundle bundle = assets.get(STRINGS, I18NBundle.class);
        return new OutroMenuStrings(bundle);
    }
}
