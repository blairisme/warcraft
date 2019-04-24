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
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.evilbird.engine.common.graphics.DirectionalAnimation;
import com.evilbird.engine.common.inject.AssetProvider;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.specialized.AnimatedItem;
import com.evilbird.warcraft.item.common.animation.AnimationSets;
import com.evilbird.warcraft.item.effect.EffectType;
import com.evilbird.warcraft.item.unit.UnitAnimation;

import javax.inject.Inject;
import java.util.Map;

/**
 * Instances of this class represent a confirmation animation item, shown to
 * provide visual feedback to the user when they
 *
 * @author Blair Butterworth
 */
public class ConfirmProvider implements AssetProvider<Item>
{
    private static final String TEXTURE = "data/textures/neutral/hud/green_cross.png";
    private AssetManager assets;

    @Inject
    public ConfirmProvider(Device device) {
        assets = device.getAssetStorage();
    }

    @Override
    public void load() {
        assets.load(TEXTURE, Texture.class);
    }

    @Override
    public Item get() {
        AnimatedItem result = new AnimatedItem();
        result.setAvailableAnimations(getAnimations());
        result.setAnimation(UnitAnimation.Idle);
        result.setTouchable(Touchable.disabled);
        result.setType(EffectType.Confirm);
        result.setSize(32, 32);
        return result;
    }

    private Map<Identifier, DirectionalAnimation> getAnimations() {
        Texture texture = assets.get(TEXTURE, Texture.class);
        return AnimationSets.effectAnimations(texture);
    }
}
