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
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.evilbird.engine.common.audio.MusicCombination;
import com.evilbird.engine.common.audio.MusicSequence;
import com.evilbird.engine.common.inject.AssetProvider;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.menu.Menu;

import javax.inject.Inject;

import static com.evilbird.engine.common.assets.AssetUtilities.fontSize;

public class HumanIntroOneFactory implements AssetProvider<Menu>
{
    private static final String FONT = "data/fonts/philosopher.ttf";
    private static final String BUTTON = "data/textures/menu/button.png";
    private static final String BACKGROUND = "data/textures/menu/introduction.png";
    private static final String NARRATION_PART_1 = "data/sounds/menu/human/intro1a.mp3";
    private static final String NARRATION_PART_2 = "data/sounds/menu/human/intro1b.mp3";
    private static final String NARRATION_BACKGROUND = "data/music/04.mp3";
    private static final String CLICK = "data/sounds/menu/click.mp3";

    private AssetManager assets;

    @Inject
    public HumanIntroOneFactory(Device device) {
        this.assets = device.getAssetStorage().getAssets();
    }

    @Override
    public void load() {
        assets.load(BUTTON, Texture.class);
        assets.load(BACKGROUND, Texture.class);
        assets.load(FONT, BitmapFont.class, fontSize(20));
        assets.load(NARRATION_PART_1, Music.class);
        assets.load(NARRATION_PART_2, Music.class);
        assets.load(NARRATION_BACKGROUND, Music.class);
        assets.load(CLICK, Sound.class);
    }

    @Override
    public Menu get() {
        HumanIntroOne menu = new HumanIntroOne();
        menu.setBackground(getBackground());
        menu.setButtonTexture(getButtonTexture());
        menu.setFont(getFont());
        menu.setNarration(getNarration());
        menu.setButtonSound(getButtonClick());
        return menu;
    }

    private Drawable getBackground() {
        Texture menuTexture = assets.get(BACKGROUND);
        TextureRegion menuTextureRegion = new TextureRegion(menuTexture);
        return new TextureRegionDrawable(menuTextureRegion);
    }

    private TextureRegion getButtonTexture() {
        Texture buttonTexture = assets.get(BUTTON);
        return new TextureRegion(buttonTexture, 0, 56, 224, 28);
    }

    private BitmapFont getFont() {
        return assets.get(FONT);
    }

    private Sound getButtonClick() {
        return assets.get(CLICK);
    }

    private Music getNarration() {
        Music narration1 = assets.get(NARRATION_PART_1);
        Music narration2 = assets.get(NARRATION_PART_2);
        Music narration = new MusicSequence(narration1, narration2);

        Music background = assets.get(NARRATION_BACKGROUND);
        background.setVolume(0.7f);

        return new MusicCombination(narration, background);
    }
}
