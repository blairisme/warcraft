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
import com.badlogic.gdx.audio.Music;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.device.DeviceDisplay;
import com.evilbird.engine.game.GameFactory;
import com.evilbird.engine.state.StateIdentifier;
import com.evilbird.warcraft.common.WarcraftContext;
import com.evilbird.warcraft.state.campaign.Campaign;
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
        this.display = device.getDeviceDisplay();
        this.manager = device.getAssetStorage();
    }

    public IntroMenuFactory(DeviceDisplay display, AssetManager manager) {
        this.display = display;
        this.manager = manager;
    }

    @Override
    public void load(Identifier context) {
        Validate.isInstanceOf(WarcraftContext.class, context);
        load((WarcraftContext)context);
    }

    private void load(WarcraftContext context) {
    }

    @Override
    public void unload(Identifier context) {
        assets.unload();
    }

    @Override
    public IntroMenu get(Identifier identifier) {
        Validate.isInstanceOf(IntroMenuType.class, identifier);
        IntroMenuType type = (IntroMenuType)identifier;

        assets = new IntroMenuAssets(manager, type);
        builder = new IntroMenuBuilder(display, assets);
        assets.load();
        manager.finishLoading();

        Campaign campaign = Campaign.valueOf(type.name());
        return getIntro(campaign);
    }

    private IntroMenu getIntro(Campaign campaign) {
        IntroMenu menu = builder.build();
        addContent(menu, assets);
        addNarration(menu, assets);
        addButtonAction(menu, campaign);
        return menu;
    }

    private void addContent(IntroMenu menu, IntroMenuAssets assets) {
        IntroMenuStrings strings = assets.getStrings();
        menu.setTitle(strings.getTitle());
        menu.setDescription(strings.getDescription());
        menu.setObjectives(strings.getObjectives());
    }

    private void addNarration(IntroMenu menu, IntroMenuAssets assets) {
        Music narration = assets.getNarration();
        menu.setMusic(narration);
    }

    private void addButtonAction(IntroMenu menu, StateIdentifier level) {
        menu.setButtonAction(() -> menu.showState(level));
    }
}
