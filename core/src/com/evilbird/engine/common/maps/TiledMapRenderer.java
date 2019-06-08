/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.maps;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

/**
 * Renders a given tiled map. Extends OrthogonalTiledMapRenderer, allowing a
 * single instance to be reused by supplying the current {@link Batch} before
 * rendering.
 *
 * @author Blair Butterworth
 */
public class TiledMapRenderer extends OrthogonalTiledMapRenderer
{
    public TiledMapRenderer() {
        super(null, null);
    }

    public void setBatch(Batch batch) {
        this.batch = batch;
    }
}
