/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.layer;

import com.evilbird.engine.common.error.UnknownEntityException;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.game.GameContext;
import com.evilbird.engine.game.GameFactory;
import com.evilbird.warcraft.object.layer.fog.FogFactory;
import com.evilbird.warcraft.object.layer.forest.ForestFactory;
import com.evilbird.warcraft.object.layer.terrain.TerrainFactory;
import com.evilbird.warcraft.object.layer.wall.WallFactory;

import javax.inject.Inject;

/**
 * Instances of this factory are used to construct {@link Layer} items.
 *
 * @author Blair Butterworth
 */
public class LayerFactory implements GameFactory<Layer>
{
    private FogFactory fogFactory;
    private ForestFactory forestFactory;
    private TerrainFactory terrainFactory;
    private WallFactory wallFactory;

    @Inject
    public LayerFactory(
        FogFactory fogFactory,
        ForestFactory forestFactory,
        TerrainFactory terrainFactory,
        WallFactory wallFactory)
    {
        this.fogFactory = fogFactory;
        this.forestFactory = forestFactory;
        this.terrainFactory = terrainFactory;
        this.wallFactory = wallFactory;
    }

    @Override
    public Layer get(Identifier identifier) {
        LayerIdentifier layerIdentifier = (LayerIdentifier)identifier;
        switch (layerIdentifier.getType()) {
            case Map:
            case Mountain:
            case Sea:
            case Shore: return terrainFactory.get(identifier);
            case Forest: return forestFactory.get(identifier);
            case OpaqueFog:
            case TransparentFog: return fogFactory.get(identifier);
            case Wall: return wallFactory.get(identifier);
            default: throw new UnknownEntityException(identifier);
        }
    }

    @Override
    public void load(GameContext context) {
        fogFactory.load(context);
        forestFactory.load(context);
        terrainFactory.load(context);
        wallFactory.load(context);
    }

    @Override
    public void unload(GameContext context) {
        fogFactory.unload(context);
        forestFactory.unload(context);
        terrainFactory.unload(context);
        wallFactory.unload(context);
    }
}
