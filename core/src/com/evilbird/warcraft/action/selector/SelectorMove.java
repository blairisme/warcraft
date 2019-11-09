/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.selector;

import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.engine.item.spatial.ItemGraph;

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
        Item item = getItem();
        ItemRoot root = item.getRoot();
        ItemGraph graph = root.getSpatialGraph();

        Vector2 graphSize = graph.getGraphSize();
        Vector2 nodeSize = graph.getNodeSize();
        Vector2 itemSize = item.getSize();
        Vector2 itemSizeCenter = multiply(itemSize, 0.5f);
        Vector2 mapSize = subtract(graphSize, itemSize);

        Vector2 position = root.unproject(getCause().getPosition());
        position = subtract(position, itemSizeCenter);
        position = round(position, nodeSize);
        position = clamp(position, Zero, mapSize);

        item.setPosition(position);
        return true;
    }
}
