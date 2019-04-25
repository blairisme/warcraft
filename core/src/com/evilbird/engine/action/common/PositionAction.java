/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.action.common;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.engine.item.spatial.ItemGraph;
import com.evilbird.engine.item.spatial.ItemNode;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Predicate;

import static com.evilbird.engine.action.common.ActionUtils.getRecipient;

/**
 * Instances of this {@link Action} position the subject of the action in the
 * first unoccupied position adjacent to the actions target.
 *
 * @author Blair Butterworth
 */
public class PositionAction extends BasicAction
{
    private ActionRecipient from;
    private ActionRecipient to;
    private Predicate<ItemNode> unoccupied;

    public PositionAction(ActionRecipient from, ActionRecipient to, Predicate<ItemNode> unoccupied) {
        this.from = from;
        this.to = to;
        this.unoccupied = unoccupied;
    }

    public static PositionAction positionAdjacent(ActionRecipient from, ActionRecipient to, Predicate<ItemNode> unoccupied) {
        return new PositionAction(from, to, unoccupied);
    }

    @Override
    public boolean act(float delta) {
        Item item = getRecipient(this, from);
        Item target = getRecipient(this, to);

        ItemRoot root = target.getRoot();
        ItemGraph graph = root.getSpatialGraph();

        Collection<ItemNode> adjacentNodes = graph.getAdjacentNodes(target.getPosition(), target.getSize());
        Optional<ItemNode> unoccupiedNode = adjacentNodes.stream().filter(unoccupied).findFirst();

        if (unoccupiedNode.isPresent()) {
            ItemNode destination = unoccupiedNode.get();
            item.setPosition(destination.getWorldReference());
        }
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null) return false;
        if (obj.getClass() != getClass()) return false;

        PositionAction that = (PositionAction)obj;
        return new EqualsBuilder()
            .appendSuper(super.equals(obj))
            .append(from, that.from)
            .append(to, that.to)
            .append(unoccupied, that.unoccupied)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .appendSuper(super.hashCode())
            .append(from)
            .append(to)
            .append(unoccupied)
            .toHashCode();
    }
}
