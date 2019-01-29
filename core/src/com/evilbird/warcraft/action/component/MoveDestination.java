/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.component;

import com.evilbird.engine.item.ItemGraph;
import com.evilbird.engine.item.ItemNode;

/**
 * Implementors of this interface provide methods that specify the end point of
 * a {@link MoveAction move action}.
 *
 * @author Blair Butterworth
 */
interface MoveDestination
{
    ItemNode getDestinationNode(ItemGraph graph, ItemNode node);

    boolean isDestinationValid(ItemGraph graph, ItemNode node);

    boolean isDestinationReached(ItemGraph graph, ItemNode node);
}
