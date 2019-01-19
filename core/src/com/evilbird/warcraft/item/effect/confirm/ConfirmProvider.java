/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.effect.confirm;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.evilbird.engine.common.graphics.DirectionalAnimation;
import com.evilbird.engine.common.inject.AssetProvider;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.specialized.animated.AnimatedItem;
import com.evilbird.engine.item.specialized.animated.AnimationIdentifier;
import com.evilbird.warcraft.item.common.animation.AnimationCollections;
import com.evilbird.warcraft.item.effect.EffectType;
import com.evilbird.warcraft.item.unit.UnitAnimation;

import java.util.Map;

import javax.inject.Inject;

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
        AnimatedItem result = new AnimatedItem();
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
