/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.ui.confirmation;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.common.graphics.Animation;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.game.GameContext;
import com.evilbird.engine.game.GameFactory;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.specialized.AnimatedItem;
import com.evilbird.engine.item.specialized.AnimatedItemStyle;
import com.evilbird.warcraft.item.common.animation.AnimationLayouts;
import com.evilbird.warcraft.item.common.animation.AnimationSetBuilder;
import com.evilbird.warcraft.item.unit.UnitAnimation;

import javax.inject.Inject;
import java.util.Collections;
import java.util.Map;

import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;

/**
 * Instances of this class represent a confirmation animation item, shown to
 * provide visual feedback to the user when they perform an action involving a
 * destination.
 *
 * @author Blair Butterworth
 */
public class ConfirmFactory implements GameFactory<Item>
{
    private AssetManager manager;
    private ConfirmAssets assets;

    @Inject
    public ConfirmFactory(Device device) {
        this(device.getAssetStorage());
    }

    public ConfirmFactory(AssetManager manager) {
        this.manager = manager;
    }

    @Override
    public void load(GameContext context) {
        assets = new ConfirmAssets(manager);
        assets.load();
    }

    @Override
    public void unload(GameContext context) {
        if (assets != null) {
            assets.unload();
        }
    }

    @Override
    public Item get(Identifier type) {
        AnimatedItem result = new AnimatedItem(getSkin());
        result.setAnimation(UnitAnimation.Idle);
        result.setTouchable(Touchable.disabled);
        result.setType(ConfirmType.Confirm);
        result.setIdentifier(objectIdentifier("Confirm", result));
        result.setSize(32, 32);
        return result;
    }

    private Skin getSkin() {
        Skin skin = new Skin();
        skin.add("default", getAnimationStyle(), AnimatedItemStyle.class);
        return skin;
    }

    private AnimatedItemStyle getAnimationStyle() {
        AnimatedItemStyle animatedItemStyle = new AnimatedItemStyle();
        animatedItemStyle.animations = getAnimations();
        animatedItemStyle.sounds = Collections.emptyMap();
        return animatedItemStyle;
    }

    private Map<Identifier, Animation> getAnimations() {
        AnimationSetBuilder builder = new AnimationSetBuilder();
        builder.set(UnitAnimation.Idle, AnimationLayouts.effectSchema(), assets. getGreenCross());
        return builder.build();
    }
}
