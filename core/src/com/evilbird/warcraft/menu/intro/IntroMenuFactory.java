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
import com.evilbird.engine.menu.Menu;
import com.evilbird.engine.state.StateIdentifier;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

import static com.evilbird.engine.common.assets.AssetUtilities.fontSize;
import static com.evilbird.engine.common.graphics.TextureUtils.getDrawable;
import static com.evilbird.warcraft.state.WarcraftStateScenario.Human1;
import static java.util.Arrays.asList;

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
    private static final String BACKGROUND = "data/textures/menu/introduction.png";
    private static final String INTRO_NARRATION_1A = "data/sounds/menu/human/intro1a.mp3";
    private static final String INTRO_NARRATION_2B = "data/sounds/menu/human/intro1b.mp3";
    private static final String NARRATION_BACKGROUND = "data/music/04.mp3";
    private static final String CLICK = "data/sounds/menu/click.mp3";
    private static final String INTRO_BUNDLE_1 = "data/strings/intro1";

    private AssetManager assets;

    @Inject
    public IntroMenuFactory(Device device) {
        this.assets = device.getAssetStorage();
    }

    @Override
    public void load() {
        assets.load(BUTTON, Texture.class);
        assets.load(BACKGROUND, Texture.class);
        assets.load(FONT, BitmapFont.class, fontSize(20));
        assets.load(INTRO_NARRATION_1A, Music.class);
        assets.load(INTRO_NARRATION_2B, Music.class);
        assets.load(NARRATION_BACKGROUND, Music.class);
        assets.load(INTRO_BUNDLE_1, I18NBundle.class);
        assets.load(CLICK, Sound.class);
    }

    @Override
    public Menu get(Identifier identifier) {
        Validate.isInstanceOf(IntroMenuType.class, identifier);

        switch((IntroMenuType)identifier) {
            case HumanLevel1: return getHumanIntro1();
            default: throw new UnsupportedOperationException();
        }
    }

    private Menu getHumanIntro1() {
        IntroMenu menu = new IntroMenu(getSkin());
        addContent(menu, INTRO_BUNDLE_1);
        addNarration(menu, INTRO_NARRATION_1A, INTRO_NARRATION_2B);
        addButtonAction(menu, Human1);
        return menu;
    }

    private void addContent(IntroMenu menu, String bundle) {
        I18NBundle strings = assets.get(bundle, I18NBundle.class);
        menu.setTitle(strings.get("title"));
        menu.setDescription(strings.get("description"));
        menu.setObjectives(strings.get("objectives"));
    }

    private void addNarration(IntroMenu menu, String asset1, String asset2) {
        Music narration1 = assets.get(asset1, Music.class);
        Music narration2 = assets.get(asset2, Music.class);
        Music narration = new MusicSequence(asList(narration1, narration2));

        Music background = assets.get(NARRATION_BACKGROUND, Music.class);
        background.setVolume(0.7f);

        Music music = new MusicCombination(narration, background);
        menu.setMusic(music);
    }

    private Skin getSkin() {
        Skin skin = new Skin();
        addLabelStyle(skin);
        addButtonStyle(skin);
        addBackgroundStyle(skin);
        return skin;
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

    private void addBackgroundStyle(Skin skin) {
        skin.add("intro-background", getDrawable(assets, BACKGROUND), Drawable.class);
    }

    private void addLabelStyle(Skin skin) {
        LabelStyle labelStyle = new LabelStyle();
        labelStyle.font = assets.get(FONT, BitmapFont.class);
        labelStyle.fontColor = Color.WHITE;

        skin.add("default", labelStyle);
    }

    private void addButtonStyle(Skin skin) {
        Drawable enabled = getDrawable(assets, BUTTON, 0, 0, 225, 30);
        Drawable selected = getDrawable(assets, BUTTON, 0, 30, 225, 30);
        Drawable disabled = getDrawable(assets, BUTTON, 0, 60, 225, 30);

        TextButtonStyle textButtonStyle = new TextButtonStyle();
        textButtonStyle.font = Fonts.ARIAL;
        textButtonStyle.fontColor = Color.WHITE;
        textButtonStyle.up = enabled;
        textButtonStyle.over = enabled;
        textButtonStyle.checked = enabled;
        textButtonStyle.checkedOver = enabled;
        textButtonStyle.disabled = disabled;
        textButtonStyle.down = selected;

        skin.add("default", textButtonStyle);
        //menu.setButtonSound(assets.get(CLICK));
    }
}
