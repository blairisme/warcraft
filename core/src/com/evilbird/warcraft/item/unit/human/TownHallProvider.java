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
import com.evilbird.warcraft.action.ActionType;
import com.evilbird.warcraft.item.unit.common.AnimatedItem;
import com.evilbird.warcraft.item.unit.common.AnimationBuilder;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

public class TownHallProvider implements AssetObjectProvider<Item>
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
    /*
    Health: 1200
        Production:
            Gold: 100
            Lumber: 100
            Oil: 100
         */

        Texture texture = assets.get("data/textures/human/winter/town_hall.png", Texture.class);
        Texture constructionTexture = assets.get("data/textures/neutral/perennial/construction.png", Texture.class);
        Map<Identifier, DirectionalAnimation> animations = AnimationBuilder.getBuildingAnimationSet(texture, constructionTexture, 128);
        animations.put(new Identifier("DepositGold"), animations.get(new Identifier("Idle")));
        animations.put(new Identifier("DepositWood"), animations.get(new Identifier("Idle")));

        Texture iconTexture = assets.get("data/textures/neutral/perennial/icons.png", Texture.class);
        TextureRegion iconRegion = new TextureRegion(iconTexture, 0, 304, 46, 38);
        TextureRegionDrawable icon = new TextureRegionDrawable(iconRegion);

        EnumSet<ActionType> actions = EnumSet.noneOf(ActionType.class);
        //actions.add(Actions.BuildPeasant);

        Map<Identifier, Object> properties = new HashMap<Identifier, Object>();
        properties.put(new Identifier("Animation"), new Identifier("Idle"));
        properties.put(new Identifier("Selected"), false);
        properties.put(new Identifier("Gold"), 0f);
        properties.put(new Identifier("Wood"), 0f);
        properties.put(new Identifier("Id"), new Identifier());
        properties.put(new Identifier("Icon"), icon);
        properties.put(new Identifier("Health"), 1200.0f);
        properties.put(new Identifier("HealthMaximum"), 1200.0f);
        properties.put(new Identifier("Actions"), actions);

        AnimatedItem result = new AnimatedItem(properties, animations);
        result.setType(new Identifier("TownHall"));

        return result;
    }
}
