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
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.evilbird.engine.common.inject.AssetProvider;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.level.Level;
import com.evilbird.engine.menu.IndexMenu;
import com.evilbird.warcraft.menu.common.IntroMenu;

import javax.inject.Inject;
import javax.inject.Provider;
import java.util.Collections;
import java.util.List;

public class HumanIntroOneFactory implements AssetProvider<IndexMenu>
{
    private static final String BUTTON = "data/textures/menu/button.png";
    private static final String BACKGROUND = "data/textures/menu/introduction.png";
    private static final String NARATION_1 = "data/sounds/menu/human/intro1a.mp3";
    private static final String NARATION_2 = "data/sounds/menu/human/intro1b.mp3";

    private AssetManager assets;
    private Provider<Level> levelFactory;

    @Inject
    public HumanIntroOneFactory(Device device, Provider<Level> levelFactory) {
        this.assets = device.getAssetStorage().getAssets();
        this.levelFactory = levelFactory;
    }

    @Override
    public void load() {
        assets.load(BUTTON, Texture.class);
        assets.load(BACKGROUND, Texture.class);
    }

    @Override
    public IndexMenu get() {
        IntroMenu menu = new IntroMenu();
        menu.setBackground(getBackground());
        menu.setButtonTexture(getButtonTexture());
        menu.setTitle(getTitle());
        menu.setDescription(getDescription());
        menu.setObjectives(getObjectives());
        menu.setMusic(getMusic());
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

    private String getTitle() {
        return "I. HILLSBRAD";
    }

    private String getDescription() {
        return "Due to your position as regional commander of the southern " +
                "defense forces, Lord Terenas commands that you raise an outpost" +
                " in the Hillsbrad foothills.\n" +
                "\n" +
                "It is rumored that Orcish marauders have been raiding coastal" +
                " towns in the area, but whether these attacks are part of a " +
                "greater Horde offensive is, as yet, unknown.\n" +
                "\n" +
                "Your outpost is to provide food and information for Alliance " +
                "troops and, as such, should be a community consisting of at " +
                "least four Farms.\n" +
                "\n" +
                "You must also construct a Barracks in order to safeguard the " +
                "Hillsbrad operation.";
    }

    private String getObjectives() {
        return "Objectives:\n" +
                "-Build four Farms\n" +
                "-Build a Barracks\n";
    }

    private List<Music> getMusic() {
        //Music music = Gdx.audio.newMusic(Gdx.files.internal("data/mymusic.mp3"));
        return Collections.emptyList();
    }
}
