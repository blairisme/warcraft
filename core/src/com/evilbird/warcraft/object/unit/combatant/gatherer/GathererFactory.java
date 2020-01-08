/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
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
