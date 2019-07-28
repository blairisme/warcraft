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
import com.evilbird.engine.common.control.SelectListener;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.device.DeviceDisplay;
import com.evilbird.engine.game.GameContext;
import com.evilbird.engine.game.GameController;
import com.evilbird.engine.game.GameFactory;
import com.evilbird.engine.menu.MenuIdentifier;
import com.evilbird.engine.state.IntroducedState;
import com.evilbird.engine.state.State;
import com.evilbird.engine.state.StateIdentifier;
import com.evilbird.engine.state.StateSequence;
import com.evilbird.warcraft.menu.main.MainMenuType;
import com.evilbird.warcraft.state.WarcraftContext;
import org.apache.commons.lang3.Validate;

import javax.inject.Inject;

/**
 * Instances of this factory create {@link OutroMenu OutroMenus}, menus shown
 * when a scenario is completed, or failed.
 *
 * @author Blair Butterworth
 */
public class OutroMenuFactory implements GameFactory<OutroMenu>
{
    private AssetManager manager;
    private DeviceDisplay display;

    private OutroMenuAssets assets;
    private OutroMenuBuilder builder;

    @Inject
    public OutroMenuFactory(Device device) {
        this(device.getDeviceDisplay(), device.getAssetStorage());
    }

    public OutroMenuFactory(DeviceDisplay display, AssetManager manager) {
        this.display = display;
        this.manager = manager;
    }

    @Override
    public void load(GameContext context) {
        assets = new OutroMenuAssets(manager, (WarcraftContext)context);
        builder = new OutroMenuBuilder(display, assets);
        assets.load();
    }

    @Override
    public void unload(GameContext context) {
        if (assets != null) {
            assets.unload();
        }
    }

    @Override
    public OutroMenu get(Identifier identifier) {
        Validate.isInstanceOf(OutroMenuType.class, identifier);
        return getMenu((OutroMenuType)identifier);
    }

    private OutroMenu getMenu(OutroMenuType type) {
        OutroMenu menu = builder.build();
        menu.setType(type);
        menu.setLabelBundle(assets.getStrings());
        menu.setButtonAction(showNextIntro(menu));
        return menu;
    }

    private SelectListener showNextIntro(OutroMenu menu) {
        return () -> showNextMenu(menu);
    }

    private void showNextMenu(OutroMenu menu) {
        State currentState = getCurrentState(menu);
        StateIdentifier nextState = getNextState(currentState);
        MenuIdentifier menuIdentifier = getNextMenu(nextState);
        menu.showMenu(menuIdentifier);
    }

    private State getCurrentState(OutroMenu menu) {
        GameController controller = menu.getController();
        return controller.getState();
    }

    private StateIdentifier getNextState(State state) {
        if (state instanceof StateSequence) {
            StateSequence sequence = (StateSequence)state;
            return sequence.getNextState();
        }
        return null;
    }

    private MenuIdentifier getNextMenu(StateIdentifier state) {
        if (state instanceof IntroducedState) {
            IntroducedState introducedState = (IntroducedState)state;
            return introducedState.getIntroductionMenu();
        }
        return MainMenuType.Home;
    }
}
