/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.unit.combatant;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.game.GameContext;
import com.evilbird.engine.game.GameFactory;
import com.evilbird.warcraft.object.unit.UnitType;

/**
 * A reusable class for creating {@link Combatant Combatants}, loading the
 * necessary assets and defining the appropriate attributes.
 *
 * @author Blair Butterworth
 */
public abstract class CombatantFactory<A extends CombatantAssets, B extends CombatantBuilder<T>, T extends Combatant>
    implements GameFactory<T>
{
    protected A assets;
    protected B builder;
    protected AssetManager manager;
    protected UnitType assetType;
    protected UnitType buildType;

    /**
     * Creates a new instance of this class given an {@link AssetManager}, used
     * to obtain assets for the the objects created by the factory, and a pair
     * of {@link UnitType UnitTypes}, representing the type of assets and
     * resulting type of objects created by the factory.
     */
    public CombatantFactory(AssetManager manager, UnitType assetType, UnitType buildType) {
        this.manager = manager;
        this.assetType = assetType;
        this.buildType = buildType;
    }

    @Override
    public void load(GameContext context) {
        assets = newAssets(manager, assetType);
        builder = newBuilder(assets, buildType);
        assets.load();
    }

    @Override
    public void unload(GameContext context) {
        if (assets != null) {
            assets.unload();
        }
    }

    protected abstract A newAssets(AssetManager manager, UnitType type);

    protected abstract B newBuilder(A assets, UnitType type);
}