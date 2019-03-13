/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.building.human;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.evilbird.engine.common.audio.SoundEffect;
import com.evilbird.engine.common.graphics.DirectionalAnimation;
import com.evilbird.engine.common.inject.AssetProvider;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.animated.AnimationIdentifier;
import com.evilbird.engine.item.animated.SoundIdentifier;
import com.evilbird.warcraft.item.common.animation.AnimationCollections;
import com.evilbird.engine.common.graphics.TextureUtils;
import com.evilbird.warcraft.item.unit.UnitAnimation;
import com.evilbird.warcraft.item.unit.UnitSound;
import com.evilbird.warcraft.item.unit.UnitType;
import com.evilbird.warcraft.item.unit.building.Building;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

import static com.evilbird.engine.common.assets.AssetUtilities.loadSoundSet;
import static com.evilbird.engine.common.file.FileType.MP3;
import static com.evilbird.engine.common.audio.SoundUtils.newSoundEffect;

/**
 * Instances of this class create {@link Building Farms}, loading the
 * necessary assets and defining the appropriate attributes.
 *
 * @author Blair Butterworth
 */
public class FarmProvider implements AssetProvider<Item>
{
    private static final String MAIN = "data/textures/human/winter/farm.png";
    private static final String ICONS = "data/textures/neutral/perennial/icons.png";
    private static final String CONSTRUCTION = "data/textures/neutral/perennial/construction.png";
    private static final String DESTRUCTION = "data/textures/neutral/winter/destroyed_site.png";
    private static final String SELECTED = "data/sounds/human/building/farm/selected/1.mp3";
    private static final String DESTROYED = "data/sounds/neutral/building/destroyed/";
    private AssetManager assets;

    @Inject
    public FarmProvider(Device device) {
        this.assets = device.getAssetStorage();
    }

    @Override
    public void load() {
        loadTextures();
        loadSounds();
    }

    private void loadTextures() {
        assets.load(MAIN, Texture.class);
        assets.load(CONSTRUCTION, Texture.class);
        assets.load(DESTRUCTION, Texture.class);
        assets.load(ICONS, Texture.class);
    }

    private void loadSounds() {
        assets.load(SELECTED, Sound.class);
        loadSoundSet(assets, DESTROYED, MP3, 3);
    }

    @Override
    public Item get() {
        Building result = new Building();
        result.setAvailableAnimations(getAnimations());
        result.setAnimation(UnitAnimation.Idle);
        result.setAvailableSounds(getSounds());
        result.setSight(5 * 32);
        result.setHealth(400);
        result.setHealthMaximum(400);
        result.setIcon(getIcon());
        result.setName("Farm");
        result.setType(UnitType.Farm);
        result.setSize(64, 64);
        return result;
    }

    private Map<AnimationIdentifier, DirectionalAnimation> getAnimations() {
        Texture general = assets.get(MAIN, Texture.class);
        Texture construction = assets.get(CONSTRUCTION, Texture.class);
        Texture destruction = assets.get(DESTRUCTION, Texture.class);
        return AnimationCollections.buildingAnimations(general, construction, destruction, 64, 64);
    }

    private Drawable getIcon() {
        return TextureUtils.getDrawable(assets, ICONS, 138, 266, 46, 38);
    }

    private Map<SoundIdentifier, SoundEffect> getSounds() {
        Map<SoundIdentifier, SoundEffect> sounds = new HashMap<>();
        sounds.put(UnitSound.Selected, newSoundEffect(assets, SELECTED));
        sounds.put(UnitSound.Die, newSoundEffect(assets, DESTROYED, MP3, 3));
        return sounds;
    }
}
