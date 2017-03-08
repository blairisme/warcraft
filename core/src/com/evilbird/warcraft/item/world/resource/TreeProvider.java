package com.evilbird.warcraft.item.world.resource;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.evilbird.engine.common.graphics.DirectionalAnimation;
import com.evilbird.engine.common.inject.AssetProvider;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.action.ActionType;
import com.evilbird.warcraft.item.world.unit.Unit;
import com.evilbird.warcraft.item.world.unit.common.AnimationBuilder;

import org.apache.commons.lang3.Range;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

//TODO: Add default animation
public class TreeProvider implements AssetProvider<Item>
{
    private AssetManager assets;

    @Inject
    public TreeProvider(Device device)
    {
        this.assets = device.getAssetStorage().getAssets();
    }

    @Override
    public void load()
    {
        assets.load("data/textures/neutral/winter/terrain.png", Texture.class);
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
        result.setName("Tree");
        result.setRange(0f);
        result.setSelected(false);
        result.setSelectable(true);
        result.setTouchable(Touchable.enabled);
        result.setSpeed(0f);
        result.setSight(4f);
        result.setType(new Identifier("Wood"));
        result.setGold(0f);
        result.setOil(0f);
        result.setWood(100f);
        result.setSize(32, 32);
        return result;
    }

    private EnumSet<ActionType> getActions()
    {
        EnumSet<ActionType> actions = EnumSet.noneOf(ActionType.class);
        return actions;
    }

    private Map<Identifier, DirectionalAnimation> getAnimations()
    {
        Texture texture = assets.get("data/textures/neutral/winter/terrain.png", Texture.class);

        Array<TextureRegion> deathTextures = AnimationBuilder.getAnimation(texture, 15 * 32, 7 * 32, 32);
        Map<Range<Float>, Array<TextureRegion>> deathFrames = new HashMap<Range<Float>, Array<TextureRegion>>(1);
        deathFrames.put(Range.between(0.0f, 360.0f), deathTextures);
        DirectionalAnimation deathAnimation = new DirectionalAnimation(0f, 0.15f, deathFrames, Animation.PlayMode.LOOP);

        Array<TextureRegion> idleTextures = AnimationBuilder.getAnimation(texture, 7 * 32, 0, 32);
        Map<Range<Float>, Array<TextureRegion>> idleFrames = new HashMap<Range<Float>, Array<TextureRegion>>(1);
        idleFrames.put(Range.between(0.0f, 360.0f), idleTextures);
        DirectionalAnimation idleAnimation = new DirectionalAnimation(0f, 0.15f, idleFrames, Animation.PlayMode.LOOP);

        Map<Identifier, DirectionalAnimation> animations = new HashMap<Identifier, DirectionalAnimation>();
        animations.put(new Identifier("Idle"), idleAnimation);
        animations.put(new Identifier("Dead"), deathAnimation);
        animations.put(new Identifier("GatherWood"), idleAnimation);

        return animations;
    }

    private Drawable getIcon()
    {
        Texture iconTexture = assets.get("data/textures/neutral/perennial/icons.png", Texture.class);
        TextureRegion iconRegion = new TextureRegion(iconTexture, 46, 684, 46, 38);
        return new TextureRegionDrawable(iconRegion);
    }

    private Map<Identifier, Sound> getSounds()
    {
        Map<Identifier, Sound> sounds = new HashMap<Identifier, Sound>();
        return sounds;
    }
}
