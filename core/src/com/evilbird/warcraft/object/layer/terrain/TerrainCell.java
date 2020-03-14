/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.layer.terrain;

import com.badlogic.gdx.math.GridPoint2;
import com.evilbird.engine.action.Action;
import com.evilbird.engine.common.time.GameTimer;
import com.evilbird.engine.object.GameObjectContainer;
import com.evilbird.warcraft.object.layer.LayerCell;

/**
 * A {@link LayerCell} that represents a single subdivision of a
 * {@link Terrain} layer. TerrainCells are a transient entity used for
 * reporting and interface standardisation. They do not belong to a hierarchy
 * of terrain objects.
 *
 * @author Blair Butterworth
 */
public class TerrainCell extends LayerCell
{
    private Terrain parent;

    public TerrainCell(Terrain parent, GridPoint2 location) {
        super(parent, location);
        this.parent = parent;
        this.type = parent.getType();
    }

    @Override
    public void addAction(Action action) {
        parent.addAction(action);
    }

    @Override
    public void addAction(Action action, GameTimer delay) {
        parent.addAction(action, delay);
    }

    @Override
    public Terrain getParent() {
        return parent;
    }

    @Override
    public GameObjectContainer getRoot() {
        return parent.getRoot();
    }

    @Override
    public void removeAction(Action action) {
        parent.removeAction(action);
    }

    @Override
    public void removeActions() {
        throw new UnsupportedOperationException();
    }
}
