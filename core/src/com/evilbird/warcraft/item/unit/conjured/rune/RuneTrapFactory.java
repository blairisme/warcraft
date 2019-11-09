/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.conjured.rune;

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
 * Instances of this class create {@link RuneTrap} objects, loading the
 * necessary assets and defining the appropriate attributes.
 *
 * @author Blair Butterworth
 */
public class RuneTrapFactory implements GameFactory<RuneTrap>
{
    protected AssetManager manager;
    protected ConjuredAssets assets;
    protected RuneTrapBuilder builder;

    @Inject
    public RuneTrapFactory(Device device) {
        this(device.getAssetStorage());
    }

    public RuneTrapFactory(AssetManager manager) {
        this.manager = manager;
    }

    @Override
    public RuneTrap get(Identifier type) {
        RuneTrap result = (RuneTrap)builder.build();
        result.setAttackSpeed(3);
        result.setAttackRange(tiles(2));
        result.setBasicDamage(20);
        result.setPiercingDamage(5);
        result.setIdentifier(objectIdentifier("RuneTrap", result));
        result.setType(UnitType.RuneTrap);
        result.setSize(96, 96);
        return result;
    }

    @Override
    public void load(GameContext context) {
        assets = new ConjuredAssets(manager);
        builder = new RuneTrapBuilder(assets);
        assets.load();
    }

    @Override
    public void unload(GameContext context) {
        if (assets != null) {
            assets.unload();
        }
    }
}