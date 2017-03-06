package com.evilbird.warcraft.item.unit.neutral;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.evilbird.engine.common.graphics.DirectionalAnimation;
import com.evilbird.engine.common.inject.AssetObjectProvider;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.unit.common.AnimatedItem;
import com.evilbird.warcraft.item.unit.common.AnimationBuilder;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

public class GoldMineProvider implements AssetObjectProvider<Item>
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
        Texture texture = assets.get("data/textures/neutral/winter/gold_mine.png", Texture.class);
        Texture constructionTexture = assets.get("data/textures/neutral/perennial/construction.png", Texture.class);
        Map<Identifier, DirectionalAnimation> animations = AnimationBuilder.getBuildingAnimationSet(texture, constructionTexture, 96);
        animations.put(new Identifier("GatherGold"), animations.get(new Identifier("Construct")));
        animations.remove(new Identifier("Construct"));

        Texture iconTexture = assets.get("data/textures/neutral/perennial/icons.png", Texture.class);
        TextureRegion iconRegion = new TextureRegion(iconTexture, 184, 532, 46, 38);
        TextureRegionDrawable icon = new TextureRegionDrawable(iconRegion);

        Map<Identifier, Object> properties = new HashMap<Identifier, Object>();
        properties.put(new Identifier("Animation"), new Identifier("Idle"));
        properties.put(new Identifier("Selected"), false);
        properties.put(new Identifier("Gold"), 1000f);
        properties.put(new Identifier("Id"), new Identifier());
        properties.put(new Identifier("Icon"), icon);
        properties.put(new Identifier("Health"), 2500.0f);
        properties.put(new Identifier("HealthMaximum"), 2500.0f);

        AnimatedItem result = new AnimatedItem(properties, animations);
        result.setType(new Identifier("GoldMine"));

        return result;
    }
}
