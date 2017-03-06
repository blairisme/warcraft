package com.evilbird.warcraft.item.unit.human;

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

public class BarracksProvider implements AssetObjectProvider<Item>
{
    private AssetManager assets;

    @Inject
    public BarracksProvider(Device device)
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
        Texture texture = assets.get("data/textures/human/winter/barracks.png", Texture.class);
        Texture constructionTexture = assets.get("data/textures/neutral/perennial/construction.png", Texture.class);
        Map<Identifier, DirectionalAnimation> animations = AnimationBuilder.getBuildingAnimationSet(texture, constructionTexture, 96);

        Texture iconTexture = assets.get("data/textures/neutral/perennial/icons.png", Texture.class);
        TextureRegion iconRegion = new TextureRegion(iconTexture, 92, 304, 46, 38);
        TextureRegionDrawable icon = new TextureRegionDrawable(iconRegion);

        Map<Identifier, Object> properties = new HashMap<Identifier, Object>();
        properties.put(new Identifier("Animation"), new Identifier("Idle"));
        properties.put(new Identifier("Selected"), false);
        properties.put(new Identifier("Enabled"), false);
        properties.put(new Identifier("Completion"), 0f);
        properties.put(new Identifier("Id"), new Identifier());
        properties.put(new Identifier("Icon"), icon);
        properties.put(new Identifier("Health"), 800.0f);
        properties.put(new Identifier("HealthMaximum"), 800.0f);

        AnimatedItem result = new AnimatedItem(properties, animations);
        result.setType(new Identifier("Barracks"));

        return result;
    }
}
