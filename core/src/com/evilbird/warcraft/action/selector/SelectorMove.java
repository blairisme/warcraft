/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.selector;

import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectContainer;
import com.evilbird.engine.object.spatial.GameObjectGraph;
import com.evilbird.warcraft.object.selector.SelectorType;

import javax.inject.Inject;

import static com.badlogic.gdx.math.Vector2.Zero;
import static com.evilbird.engine.common.math.VectorUtils.clamp;
import static com.evilbird.engine.common.math.VectorUtils.multiply;
import static com.evilbird.engine.common.math.VectorUtils.round;
import static com.evilbird.engine.common.math.VectorUtils.subtract;

/**
 * Instances of this class reposition a given selector to the location of
 * the given input.
 *
 * @author Blair Butterworth
 */
public class SelectorMove extends BasicAction
{
    @Inject
    public SelectorMove() {
        setIdentifier(SelectorActions.MoveSelector);
    }

    @Override
    public boolean act(float delta) {
        GameObject item = getSubject();
        GameObjectContainer container = item.getRoot();
        GameObjectGraph graph = container.getSpatialGraph();

        Vector2 itemSize = item.getSize();
        Vector2 itemSizeCenter = multiply(itemSize, 0.5f);

        Vector2 graphSize = graph.getGraphSize();
        Vector2 mapSize = subtract(graphSize, itemSize);

        Vector2 position = container.unproject(getCause().getPosition());
        position = subtract(position, itemSizeCenter);
        position = clamp(position, Zero, mapSize);

        SelectorType selector = (SelectorType)item.getType();
        if (selector.isBuildingSelector()) {
            Vector2 nodeSize = graph.getNodeSize();
            position = round(position, nodeSize);
        }
        item.setPosition(position);
        return true;
    }
}
