/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.menu.ingame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.evilbird.engine.common.control.SelectListener;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.device.DeviceDisplay;
import com.evilbird.engine.game.GameFactory;
import com.evilbird.engine.menu.MenuIdentifier;
import com.evilbird.engine.state.StateService;
import com.evilbird.warcraft.item.unit.UnitFaction;
import com.evilbird.warcraft.menu.outro.OutroMenuType;
import com.evilbird.warcraft.state.WarcraftSave;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.Collection;

import static com.evilbird.warcraft.menu.ingame.IngameMenuLayout.Normal;
import static com.evilbird.warcraft.menu.ingame.IngameMenuLayout.Small;
import static com.evilbird.warcraft.menu.ingame.IngameMenuLayout.Wide;
import static com.evilbird.warcraft.menu.ingame.IngameMenuType.Confirm;
import static com.evilbird.warcraft.menu.ingame.IngameMenuType.Defeat;
import static com.evilbird.warcraft.menu.ingame.IngameMenuType.Exit;
import static com.evilbird.warcraft.menu.ingame.IngameMenuType.Load;
import static com.evilbird.warcraft.menu.ingame.IngameMenuType.Objectives;
import static com.evilbird.warcraft.menu.ingame.IngameMenuType.Options;
import static com.evilbird.warcraft.menu.ingame.IngameMenuType.Preferences;
import static com.evilbird.warcraft.menu.ingame.IngameMenuType.Root;
import static com.evilbird.warcraft.menu.ingame.IngameMenuType.Save;
import static com.evilbird.warcraft.menu.ingame.IngameMenuType.Sounds;
import static com.evilbird.warcraft.menu.ingame.IngameMenuType.Speeds;
import static com.evilbird.warcraft.menu.intro.IntroMenuType.Human1;
import static com.evilbird.warcraft.state.WarcraftStateType.UserState;

/**
 * Instances of this factory create {@link IngameMenu} instances, menus
 * accessible during the game and rendered over a paused game state.
 *
 * @author Blair Butterworth
 */
@SuppressWarnings("unchecked")
public class IngameMenuFactory implements GameFactory<IngameMenu>
{
    private static final Logger LOGGER = LoggerFactory.getLogger(IngameMenuFactory.class);

    private StateService states;
    private DeviceDisplay display;
    private IngameMenuAssets assets;
    private IngameMenuBuilder builder;

    @Inject
    public IngameMenuFactory(Device device, StateService states) {
        this(device, states, device.getAssetStorage());
    }

    public IngameMenuFactory(Device device, StateService states, AssetManager manager) {
        this.states = states;
        this.display = device.getDeviceDisplay();
        this.assets = new IngameMenuAssets(manager, UnitFaction.Human);
        this.builder = new IngameMenuBuilder(display, assets);
    }

    @Override
    public void load(Identifier context) {
        assets.load();
    }

    @Override
    public void unload(Identifier context) {
    }

    @Override
    public IngameMenu get(Identifier type) {
        IngameMenu menu = builder.build();
        return setLayout(menu, type);
    }

    private IngameMenu setLayout(IngameMenu menu, Identifier identifier) {
        switch ((IngameMenuType)identifier) {
            case Root: return setRootLayout(menu);
            case Save: return setSaveLayout(menu);
            case Load: return setLoadLayout(menu);
            case Exit: return setExitLayout(menu);
            case Confirm: return setConfirmLayout(menu);
            case Options: return setOptionsLayout(menu);
            case Sounds: return setSoundsLayout(menu);
            case Speeds: return setSpeedsLayout(menu);
            case Preferences: return setPreferencesLayout(menu);
            case Objectives: return setObjectivesLayout(menu);
            case Defeat: return setDefeatLayout(menu);
            case Victory: return setVictoryLayout(menu);
            default: throw new UnsupportedOperationException();
        }
    }

    private IngameMenu setRootLayout(IngameMenu menu) {
        menu.setLayout(Normal);
        menu.addTitle("Game Menu");
        menu.addButton("Save", showMenu(menu, Save));
        menu.addButton("Load", showMenu(menu, Load));
        menu.addButton("Options", showMenu(menu, Options));
        menu.addButton("Scenario Objectives", showMenu(menu, Objectives));
        menu.addButton("End Scenario", showMenu(menu, Exit));
        menu.addSpacer();
        menu.addButton("Return to Game", showState(menu));
        return menu;
    }

    private IngameMenu setSaveLayout(IngameMenu menu) {
        menu.setLayout(Wide);
        menu.addTitle("Save Game");

        TextField field = menu.addTextField("");
        List list = menu.addList();
        menu.addButtonRow(
            Pair.of("Save", saveState(menu, field)),
            Pair.of("Delete", deleteState(menu, list)),
            Pair.of("Cancel", showState(menu)));

        addStates(menu, list);
        return menu;
    }

    private IngameMenu setLoadLayout(IngameMenu menu) {
        menu.setLayout(Wide);
        menu.addTitle("Load Game");

        List list = menu.addList();
        menu.addButtonRow(
            Pair.of("Load", loadState(menu, list)),
            Pair.of("Delete", deleteState(menu, list)),
            Pair.of("Cancel", showState(menu)));

        addStates(menu, list);
        return menu;
    }

    private IngameMenu setExitLayout(IngameMenu menu) {
        menu.setLayout(Normal);
        menu.addTitle("End Scenario");
        menu.addButton("Restart Scenario", showMenu(menu, Human1));
        menu.addButton("Surrender", showMenu(menu, Confirm));
        menu.addButton("Quit to Menu", showMenu(menu, Confirm));
        menu.addButton("Exit Program", shutdown());
        menu.addSpacer();
        menu.addButton("Previous", showMenu(menu, Root));
        return menu;
    }

    private IngameMenu setConfirmLayout(IngameMenu menu) {
        menu.setLayout(Normal);
        menu.addTitle("Are you sure you want to surrender to your enemies?");
        menu.addButton("Surrender", showMenu(menu, Defeat));
        menu.addSpacer();
        menu.addButton("Cancel", showState(menu));
        return menu;
    }

    private IngameMenu setOptionsLayout(IngameMenu menu) {
        menu.setLayout(Normal);
        menu.addTitle("Game Options");
        menu.addButton("Sounds", showMenu(menu, Sounds));
        menu.addButton("Speeds", showMenu(menu, Speeds));
        menu.addButton("Preferences", showMenu(menu, Preferences));
        menu.addSpacer();
        menu.addButton("Previous", showMenu(menu, Root));
        return menu;
    }

    private IngameMenu setSoundsLayout(IngameMenu menu) {
        menu.setLayout(Normal);
        menu.addTitle("Sound Settings");
        menu.addSpacer();
        menu.addButtonRow(
            Pair.of("OK", showState(menu)),
            Pair.of("Cancel", showState(menu)));
        return menu;
    }

    private IngameMenu setSpeedsLayout(IngameMenu menu) {
        menu.setLayout(Normal);
        menu.addTitle("Speed Settings");
        menu.addSpacer();
        menu.addButtonRow(
            Pair.of("OK", showState(menu)),
            Pair.of("Cancel", showState(menu)));
        return menu;
    }

    private IngameMenu setPreferencesLayout(IngameMenu menu) {
        menu.setLayout(Normal);
        menu.addTitle("Preferences");
        menu.addSpacer();
        menu.addButtonRow(
            Pair.of("OK", showState(menu)),
            Pair.of("Cancel", showState(menu)));
        return menu;
    }

    private IngameMenu setObjectivesLayout(IngameMenu menu) {
        menu.setLayout(Normal);
        menu.addTitle("Scenario Objectives");
        menu.addLabel(" - Build four Farms");
        menu.addLabel(" - Build a Barracks");
        menu.addSpacer();
        menu.addButton("Previous", showMenu(menu, Root));
        return menu;
    }

    private IngameMenu setDefeatLayout(IngameMenu menu) {
        menu.setLayout(Small);
        menu.addTitle("You failed to achieve victory!");
        menu.addSpacer();
        menu.addButton("OK", showMenu(menu, OutroMenuType.Defeat));
        return menu;
    }

    private IngameMenu setVictoryLayout(IngameMenu menu) {
        menu.setLayout(Small);
        menu.addTitle("Victory!");
        menu.addSpacer();
        menu.addButton("OK", showMenu(menu, OutroMenuType.Victory));
        return menu;
    }

    private void addStates(IngameMenu menu, List list) {
        try {
            Collection<Identifier> items = states.list(UserState);
            list.setItems(items.toArray());
        }
        catch (Throwable error) {
            LOGGER.error("Failed to list states", error);
            menu.showError(error);
        }
    }

    private SelectListener saveState(IngameMenu menu, TextField field) {
        return () -> {
            try {
                menu.saveState(new WarcraftSave(field.getText()));
            }
            catch (Throwable error) {
                LOGGER.error("Failed to save state", error);
                menu.showError(error);
            }
        };
    }

    private SelectListener deleteState(IngameMenu menu, List list) {
        return () -> {
            try {
                states.remove((WarcraftSave)list.getSelected());
            }
            catch (Throwable error) {
                LOGGER.error("Failed to remove state", error);
                menu.showError(error);
            }
        };
    }

    private SelectListener loadState(IngameMenu menu, List list) {
        return () -> {
            try {
                menu.showState((WarcraftSave)list.getSelected());
            }
            catch (Throwable error) {
                LOGGER.error("Failed to load state", error);
                menu.showError(error);
            }
        };
    }

    private SelectListener showState(IngameMenu menu) {
        return menu::showState;
    }

    private SelectListener showMenu(IngameMenu menu, IngameMenuType type) {
        return () -> menu.showMenuOverlay(type);
    }

    private SelectListener showMenu(IngameMenu menu, MenuIdentifier type) {
        return () -> menu.showMenu(type);
    }

    private SelectListener shutdown() {
        return () -> Gdx.app.exit();
    }
}
