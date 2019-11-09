/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.conjured.flameshield;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.game.GameContext;
import com.evilbird.engine.game.GameFactory;
import com.evilbird.warcraft.item.unit.UnitType;
import com.evilbird.warcraft.item.unit.conjured.ConjuredAssets;

import javax.inject.Inject;

import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;
import static com.evilbird.warcraft.item.WarcraftItemConstants.tiles;

/**
 * Instances of this class create {@link FlameShield} objects, loading the
 * necessary assets and defining the appropriate attributes.
 *
 * @author Blair Butterworth
 */
public class FlameShieldFactory implements GameFactory<FlameShield>
{
    protected AssetManager manager;
    protected ConjuredAssets assets;
    protected FlameShieldBuilder builder;

    @Inject
    public FlameShieldFactory(Device device) {
        this(device.getAssetStorage());
    }

    public FlameShieldFactory(AssetManager manager) {
        this.manager = manager;
    }

    @Override
    public FlameShield get(Identifier type) {
        FlameShield result = (FlameShield)builder.build();
        result.setAttackSpeed(3);
        result.setAttackRange(tiles(2));
        result.setBasicDamage(20);
        result.setPiercingDamage(5);
        result.setIdentifier(objectIdentifier("FlameShield", result));
        result.setType(UnitType.FlameShield);
        result.setSize(64, 64);
        return result;
    }

    @Override
    public void load(GameContext context) {
        assets = new ConjuredAssets(manager);
        builder = new FlameShieldBuilder(assets);
        assets.load();
    }

    @Override
    public void unload(GameContext context) {
        if (assets != null) {
            assets.unload();
        }
    }
}
