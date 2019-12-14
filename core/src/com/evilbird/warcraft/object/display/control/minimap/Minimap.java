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
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;
import com.evilbird.engine.common.graphics.renderable.BaseRenderable;
import com.evilbird.engine.common.graphics.renderable.Renderable;
import com.evilbird.engine.object.GameObjectContainer;

/**
 * Represents a visual summary of the game world, including terrain and player
 * objects.
 *
 * @author Blair Butterworth
 */
public class Minimap extends BaseRenderable implements Renderable, Disposable
{
    private MinimapTerrain terrain;
    private MinimapUnits units;

    public Minimap(GameObjectContainer container) {
        terrain = new MinimapTerrain(container);
        units = new MinimapUnits(container);
    }

    @Override
    public void dispose() {
        terrain.dispose();
        units.dispose();
    }

    @Override
    public void draw(Batch batch, float x, float y, float width, float height) {
        terrain.draw(batch, x, y, width, height);
        units.draw(batch, x, y, width, height);
    }

    public void invalidate(Vector2 position, Vector2 size) {
        units.invalidate(position, size);
    }

    @Override
    public void update(float time) {
        terrain.update(time);
        units.update(time);
    }
}
