/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.unit.combatant.gatherer;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.warcraft.object.unit.UnitType;
import com.evilbird.warcraft.object.unit.combatant.CombatantFactory;

/**
 * A reusable class for creating {@link Gatherer}s, loading the
 * necessary assets and defining the appropriate attributes.
 *
 * @author Blair Butterworth
 */
public abstract class GathererFactory extends CombatantFactory<GathererAssets, GathererBuilder, Gatherer>
{
    public GathererFactory(AssetManager manager, UnitType type) {
        this(manager, type, type);
    }

    public GathererFactory(AssetManager manager, UnitType assetType, UnitType buildType) {
        super(manager, assetType, buildType);
    }

    @Override
    protected GathererAssets newAssets(AssetManager manager, UnitType type) {
        return new GathererAssets(manager, type);
    }

    @Override
    protected GathererBuilder newBuilder(GathererAssets assets, UnitType type) {
        return new GathererBuilder(assets, type);
    }
}
