/*
 * Blair Butterworth (c) 2018
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.resource.goldmine;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.evilbird.engine.common.audio.SilentSoundEffect;
import com.evilbird.engine.common.audio.SoundEffect;
import com.evilbird.engine.common.graphics.DirectionalAnimation;
import com.evilbird.engine.common.graphics.TextureUtils;
import com.evilbird.engine.common.inject.AssetProvider;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.animated.AnimationIdentifier;
import com.evilbird.engine.item.animated.SoundIdentifier;
import com.evilbird.warcraft.item.common.animation.AnimationCollections;
import com.evilbird.warcraft.item.unit.UnitAnimation;
import com.evilbird.warcraft.item.unit.UnitSound;
import com.evilbird.warcraft.item.unit.UnitType;
import com.evilbird.warcraft.item.unit.resource.Resource;
import com.evilbird.warcraft.item.unit.resource.ResourceType;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

import static com.evilbird.engine.common.assets.AssetUtilities.loadSoundSet;
import static com.evilbird.engine.common.audio.SoundUtils.newSoundEffect;
import static com.evilbird.engine.common.file.FileType.MP3;

public class GoldMineProvider implements AssetProvider<Item>
{
    private static final String BASE = "data/textures/neutral/winter/gold_mine.png";
    private static final String ICONS = "data/textures/neutral/perennial/icons.png";
    private static final String DESTRUCTION = "data/textures/neutral/winter/destroyed_site.png";
    private static final String CONSTRUCTION = "data/textures/neutral/perennial/construction.png";
    private static final String SELECTED = "data/sounds/neutral/resource/goldmine/selected/1.mp3";
    private static final String DESTROYED = "data/sounds/neutral/building/destroyed/";
    private AssetManager assets;

    @Inject
    public GoldMineProvider(Device device) {
        this.assets = device.getAssetStorage();
    }

    @Override
    public void load() {
        loadTextures();
        loadSounds();
    }

    private void loadTextures() {
        assets.load(BASE, Texture.class);
        assets.load(ICONS, Texture.class);
        assets.load(DESTRUCTION, Texture.class);
        assets.load(CONSTRUCTION, Texture.class);
    }

    private void loadSounds() {
        assets.load(SELECTED, Sound.class);
        loadSoundSet(assets, DESTROYED, MP3, 3);
    }

    @Override
    public Item get() {
        Resource result = new Resource();
        result.setAvailableAnimations(getAnimations());
        result.setAvailableSounds(getSounds());
        result.setAnimation(UnitAnimation.Idle);
        result.setHealth(2400f);
        result.setHealthMaximum(2400f);
        result.setIcon(getIcon());
        result.setName("Gold Mine");
        result.setSelected(false);
        result.setSelectable(true);
        result.setTouchable(Touchable.enabled);
        result.setType(UnitType.GoldMine);
        result.setResource(ResourceType.Gold, 2400f);
        result.setSize(96, 96);
        return result;
    }

    private Map<AnimationIdentifier, DirectionalAnimation> getAnimations() {
        Texture general = assets.get(BASE, Texture.class);
        Texture destruction = assets.get(DESTRUCTION, Texture.class);
        return AnimationCollections.resourceBuildingAnimations(general, destruction);
    }

    private Drawable getIcon() {
        return TextureUtils.getDrawable(assets, ICONS, 184, 532, 46, 38);
    }

    private Map<SoundIdentifier, SoundEffect> getSounds() {
        Map<SoundIdentifier, SoundEffect> sounds = new HashMap<>();
        sounds.put(UnitSound.GatherGold, new SilentSoundEffect());
        sounds.put(UnitSound.Selected, newSoundEffect(assets, SELECTED));
        sounds.put(UnitSound.Die, newSoundEffect(assets, DESTROYED, MP3, 3));
        return sounds;
    }
}
