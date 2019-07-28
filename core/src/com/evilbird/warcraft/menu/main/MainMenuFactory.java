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
import com.evilbird.engine.common.control.SelectListener;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.device.DeviceDisplay;
import com.evilbird.engine.game.GameContext;
import com.evilbird.engine.game.GameFactory;
import com.evilbird.engine.menu.Menu;
import com.evilbird.warcraft.menu.intro.IntroMenuType;
import org.apache.commons.lang3.Validate;

import javax.inject.Inject;

import static com.evilbird.warcraft.menu.intro.IntroMenuType.Human1;
import static com.evilbird.warcraft.menu.intro.IntroMenuType.Orc1;
import static com.evilbird.warcraft.menu.main.MainMenuType.Campaign;
import static com.evilbird.warcraft.menu.main.MainMenuType.CampaignNew;
import static com.evilbird.warcraft.menu.main.MainMenuType.Home;

/**
 * Instances of this factory create {@link Menu Menus} whose contents are
 * specified by the given {@link MainMenuType}.
 *
 * @author Blair Butterworth
 */
public class MainMenuFactory implements GameFactory<MainMenu>
{
    private MainMenuAssets assets;
    private MainMenuBuilder builder;

    @Inject
    public MainMenuFactory(Device device) {
        this(device.getDeviceDisplay(), device.getAssetStorage());
    }

    public MainMenuFactory(DeviceDisplay display, AssetManager manager) {
        this.assets = new MainMenuAssets(manager);
        this.builder = new MainMenuBuilder(display, assets);
    }

    @Override
    public void load(GameContext context) {
        assets.load();
    }

    @Override
    public void unload(GameContext context) {
    }

    @Override
    public MainMenu get(Identifier identifier) {
        Validate.isInstanceOf(MainMenuType.class, identifier);

        switch((MainMenuType)identifier) {
            case Home: return getHomeMenu();
            case Campaign: return getCampaignMenu();
            case CampaignNew: return getNewCampaignMenu();
            case CampaignLoad: return getLoadCampaignMenu();
            default: throw new UnsupportedOperationException();
        }
    }

    private MainMenu getHomeMenu() {
        MainMenu menu = builder.build();
        MainMenuStrings strings = assets.getStrings();
        menu.insertButton(strings.getSinglePlayer(), showMenu(menu, Campaign));
        menu.insertButton(strings.getMultiPlayer());
        menu.insertButton(strings.getReplyIntro());
        menu.insertButton(strings.getShowCredits());
        menu.insertButton(strings.getExit(), shutdown());
        return menu;
    }

    private MainMenu getCampaignMenu() {
        MainMenu menu = builder.build();
        MainMenuStrings strings = assets.getStrings();
        menu.insertButton(strings.getNewCampaign(), showMenu(menu, CampaignNew));
        menu.insertButton(strings.getLoad());
        menu.insertButton(strings.getCustomCampaign());
        menu.insertButton(strings.getPrevious(), showMenu(menu, Home));
        return menu;
    }

    private MainMenu getNewCampaignMenu() {
        MainMenu menu = builder.build();
        MainMenuStrings strings = assets.getStrings();
        menu.insertButton(strings.getHumanCampaign(), showMenu(menu, Human1));
        menu.insertButton(strings.getOrcCampaign(), showMenu(menu, Orc1));
        menu.insertButton(strings.getPrevious(), showMenu(menu, Campaign));
        return menu;
    }

    private MainMenu getLoadCampaignMenu() {
        MainMenu menu = builder.build();
        MainMenuStrings strings = assets.getStrings();
        menu.insertButton(strings.getPrevious(), showMenu(menu, Home));
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
}
