package com.evilbird.warcraft.item.unit.neutral;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.graphics.DirectionalAnimation;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.utility.AssetObjectProvider;
import com.evilbird.engine.utility.Identifier;
import com.evilbird.warcraft.item.unit.AnimatedItem;
import com.evilbird.warcraft.item.unit.AnimationBuilder;

import org.apache.commons.lang3.Range;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

//TODO: Add default animation
public class Tree implements AssetObjectProvider<Item>
{
    private AssetManager assets;

    @Inject
    public Tree(Device device)
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

        Map<Identifier, Object> properties = new HashMap<Identifier, Object>();
        properties.put(new Identifier("Type"), new Identifier("Wood"));
        properties.put(new Identifier("Animation"), new Identifier("Idle"));
        properties.put(new Identifier("Selected"), false);
        properties.put(new Identifier("Enabled"), true);
        properties.put(new Identifier("Wood"), 100f);
        properties.put(new Identifier("Id"), new Identifier());

        return new AnimatedItem(properties, animations);
    }
}
