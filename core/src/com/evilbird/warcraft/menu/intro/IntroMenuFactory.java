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
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.evilbird.engine.common.graphics.Fonts;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.device.DeviceDisplay;
import com.evilbird.engine.game.GameFactory;
import com.evilbird.engine.state.StateIdentifier;
import com.evilbird.warcraft.state.campaign.WarcraftCampaign;
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
    private AssetManager assetManager;

    @Inject
    public IntroMenuFactory(Device device) {
        this.display = device.getDeviceDisplay();
        this.assetManager = device.getAssetStorage();
    }

    @Override
    public void load(Identifier context) {
    }

    @Override
    public void unload(Identifier context) {
    }

    @Override
    public IntroMenu get(Identifier identifier) {
        Validate.isInstanceOf(IntroMenuType.class, identifier);
        IntroMenuType type = (IntroMenuType)identifier;
        WarcraftCampaign campaign = WarcraftCampaign.valueOf(type.name());
        IntroMenuAssets assets = getAssets(type);
        return getIntro(campaign, assets);
    }

    private IntroMenuAssets getAssets(IntroMenuType type) {
        IntroMenuAssets assets = new IntroMenuAssets(assetManager, type);
        assets.load();
        return assets;
    }

    private IntroMenu getIntro(WarcraftCampaign campaign, IntroMenuAssets assets) {
        Skin skin = getSkin(assets);
        IntroMenu menu = new IntroMenu(display, skin);
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

    private Skin getSkin(IntroMenuAssets assets) {
        Skin skin = new Skin();
        addLabelStyle(skin, assets);
        addButtonStyle(skin, assets);
        addBackgroundStyle(skin, assets);
        return skin;
    }

    private void addBackgroundStyle(Skin skin, IntroMenuAssets assets) {
        Drawable background = assets.getBackground();
        skin.add("intro-background", background, Drawable.class);
    }

    private void addLabelStyle(Skin skin, IntroMenuAssets assets) {
        LabelStyle labelStyle = new LabelStyle();
        labelStyle.font = assets.getFont();
        labelStyle.fontColor = Color.WHITE;
        skin.add("default", labelStyle);
    }

    private void addButtonStyle(Skin skin, IntroMenuAssets assets) {
        TextButtonStyle textButtonStyle = new TextButtonStyle();
        textButtonStyle.font = Fonts.ARIAL;
        textButtonStyle.fontColor = Color.WHITE;
        textButtonStyle.up = assets.getButtonUp();
        textButtonStyle.over = textButtonStyle.up;
        textButtonStyle.checked = textButtonStyle.up;
        textButtonStyle.checkedOver = textButtonStyle.up;
        textButtonStyle.disabled = assets.getButtonDisabled();
        textButtonStyle.down = assets.getButtonDown();
        skin.add("default", textButtonStyle);
    }
}
