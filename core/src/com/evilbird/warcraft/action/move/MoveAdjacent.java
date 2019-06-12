/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.move;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.common.ActionRecipient;
import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.engine.item.spatial.ItemGraph;
import com.evilbird.engine.item.spatial.ItemNode;
import com.evilbird.warcraft.action.common.path.ItemPathFilter;
import com.evilbird.warcraft.item.common.movement.Movable;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Collection;
import java.util.Optional;

import static com.evilbird.engine.action.common.ActionRecipient.Subject;
import static com.evilbird.engine.action.common.ActionRecipient.Target;
import static com.evilbird.engine.action.common.ActionUtils.getRecipient;

/**
 * Instances of this {@link Action} position the subject of the action in the
 * first unoccupied position adjacent to the actions whileTarget.
 *
 * @author Blair Butterworth
 */
public class MoveAdjacent extends BasicAction
{
    private ActionRecipient from;
    private ActionRecipient to;

    public MoveAdjacent(ActionRecipient from, ActionRecipient to) {
        this.from = from;
        this.to = to;
    }

    public static MoveAdjacent moveAdjacent(ActionRecipient from, ActionRecipient to) {
        return new MoveAdjacent(from, to);
    }

    public static MoveAdjacent moveAdjacentSubject() {
        return moveAdjacent(Target, Subject);
    }

    public static MoveAdjacent moveAdjacentTarget() {
        return moveAdjacent(Subject, Target);
    }

    @Override
    public boolean act(float delta) {
        Movable subject = (Movable)getRecipient(this, from);
        Item target = getRecipient(this, to);

        ItemRoot root = target.getRoot();
        ItemGraph graph = root.getSpatialGraph();

        ItemPathFilter capability = new ItemPathFilter();
        capability.addTraversableCapability(subject.getMovementCapability());

        Collection<ItemNode> adjacent = graph.getAdjacentNodes(target.getPosition(), target.getSize());
        Optional<ItemNode> unoccupied = adjacent.stream().filter(capability).findFirst();

        if (unoccupied.isPresent()) {
            graph.removeOccupants(subject);
            ItemNode destination = unoccupied.get();
            subject.setPosition(destination.getWorldReference());
            graph.addOccupants(subject);
        }
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (obj.getClass() != getClass()) { return false; }

        MoveAdjacent that = (MoveAdjacent)obj;
        return new EqualsBuilder()
            .appendSuper(super.equals(obj))
            .append(from, that.from)
            .append(to, that.to)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .appendSuper(super.hashCode())
            .append(from)
            .append(to)
            .toHashCode();
    }
}
