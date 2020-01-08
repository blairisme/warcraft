/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
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
