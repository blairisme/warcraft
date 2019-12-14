/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.display.control.minimap;

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
public class Minimap extends BaseRenderable implements Renderable, Disposable
{
    private MinimapTerrainLayer terrain;
    private MinimapTerrainLayer forest;
    private MinimapUnitLayer units;

    public Minimap(GameObjectContainer container) {
        terrain = new MinimapTerrainLayer(container, asList(Map, Shore, Sea, Mountain));
        forest = new MinimapTerrainLayer(container, singletonList(Forest));
        units = new MinimapUnitLayer(container);
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
