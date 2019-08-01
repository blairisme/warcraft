/*
 * Blair Butterworth (c) 2018
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.layer;

import com.evilbird.engine.common.error.UnknownEntityException;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.game.GameContext;
import com.evilbird.engine.game.GameFactory;
import com.evilbird.warcraft.item.layer.fog.FogFactory;
import com.evilbird.warcraft.item.layer.forest.ForestFactory;
import com.evilbird.warcraft.item.layer.terrain.TerrainFactory;
import com.evilbird.warcraft.item.layer.wall.WallFactory;

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
