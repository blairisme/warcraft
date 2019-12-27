/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.unit.conjured.blizzard;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.game.GameContext;
import com.evilbird.engine.game.GameFactory;
import com.evilbird.warcraft.object.unit.UnitType;
import com.evilbird.warcraft.object.unit.conjured.ConjuredAssets;

import javax.inject.Inject;

import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;
import static com.evilbird.warcraft.object.common.query.GameObjectUtils.tiles;

/**
 * Instances of this class create {@link Blizzard} objects, loading the
 * necessary assets and defining the appropriate attributes.
 *
 * @author Blair Butterworth
 */
public class BlizzardFactory implements GameFactory<Blizzard>
{
    protected AssetManager manager;
    protected ConjuredAssets assets;
    protected BlizzardBuilder builder;

    @Inject
    public BlizzardFactory(Device device) {
        this(device.getAssetStorage());
    }

    public BlizzardFactory(AssetManager manager) {
        this.manager = manager;
    }

    @Override
    public Blizzard get(Identifier type) {
        Blizzard result = (Blizzard)builder.build();
        result.setAttackSpeed(3);
        result.setAttackRange(tiles(2));
        result.setBasicDamage(20);
        result.setPiercingDamage(5);
        result.setIdentifier(objectIdentifier("Blizzard", result));
        result.setType(UnitType.Blizzard);
        result.setSize(160, 160);
        return result;
    }

    @Override
    public void load(GameContext context) {
        assets = new ConjuredAssets(manager, UnitType.Blizzard);
        builder = new BlizzardBuilder(assets);
        assets.load();
    }

    @Override
    public void unload(GameContext context) {
        if (assets != null) {
            assets.unload();
        }
    }
}
