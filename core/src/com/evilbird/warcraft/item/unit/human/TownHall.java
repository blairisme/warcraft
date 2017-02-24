package com.evilbird.warcraft.item.unit.human;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.graphics.DirectionalAnimation;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.utility.AssetObjectProvider;
import com.evilbird.engine.utility.Identifier;
import com.evilbird.warcraft.item.unit.AnimatedItem;
import com.evilbird.warcraft.item.unit.AnimationBuilder;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

public class TownHall implements AssetObjectProvider<Item>
{
    private AssetManager assets;

    @Inject
    public TownHall(Device device)
    {
        this.assets = device.getAssetStorage().getAssets();
    }

    @Override
    public void load()
    {
        assets.load("data/textures/human/winter/town_hall.png", Texture.class);
        assets.load("data/textures/neutral/perennial/construction.png", Texture.class);
    }

    @Override
    public Item get()
    {
        Texture texture = assets.get("data/textures/human/winter/town_hall.png", Texture.class);
        Texture constructionTexture = assets.get("data/textures/neutral/perennial/construction.png", Texture.class);
        Map<Identifier, DirectionalAnimation> animations = AnimationBuilder.getBuildingAnimationSet(texture, constructionTexture, 128);
        animations.put(new Identifier("DepositGold"), animations.get(new Identifier("Idle")));
        animations.put(new Identifier("DepositWood"), animations.get(new Identifier("Idle")));

        Map<Identifier, Object> properties = new HashMap<Identifier, Object>();
        properties.put(new Identifier("Type"), new Identifier("TownHall"));
        properties.put(new Identifier("Animation"), new Identifier("Idle"));
        properties.put(new Identifier("Selected"), false);
        properties.put(new Identifier("Gold"), 0f);
        properties.put(new Identifier("Wood"), 0f);
        properties.put(new Identifier("Id"), new Identifier());

        return new AnimatedItem(properties, animations);
    }
}
