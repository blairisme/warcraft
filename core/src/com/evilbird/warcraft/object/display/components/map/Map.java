/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.display.components.map;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.Disposable;
import com.evilbird.engine.common.graphics.renderable.BaseRenderable;
import com.evilbird.engine.common.graphics.renderable.Renderable;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectContainer;
import com.evilbird.warcraft.object.layer.forest.ForestCell;
import com.evilbird.warcraft.object.unit.Unit;

import static com.evilbird.warcraft.object.layer.LayerType.Forest;
import static com.evilbird.warcraft.object.layer.LayerType.Map;
import static com.evilbird.warcraft.object.layer.LayerType.Mountain;
import static com.evilbird.warcraft.object.layer.LayerType.Sea;
import static com.evilbird.warcraft.object.layer.LayerType.Shore;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

/**
 * Represents a visual summary of the game world, including terrain and player
 * objects.
 *
 * @author Blair Butterworth
 */
public class Map extends BaseRenderable implements Renderable, Disposable
{
    private MapTerrainLayer terrain;
    private MapTerrainLayer forest;
    private MapUnitLayer units;

    public Map(GameObjectContainer container) {
        terrain = new MapTerrainLayer(container, asList(Map, Shore, Sea, Mountain));
        forest = new MapTerrainLayer(container, singletonList(Forest));
        units = new MapUnitLayer(container);
    }

    @Override
    public void dispose() {
        terrain.dispose();
        forest.dispose();
        units.dispose();
    }

    @Override
    public void draw(Batch batch, float x, float y, float width, float height) {
        terrain.draw(batch, x, y, width, height);
        forest.draw(batch, x, y, width, height);
        units.draw(batch, x, y, width, height);
    }

    public void invalidate(GameObject object) {
        if (object instanceof Unit) {
            units.invalidate(object.getPosition(), object.getSize());
        }
        if (object instanceof ForestCell) {
            forest.invalidate();
        }
    }

    @Override
    public void update(float time) {
        terrain.update(time);
        forest.update(time);
        units.update(time);
    }
}
