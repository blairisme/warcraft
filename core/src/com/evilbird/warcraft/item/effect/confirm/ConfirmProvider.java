package com.evilbird.warcraft.item.effect.confirm;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.evilbird.engine.common.graphics.DirectionalAnimation;
import com.evilbird.engine.common.inject.AssetProvider;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.specialized.Animation;
import com.evilbird.engine.item.specialized.AnimationIdentifier;
import com.evilbird.warcraft.common.AnimationBuilder;
import com.evilbird.warcraft.common.AnimationCollections;
import com.evilbird.warcraft.item.effect.EffectType;
import com.evilbird.warcraft.item.unit.UnitAnimation;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import static com.evilbird.warcraft.common.AnimationSchemas.effectSchema;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class ConfirmProvider implements AssetProvider<Item>
{
    private AssetManager assets;

    @Inject
    public ConfirmProvider(Device device)
    {
        assets = device.getAssetStorage().getAssets();
    }

    @Override
    public void load()
    {
        assets.load("data/textures/neutral/hud/green_cross.png", Texture.class);
    }

    @Override
    public Item get()
    {
        Animation result = new Animation();
        result.setAvailableAnimations(getAnimations());
        result.setAnimation(UnitAnimation.Idle);
        result.setType(EffectType.Confirm);
        result.setSize(32, 32);
        return result;
    }

    //TODO: Cache
    private Map<AnimationIdentifier, DirectionalAnimation> getAnimations()
    {
        Texture texture = assets.get("data/textures/neutral/hud/green_cross.png", Texture.class);
        return AnimationCollections.effectAnimations(texture);
    }
}
