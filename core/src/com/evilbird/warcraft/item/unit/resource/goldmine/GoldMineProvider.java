package com.evilbird.warcraft.item.unit.resource.goldmine;

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
import com.evilbird.warcraft.common.AnimationBuilder;
import com.evilbird.warcraft.item.unit.resource.Resource;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

public class GoldMineProvider implements AssetProvider<Item>
{
    private AssetManager assets;

    @Inject
    public GoldMineProvider(Device device)
    {
        this.assets = device.getAssetStorage().getAssets();
    }

    @Override
    public void load()
    {
        assets.load("data/textures/neutral/winter/gold_mine.png", Texture.class);
        assets.load("data/textures/neutral/perennial/construction.png", Texture.class);
    }

    @Override
    public Item get()
    {
        Resource result = new Resource();
        result.setActions(getActions());
        result.setAvailableAnimations(getAnimations());
        result.setAnimation(new Identifier("Idle"));
        result.setAvailableSounds(getSounds());
        result.setHealth(2500.0f);
        result.setHealthMaximum(2500.0f);
        result.setIcon(getIcon());
        result.setName("Gold Mine");
        result.setSelected(false);
        result.setSelectable(true);
        result.setTouchable(Touchable.enabled);
        result.setType(new Identifier("GoldMine"));
        result.setAmount(2500f);
        result.setSize(96, 96);
        return result;
    }

    private EnumSet<ActionType> getActions()
    {
        EnumSet<ActionType> actions = EnumSet.noneOf(ActionType.class);
        return actions;
    }

    private Map<Identifier, DirectionalAnimation> getAnimations()
    {
        Texture texture = assets.get("data/textures/neutral/winter/gold_mine.png", Texture.class);
        Texture constructionTexture = assets.get("data/textures/neutral/perennial/construction.png", Texture.class);
        Map<Identifier, DirectionalAnimation> animations = AnimationBuilder.getBuildingAnimationSet(texture, constructionTexture, 96);
        animations.put(new Identifier("GatherGold"), animations.get(new Identifier("Construct")));
        animations.remove(new Identifier("Construct"));
        return animations;
    }

    private Drawable getIcon()
    {
        Texture iconTexture = assets.get("data/textures/neutral/perennial/icons.png", Texture.class);
        TextureRegion iconRegion = new TextureRegion(iconTexture, 184, 532, 46, 38);
        return new TextureRegionDrawable(iconRegion);
    }

    private Map<Identifier, SoundEffect> getSounds()
    {
        Map<Identifier, SoundEffect> sounds = new HashMap<Identifier, SoundEffect>();
        return sounds;
    }
}
