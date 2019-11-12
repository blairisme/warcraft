/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.badge;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.game.GameContext;
import com.evilbird.engine.game.GameFactory;
import org.apache.commons.lang3.Validate;

import javax.inject.Inject;

import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;

/**
 * A factory for the creation of badges, loading the necessary assets and
 * creating new game objects.
 *
 * @author Blair Butterworth
 */
public class BadgeFactory implements GameFactory<Badge>
{
    protected AssetManager manager;
    protected BadgeAssets assets;

    @Inject
    public BadgeFactory(Device device) {
        this(device.getAssetStorage());
    }

    public BadgeFactory(AssetManager manager) {
        this.manager = manager;
    }

    @Override
    public void load(GameContext context) {
        assets = new BadgeAssets(manager);
        assets.load();
    }

    @Override
    public void unload(GameContext context) {
        if (assets != null) {
            assets.unload();
        }
    }

    @Override
    public Badge get(Identifier type) {
        Validate.isInstanceOf(BadgeType.class, type);
        return get((BadgeType)type);
    }

    private Badge get(BadgeType type) {
        Badge result = new Badge();
        result.setIcon(assets.getIcon(type));
        result.setIdentifier(objectIdentifier(type.name(), result));
        result.setSize(72, 72);
        result.setTouchable(Touchable.disabled);
        result.setType(type);
        result.setVisible(true);
        return result;
    }
}
