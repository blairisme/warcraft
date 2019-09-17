/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.menu.ingame;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.device.DeviceDisplay;
import com.evilbird.engine.game.GameContext;
import com.evilbird.engine.game.GameFactory;
import com.evilbird.engine.state.StateService;
import com.evilbird.warcraft.common.WarcraftPreferences;
import com.evilbird.warcraft.menu.ingame.variant.DefeatMenu;
import com.evilbird.warcraft.menu.ingame.variant.LoadMenu;
import com.evilbird.warcraft.menu.ingame.variant.ObjectivesMenu;
import com.evilbird.warcraft.menu.ingame.variant.OptionsMenu;
import com.evilbird.warcraft.menu.ingame.variant.QuitMenu;
import com.evilbird.warcraft.menu.ingame.variant.RootMenu;
import com.evilbird.warcraft.menu.ingame.variant.SaveMenu;
import com.evilbird.warcraft.menu.ingame.variant.SoundsMenu;
import com.evilbird.warcraft.menu.ingame.variant.SurrenderMenu;
import com.evilbird.warcraft.menu.ingame.variant.VictoryMenu;
import com.evilbird.warcraft.state.WarcraftContext;

import javax.inject.Inject;

/**
 * Instances of this factory create {@link IngameMenu} instances, menus
 * accessible during the game and rendered over a paused game state.
 *
 * @author Blair Butterworth
 */
@SuppressWarnings("unchecked")
public class IngameMenuFactory implements GameFactory<IngameMenu>
{
    private AssetManager manager;
    private StateService states;
    private DeviceDisplay display;
    private IngameMenuAssets assets;
    private IngameMenuBuilder builder;
    private WarcraftPreferences preferences;

    @Inject
    public IngameMenuFactory(
        Device device,
        StateService states,
        WarcraftPreferences preferences)
    {
        this(device.getDeviceDisplay(),
            states,
            device.getAssetStorage(),
            preferences);
    }

    public IngameMenuFactory(
        DeviceDisplay display,
        StateService states,
        AssetManager manager,
        WarcraftPreferences preferences)
    {
        this.states = states;
        this.display = display;
        this.manager = manager;
        this.preferences = preferences;
    }

    @Override
    public IngameMenu get(Identifier type) {
        IngameMenu menu = builder.build();
        return setLayout(menu, type);
    }

    @Override
    public void load(GameContext context) {
        assets = new IngameMenuAssets(manager, (WarcraftContext)context);
        builder = new IngameMenuBuilder(display, assets);
        assets.load();
    }

    @Override
    public void unload(GameContext context) {
        if (assets != null) {
            assets.unload();
        }
    }

    private IngameMenu setLayout(IngameMenu menu, Identifier identifier) {
        switch ((IngameMenuType)identifier) {
            case Root: return new RootMenu(menu, assets.getStrings());
            case Save: return new SaveMenu(menu, assets.getStrings(), states);
            case Load: return new LoadMenu(menu, assets.getStrings(), states);
            case Exit: return new QuitMenu(menu, assets.getStrings());
            case Confirm: return new SurrenderMenu(menu, assets.getStrings());
            case Options: return new OptionsMenu(menu, assets.getStrings());
            case Sounds: return new SoundsMenu(menu, assets.getStrings(), preferences);
            case Objectives: return new ObjectivesMenu(menu, assets.getStrings());
            case Defeat: return new DefeatMenu(menu, assets.getStrings());
            case Victory: return new VictoryMenu(menu, assets.getStrings());
            default: throw new UnsupportedOperationException();
        }
    }
}
