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
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.evilbird.engine.common.graphics.DirectionalAnimation;
import com.evilbird.engine.common.inject.AssetProvider;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.specialized.animated.AnimationIdentifier;
import com.evilbird.warcraft.item.common.animation.AnimationCollections;
import com.evilbird.warcraft.item.unit.UnitAnimation;
import com.evilbird.warcraft.item.unit.UnitType;
import com.evilbird.warcraft.item.unit.building.Building;

import javax.inject.Inject;
import java.util.Map;

/**
 * Instances of this class create {@link Building Farms}, loading the
 * necessary assets and defining the appropriate attributes.
 *
 * @author Blair Butterworth
 */
public class FarmProvider implements AssetProvider<Item>
{
    public static final String BASE = "data/textures/human/winter/farm.png";
    public static final String CONSTRUCTION = "data/textures/neutral/perennial/construction.png";
    public static final String DESTRUCTION = "data/textures/neutral/winter/destroyed_site.png";
    public static final String ICONS = "data/textures/neutral/perennial/icons.png";
    private AssetManager assets;

    @Inject
    public FarmProvider(Device device) {
        this.assets = device.getAssetStorage().getAssets();
    }

    @Override
    public void load() {
        assets.load(BASE, Texture.class);
        assets.load(CONSTRUCTION, Texture.class);
        assets.load(DESTRUCTION, Texture.class);
        assets.load(ICONS, Texture.class);
    }

    @Override
    public Item get() {
        Building result = new Building();
        result.setAvailableAnimations(getAnimations());
        result.setAnimation(UnitAnimation.Idle);
        result.setHealth(400.0f);
        result.setHealthMaximum(400.0f);
        result.setIcon(getIcon());
        result.setName("Farm");
        result.setType(UnitType.Farm);
        result.setSize(64, 64);
        return result;
    }

    private Map<AnimationIdentifier, DirectionalAnimation> getAnimations() {
        Texture general = assets.get(BASE, Texture.class);
        Texture construction = assets.get(CONSTRUCTION, Texture.class);
        Texture destruction = assets.get(DESTRUCTION, Texture.class);
        return AnimationCollections.buildingAnimations(general, construction, destruction, 64, 64);
    }

    private Drawable getIcon() {
        Texture iconTexture = assets.get(ICONS, Texture.class);
        TextureRegion iconRegion = new TextureRegion(iconTexture, 138, 266, 46, 38);
        return new TextureRegionDrawable(iconRegion);
    }
}
