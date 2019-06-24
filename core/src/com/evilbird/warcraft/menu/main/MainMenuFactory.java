/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.menu.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.evilbird.engine.common.control.SelectListener;
import com.evilbird.engine.common.graphics.Fonts;
import com.evilbird.engine.common.inject.IdentifiedAssetProvider;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.device.DeviceDisplay;
import com.evilbird.engine.menu.Menu;
import com.evilbird.warcraft.menu.intro.IntroMenuType;
import org.apache.commons.lang3.Validate;

import javax.inject.Inject;

import static com.evilbird.engine.common.graphics.TextureUtils.getDrawable;
import static com.evilbird.warcraft.menu.intro.IntroMenuType.Human1;
import static com.evilbird.warcraft.menu.main.MainMenuType.Campaign;
import static com.evilbird.warcraft.menu.main.MainMenuType.CampaignNew;
import static com.evilbird.warcraft.menu.main.MainMenuType.Home;

/**
 * Instances of this factory create {@link Menu Menus} whose contents are
 * specified by the given {@link MainMenuType}.
 *
 * @author Blair Butterworth
 */
public class MainMenuFactory implements IdentifiedAssetProvider<Menu>
{
    private static final String BUTTON = "data/textures/common/menu/button.png";
    private static final String BACKGROUND = "data/textures/common/menu/menu.png";
    private static final String CLICK = "data/sounds/common/menu/click.mp3";
    private static final String MUSIC = "data/music/13.mp3";

    private AssetManager assets;
    private DeviceDisplay display;

    @Inject
    public MainMenuFactory(Device device) {
        this.assets = device.getAssetStorage();
        this.display = device.getDeviceDisplay();
    }

    @Override
    public void load() {
        assets.load(BUTTON, Texture.class);
        assets.load(BACKGROUND, Texture.class);
        assets.load(CLICK, Sound.class);
        assets.load(MUSIC, Music.class);
    }

    @Override
    public Menu get(Identifier identifier) {
        Validate.isInstanceOf(MainMenuType.class, identifier);

        switch((MainMenuType)identifier) {
            case Home: return getHomeMenu();
            case Campaign: return getCampaignMenu();
            case CampaignNew: return getNewCampaignMenu();
            case CampaignLoad: return getLoadCampaignMenu();
            default: throw new UnsupportedOperationException();
        }
    }

    private Menu getHomeMenu() {
        MainMenu menu = new MainMenu(display, getSkin());
        menu.insertButton("Single Player Game", showMenu(menu, Campaign));
        menu.insertButton("Multi Player Game");
        menu.insertButton("Replay Introduction");
        menu.insertButton("Show Credits");
        menu.insertButton("Exit Program", shutdown());
        return menu;
    }

    private Menu getCampaignMenu() {
        MainMenu menu = new MainMenu(display, getSkin());
        menu.insertButton("New Campaign", showMenu(menu, CampaignNew));
        menu.insertButton("Load Game");
        menu.insertButton("Custom Campaign");
        menu.insertButton("Previous Menu", showMenu(menu, Home));
        return menu;
    }

    private Menu getNewCampaignMenu() {
        MainMenu menu = new MainMenu(display, getSkin());
        menu.insertButton("Human Campaign", showMenu(menu, Human1));
        menu.insertButton("Orc Campaign");
        menu.insertButton("Previous Menu", showMenu(menu, Campaign));
        return menu;
    }

    private Menu getLoadCampaignMenu() {
        MainMenu menu = new MainMenu(display, getSkin());
        menu.insertButton("Previous Menu", showMenu(menu, Home));
        return menu;
    }

    private SelectListener showMenu(MainMenu menu, MainMenuType type) {
        return () -> menu.showMenu(type);
    }

    private SelectListener showMenu(MainMenu menu, IntroMenuType type) {
        return () -> menu.showMenu(type);
    }

    private SelectListener shutdown() {
        return () -> Gdx.app.exit();
    }

    private Skin getSkin() {
        Skin skin = new Skin();
        skin.add("default", getMainMenuStyle(), MainMenuStyle.class);
        skin.add("default", getTextButtonStyle(), TextButtonStyle.class);
        return skin;
    }

    private MainMenuStyle getMainMenuStyle() {
        MainMenuStyle mainMenuStyle = new MainMenuStyle();
        mainMenuStyle.background = getDrawable(assets, BACKGROUND);
        mainMenuStyle.music = assets.get(MUSIC);
        return mainMenuStyle;
    }

    private TextButtonStyle getTextButtonStyle() {
        TextButtonStyle textButtonStyle = new TextButtonStyle();
        textButtonStyle.font = Fonts.ARIAL;
        textButtonStyle.fontColor = Color.WHITE;
        textButtonStyle.up = getDrawable(assets, BUTTON, 0, 0, 224, 28);
        textButtonStyle.over = textButtonStyle.up;
        textButtonStyle.checked = textButtonStyle.up;
        textButtonStyle.checkedOver = textButtonStyle.up;
        textButtonStyle.disabled = getDrawable(assets, BUTTON, 0, 56, 224, 28);
        textButtonStyle.down = getDrawable(assets, BUTTON, 0, 28, 224, 28);
        return textButtonStyle;
    }
}
