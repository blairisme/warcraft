/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.menu.intro;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.device.DeviceDisplay;
import com.evilbird.engine.game.GameContext;
import com.evilbird.engine.game.GameFactory;
import com.evilbird.warcraft.state.WarcraftCampaign;
import org.apache.commons.lang3.Validate;

import javax.inject.Inject;

/**
 * Instances of this factory create {@link IntroMenu}s, menus that introduce a
 * scenario and explain the conditions required to win it.
 *
 * @author Blair Butterworth
 */
public class IntroMenuFactory implements GameFactory<IntroMenu>
{
    private DeviceDisplay display;
    private AssetManager manager;

    private IntroMenuAssets assets;
    private IntroMenuBuilder builder;

    @Inject
    public IntroMenuFactory(Device device) {
        this(device.getDeviceDisplay(), device.getAssetStorage());
    }

    public IntroMenuFactory(DeviceDisplay display, AssetManager manager) {
        this.display = display;
        this.manager = manager;
    }

    @Override
    public void load(GameContext context) {
    }

    private void load(IntroMenuType type) {
        assets = new IntroMenuAssets(manager, type);
        builder = new IntroMenuBuilder(display, assets);
        assets.loadSynchronous();
    }

    @Override
    public void unload(GameContext context) {
    }

    @Override
    public IntroMenu get(Identifier identifier) {
        Validate.isInstanceOf(IntroMenuType.class, identifier);
        return get((IntroMenuType)identifier);
    }

    private IntroMenu get(IntroMenuType type) {
        load(type);
        return getIntro(type.getCampaign());
    }

    private IntroMenu getIntro(WarcraftCampaign campaign) {
        IntroMenu menu = builder.build();
        menu.setCampaign(campaign);
        menu.setText(assets.getStrings());
        menu.setMusic(assets.getNarration());
        return menu;
    }
}
