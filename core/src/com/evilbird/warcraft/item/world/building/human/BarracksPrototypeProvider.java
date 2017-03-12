package com.evilbird.warcraft.item.world.building.human;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.evilbird.engine.common.audio.SoundEffect;
import com.evilbird.engine.common.graphics.DirectionalAnimation;
import com.evilbird.engine.common.inject.AssetProvider;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.action.ActionType;
import com.evilbird.warcraft.item.world.building.BuildingPrototype;
import com.evilbird.warcraft.item.world.unit.common.AnimationBuilder;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class BarracksPrototypeProvider implements AssetProvider<Item>
{
    private AssetManager assets;

    @Inject
    public BarracksPrototypeProvider(Device device)
    {
        this.assets = device.getAssetStorage().getAssets();
    }

    @Override
    public void load()
    {
        assets.load("data/textures/human/winter/barracks.png", Texture.class);
        assets.load("data/textures/neutral/perennial/construction.png", Texture.class);
        assets.load("data/textures/neutral/perennial/icons.png", Texture.class);
    }

    @Override
    public Item get()
    {
        BuildingPrototype result = new BuildingPrototype();
        result.setActions(getActions());
        result.setAvailableAnimations(getAnimations());
        result.setAnimation(new Identifier("Idle"));
        result.setAvailableSounds(getSounds());
        result.setArmour(0f);
        result.setDamageMinimum(0f);
        result.setDamageMaximum(0f);
        result.setHealth(800.0f);
        result.setHealthMaximum(800.0f);
        result.setIcon(getIcon());
        result.setLevel(1);
        result.setName("Barracks");
        result.setRange(0f);
        result.setSelected(false);
        result.setSelectable(true);
        result.setTouchable(Touchable.enabled);
        result.setSpeed(0f);
        result.setSight(4f);
        result.setType(new Identifier("BarracksPrototype"));
        result.setSize(96, 96);


        result.update(0);

        return result;
    }

    private EnumSet<ActionType> getActions()
    {
        EnumSet<ActionType> actions = EnumSet.noneOf(ActionType.class);
        return actions;
    }

    private Map<Identifier, DirectionalAnimation> getAnimations()
    {
        Texture texture = assets.get("data/textures/human/winter/barracks.png", Texture.class);
        Texture constructionTexture = assets.get("data/textures/neutral/perennial/construction.png", Texture.class);
        return AnimationBuilder.getBuildingAnimationSet(texture, constructionTexture, 96);
    }

    private Drawable getIcon()
    {
        Texture iconTexture = assets.get("data/textures/neutral/perennial/icons.png", Texture.class);
        TextureRegion iconRegion = new TextureRegion(iconTexture, 46, 684, 46, 38);
        return new TextureRegionDrawable(iconRegion);
    }

    private Map<Identifier, SoundEffect> getSounds()
    {
        Map<Identifier, SoundEffect> sounds = new HashMap<Identifier, SoundEffect>();
        return sounds;
    }
}
