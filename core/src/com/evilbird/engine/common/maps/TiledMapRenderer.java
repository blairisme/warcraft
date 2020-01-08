/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
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
