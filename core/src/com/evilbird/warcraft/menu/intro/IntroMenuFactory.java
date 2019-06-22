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
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.I18NBundle;
import com.evilbird.engine.common.audio.MusicCombination;
import com.evilbird.engine.common.audio.MusicSequence;
import com.evilbird.engine.common.graphics.Fonts;
import com.evilbird.engine.common.inject.IdentifiedAssetProvider;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.device.DeviceDisplay;
import com.evilbird.engine.menu.Menu;
import com.evilbird.engine.state.StateIdentifier;
import com.evilbird.warcraft.state.campaign.WarcraftCampaign;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

import static com.evilbird.engine.common.assets.AssetUtilities.fontSize;
import static com.evilbird.engine.common.graphics.TextureUtils.getDrawable;

/**
 * Instances of this factory create {@link IntroMenu}s, menus that introduce a
 * scenario and explain the conditions required to win it.
 *
 * @author Blair Butterworth
 */
public class IntroMenuFactory implements IdentifiedAssetProvider<Menu>
{
    private static final Logger LOGGER = LoggerFactory.getLogger(IntroMenuFactory.class);
    private static final String FONT = "data/fonts/philosopher.ttf";
    private static final String BUTTON = "data/textures/menu/button.png";
    private static final String CLICK = "data/sounds/common/menu/click.mp3";
    private static final String NARRATION_BACKGROUND = "data/music/4.mp3";

    private static final String HUMAN_NARRATION_1A = "data/sounds/human/menu/intro1a.mp3";
    private static final String HUMAN_NARRATION_1B = "data/sounds/human/menu/intro1b.mp3";
    private static final String HUMAN_NARRATION_2A = "data/sounds/human/menu/intro2a.mp3";
    private static final String HUMAN_NARRATION_2B = "data/sounds/human/menu/intro2b.mp3";
    private static final String HUMAN_BACKGROUND_1 = "data/textures/human/menu/intro1.png";
    private static final String HUMAN_STRINGS_1 = "data/strings/human/menu/intro1";
    private static final String HUMAN_STRINGS_2 = "data/strings/human/menu/intro2";

    private static final String ORC_NARRATION_1 = "data/sounds/orc/menu/intro1.mp3";
    private static final String ORC_NARRATION_2 = "data/sounds/orc/menu/intro2.mp3";
    private static final String ORC_BACKGROUND_1 = "data/textures/orc/menu/intro1.png";
    private static final String ORC_STRINGS_1 = "data/strings/orc/menu/intro1";
    private static final String ORC_STRINGS_2 = "data/strings/orc/menu/intro2";

    private AssetManager assets;
    private DeviceDisplay display;

    @Inject
    public IntroMenuFactory(Device device) {
        this.assets = device.getAssetStorage();
        this.display = device.getDeviceDisplay();
    }

    @Override
    public void load() {
        loadCommonAssets();
        loadHumanAssets();
        loadOrcAssets();
    }

    private void loadCommonAssets() {
        assets.load(BUTTON, Texture.class);
        assets.load(FONT, BitmapFont.class, fontSize(20));
        assets.load(CLICK, Sound.class);
        assets.load(NARRATION_BACKGROUND, Music.class);
    }

    private void loadHumanAssets() {
        assets.load(HUMAN_NARRATION_1A, Music.class);
        assets.load(HUMAN_NARRATION_1B, Music.class);
        assets.load(HUMAN_NARRATION_1A, Music.class);
        assets.load(HUMAN_NARRATION_1B, Music.class);
        assets.load(HUMAN_BACKGROUND_1, Texture.class);
        assets.load(HUMAN_STRINGS_1, I18NBundle.class);
        assets.load(HUMAN_STRINGS_2, I18NBundle.class);
    }

    private void loadOrcAssets() {
        assets.load(ORC_NARRATION_1, Music.class);
        assets.load(ORC_NARRATION_2, Music.class);
        assets.load(ORC_BACKGROUND_1, Texture.class);
        assets.load(ORC_STRINGS_1, I18NBundle.class);
        assets.load(ORC_STRINGS_2, I18NBundle.class);
    }

    @Override
    public Menu get(Identifier identifier) {
        Validate.isInstanceOf(IntroMenuType.class, identifier);
        switch((IntroMenuType)identifier) {
            case Human1: return getHumanIntro1();
            case Human2: return getHumanIntro2();
            case Orc1: return getOrcIntro1();
            case Orc2: return getOrcIntro2();
            default: throw new UnsupportedOperationException();
        }
    }

    private Menu getHumanIntro1() {
        return getIntro(WarcraftCampaign.Human1, HUMAN_STRINGS_1, HUMAN_NARRATION_1A, HUMAN_NARRATION_1B);
    }

    private Menu getHumanIntro2() {
        return getIntro(WarcraftCampaign.Human2, HUMAN_STRINGS_2, HUMAN_NARRATION_2A, HUMAN_NARRATION_2B);
    }

    private Menu getOrcIntro1() {
        return getIntro(WarcraftCampaign.Orc1, ORC_STRINGS_1, ORC_NARRATION_1);
    }

    private Menu getOrcIntro2() {
        return getIntro(WarcraftCampaign.Orc2, ORC_STRINGS_2, ORC_NARRATION_2);
    }

    private Menu getIntro(WarcraftCampaign scenario, String strings, String ... narration) {
        Skin skin = getSkin(scenario);
        IntroMenu menu = new IntroMenu(display, skin);
        addContent(menu, strings);
        addNarration(menu, narration);
        addButtonAction(menu, scenario);
        return menu;
    }

    private void addContent(IntroMenu menu, String bundle) {
        IntroMenuStrings strings = new IntroMenuStrings(assets.get(bundle, I18NBundle.class));
        menu.setTitle(strings.getTitle());
        menu.setDescription(strings.getDescription());
        menu.setObjectives(strings.getObjectives());
    }

    private void addNarration(IntroMenu menu, String ... narrationAssets) {
        List<Music> narration = new ArrayList<>(narrationAssets.length);
        for (String narrationAsset: narrationAssets) {
            narration.add(assets.get(narrationAsset, Music.class));
        }
        addNarration(menu, narration);
    }

    private void addNarration(IntroMenu menu, List<Music> narrationSequence) {
        Music narration = new MusicSequence(narrationSequence);
        narration.setVolume(1f);

        Music background = assets.get(NARRATION_BACKGROUND, Music.class);
        background.setVolume(0.7f);

        Music music = new MusicCombination(narration, background);
        menu.setMusic(music);
    }

    private void addButtonAction(IntroMenu menu, StateIdentifier level) {
        menu.setButtonAction(() -> {
            try {
                menu.showState(level);
            }
            catch (Throwable error) {
                LOGGER.error("Failed to load state", error);
                menu.showError(error);
            }
        });
    }

    private Skin getSkin(WarcraftCampaign scenario) {
        Skin skin = new Skin();
        addLabelStyle(skin);
        addButtonStyle(skin);
        addBackgroundStyle(skin, scenario);
        return skin;
    }

    private void addBackgroundStyle(Skin skin, WarcraftCampaign scenario) {
        String asset = scenario.name().startsWith("Human") ? HUMAN_BACKGROUND_1 : ORC_BACKGROUND_1;
        skin.add("intro-background", getDrawable(assets, asset), Drawable.class);
    }

    private void addLabelStyle(Skin skin) {
        LabelStyle labelStyle = new LabelStyle();
        labelStyle.font = assets.get(FONT, BitmapFont.class);
        labelStyle.fontColor = Color.WHITE;
        skin.add("default", labelStyle);
    }

    private void addButtonStyle(Skin skin) {
        TextButtonStyle textButtonStyle = new TextButtonStyle();
        textButtonStyle.font = Fonts.ARIAL;
        textButtonStyle.fontColor = Color.WHITE;
        textButtonStyle.up = getDrawable(assets, BUTTON, 0, 0, 225, 30);
        textButtonStyle.over = textButtonStyle.up;
        textButtonStyle.checked = textButtonStyle.up;
        textButtonStyle.checkedOver = textButtonStyle.up;
        textButtonStyle.disabled = getDrawable(assets, BUTTON, 0, 60, 225, 30);
        textButtonStyle.down = getDrawable(assets, BUTTON, 0, 30, 225, 30);
        skin.add("default", textButtonStyle);
    }
}
