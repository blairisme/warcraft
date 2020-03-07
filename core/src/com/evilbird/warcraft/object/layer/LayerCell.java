/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.layer;

import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.evilbird.engine.object.BasicGameObject;

/**
 * Represents a single element in a {@link Layer}.
 *
 * @author Blair Butterworth
 */
public class LayerCell extends BasicGameObject
{
    private static final transient int TILE_WIDTH = 32;
    private static final transient int TILE_HEIGHT = 32;

    protected GridPoint2 location;

    public LayerCell(GridPoint2 location) {
        setTouchable(Touchable.enabled);
        setVisible(true);
        setSize(TILE_WIDTH, TILE_HEIGHT);
        setLocation(location);
    }

    public GridPoint2 getLocation() {
        return location;
    }

    public void setLocation(GridPoint2 location) {
        this.location = location;
        setPosition(location.x * TILE_WIDTH, location.y * TILE_HEIGHT);
    }
}
