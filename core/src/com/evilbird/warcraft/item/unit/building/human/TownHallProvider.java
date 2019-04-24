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
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.evilbird.engine.common.audio.SoundEffect;
import com.evilbird.engine.common.collection.Maps;
import com.evilbird.engine.common.graphics.DirectionalAnimation;
import com.evilbird.engine.common.graphics.TextureUtils;
import com.evilbird.engine.common.inject.AssetProvider;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.common.animation.AnimationSets;
import com.evilbird.warcraft.item.unit.UnitAnimation;
import com.evilbird.warcraft.item.unit.UnitSound;
import com.evilbird.warcraft.item.unit.UnitType;
import com.evilbird.warcraft.item.unit.building.Building;

import javax.inject.Inject;
import java.util.Map;

import static com.evilbird.engine.common.assets.AssetUtilities.loadSoundSet;
import static com.evilbird.engine.common.audio.SoundUtils.newSoundEffect;
import static com.evilbird.engine.common.file.FileType.MP3;

/**
 * Instances of this class create {@link Building Town Halls}, loading the
 * necessary assets and defining the appropriate attributes.
 *
 * @author Blair Butterworth
 */
public class TownHallProvider implements AssetProvider<Item>
{
    private static final String BASE = "data/textures/human/winter/town_hall.png";
    private static final String ICONS = "data/textures/neutral/perennial/icons.png";
    private static final String CONSTRUCTION = "data/textures/neutral/perennial/construction_large.png";
    private static final String DESTRUCTION = "data/textures/neutral/winter/destroyed_site.png";
    private static final String DESTROYED = "data/sounds/neutral/building/destroyed/";
    private AssetManager assets;

    @Inject
    public TownHallProvider(Device device) {
        this.assets = device.getAssetStorage();
    }

    @Override
    public void load() {
        loadTextures();
        loadSounds();
    }

    private void loadTextures() {
        assets.load(BASE, Texture.class);
        assets.load(CONSTRUCTION, Texture.class);
        assets.load(DESTRUCTION, Texture.class);
        assets.load(ICONS, Texture.class);
    }

    private void loadSounds() {
        loadSoundSet(assets, DESTROYED, MP3, 3);
    }

    @Override
    public Item get() {
        Building result = new Building();
        result.setAvailableAnimations(getAnimations());
        result.setAnimation(UnitAnimation.Idle);
        result.setAvailableSounds(getSounds());
        result.setSight(5 * 32);
        result.setHealth(1200);
        result.setHealthMaximum(1200);
        result.setIcon(getIcon());
        result.setName("Town Hall");
        result.setType(UnitType.TownHall);
        result.setSize(128, 128);
        return result;
    }

    private Map<Identifier, DirectionalAnimation> getAnimations() {
        Texture general = assets.get(BASE, Texture.class);
        Texture construct = assets.get(CONSTRUCTION, Texture.class);
        Texture destruction = assets.get(DESTRUCTION, Texture.class);
        return AnimationSets.buildingAnimations(general, construct, destruction, 128, 128);
    }

    private Drawable getIcon() {
        return TextureUtils.getDrawable(assets, ICONS, 0, 304, 46, 38);
    }

    private Map<Identifier, SoundEffect> getSounds() {
        return Maps.of(UnitSound.Die, newSoundEffect(assets, DESTROYED, MP3, 3));
    }
}
