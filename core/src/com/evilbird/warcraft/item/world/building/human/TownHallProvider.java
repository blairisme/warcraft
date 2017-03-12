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
import com.evilbird.warcraft.item.world.unit.Unit;
import com.evilbird.warcraft.item.world.unit.common.AnimationBuilder;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

public class TownHallProvider implements AssetProvider<Item>
{
    private AssetManager assets;

    @Inject
    public TownHallProvider(Device device)
    {
        this.assets = device.getAssetStorage().getAssets();
    }

    @Override
    public void load()
    {
        assets.load("data/textures/human/winter/town_hall.png", Texture.class);
        assets.load("data/textures/neutral/perennial/construction.png", Texture.class);
        assets.load("data/textures/neutral/perennial/icons.png", Texture.class);
    }

    @Override
    public Item get()
    {
        Unit result = new Unit();
        result.setActions(getActions());
        result.setAvailableAnimations(getAnimations());
        result.setAnimation(new Identifier("Idle"));
        result.setAvailableSounds(getSounds());
        result.setArmour(0f);
        result.setDamageMinimum(0f);
        result.setDamageMaximum(0f);
        result.setHealth(1200.0f);
        result.setHealthMaximum(1200.0f);
        result.setIcon(getIcon());
        result.setLevel(1);
        result.setName("Town Hall");
        result.setRange(0f);
        result.setSelected(false);
        result.setSelectable(true);
        result.setTouchable(Touchable.enabled);
        result.setSpeed(0f);
        result.setSight(4f);
        result.setType(new Identifier("TownHall"));
        result.setGold(1000f);
        result.setOil(0f);
        result.setWood(1000f);
        result.setSize(128, 128);
        return result;
    }

    private EnumSet<ActionType> getActions()
    {
        EnumSet<ActionType> actions = EnumSet.noneOf(ActionType.class);
        //actions.add(Actions.BuildPeasant);
        return actions;
    }

    private Map<Identifier, DirectionalAnimation> getAnimations()
    {
        Texture texture = assets.get("data/textures/human/winter/town_hall.png", Texture.class);
        Texture constructionTexture = assets.get("data/textures/neutral/perennial/construction.png", Texture.class);
        Map<Identifier, DirectionalAnimation> animations = AnimationBuilder.getBuildingAnimationSet(texture, constructionTexture, 128);
        animations.put(new Identifier("DepositGold"), animations.get(new Identifier("Idle")));
        animations.put(new Identifier("DepositWood"), animations.get(new Identifier("Idle")));
        return animations;
    }

    private Drawable getIcon()
    {
        Texture iconTexture = assets.get("data/textures/neutral/perennial/icons.png", Texture.class);
        TextureRegion iconRegion = new TextureRegion(iconTexture, 0, 304, 46, 38);
        return new TextureRegionDrawable(iconRegion);
    }

    private Map<Identifier, SoundEffect> getSounds()
    {
        Map<Identifier, SoundEffect> sounds = new HashMap<Identifier, SoundEffect>();
        return sounds;
    }
}
