package com.evilbird.warcraft.item.unit.building.human;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.evilbird.engine.common.graphics.DirectionalAnimation;
import com.evilbird.engine.common.inject.AssetProvider;
import com.evilbird.engine.common.lang.NamedIdentifier;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.specialized.animated.AnimationIdentifier;
import com.evilbird.warcraft.action.ActionType;
import com.evilbird.warcraft.item.common.animation.AnimationCollections;
import com.evilbird.warcraft.item.unit.UnitAnimation;
import com.evilbird.warcraft.item.unit.UnitType;
import com.evilbird.warcraft.item.unit.building.Building;

import java.util.EnumSet;
import java.util.Map;

import javax.inject.Inject;

public class TownHallProvider implements AssetProvider<Item>
{
    private AssetManager assets;

    @Inject
    public TownHallProvider(Device device) {
        this.assets = device.getAssetStorage().getAssets();
    }

    @Override
    public void load() {
        assets.load("data/textures/human/winter/town_hall.png", Texture.class);
        assets.load("data/textures/neutral/perennial/construction.png", Texture.class);
        assets.load("data/textures/neutral/winter/destroyed_site.png", Texture.class);
        assets.load("data/textures/neutral/perennial/icons.png", Texture.class);
    }

    @Override
    public Item get() {
        Building result = new Building();
        result.setActions(getActions());
        result.setAvailableAnimations(getAnimations());
        result.setAnimation(UnitAnimation.Idle);
        result.setHealth(1200.0f);
        result.setHealthMaximum(1200.0f);
        result.setIcon(getIcon());
        result.setName("Town Hall");
        result.setType(UnitType.TownHall);
        result.setSize(128, 128);
        return result;
    }

    private EnumSet<ActionType> getActions() {
        return EnumSet.of(ActionType.TrainPeasant);
    }

    private Map<AnimationIdentifier, DirectionalAnimation> getAnimations() {
        Texture general = assets.get("data/textures/human/winter/town_hall.png", Texture.class);
        Texture construct = assets.get("data/textures/neutral/perennial/construction.png", Texture.class);
        Texture destruction = assets.get("data/textures/neutral/winter/destroyed_site.png", Texture.class);
        return AnimationCollections.buildingAnimations(general, construct, destruction, 128, 128);
    }

    private Drawable getIcon() {
        Texture iconTexture = assets.get("data/textures/neutral/perennial/icons.png", Texture.class);
        TextureRegion iconRegion = new TextureRegion(iconTexture, 0, 304, 46, 38);
        return new TextureRegionDrawable(iconRegion);
    }
}
