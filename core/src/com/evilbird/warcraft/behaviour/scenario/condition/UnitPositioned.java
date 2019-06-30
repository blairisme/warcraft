/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.behaviour.scenario.condition;

import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.events.EventQueue;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.engine.item.ItemType;
import com.evilbird.warcraft.action.move.MoveEvent;

import java.util.Collection;

import static com.evilbird.engine.item.utility.ItemOperations.overlaps;
import static com.evilbird.engine.item.utility.ItemPredicates.withType;
import static com.evilbird.warcraft.item.data.player.PlayerIds.Player1;

/**
 * Represents a {@link ScenarioCondition} that is fulfilled when an item of a
 * given type is moved on top of another. This is commonly used in scenarios
 * when the user is required to rescue a special character, returning it to a
 * known location.
 *
 * @author Blair Butterworth
 */
public class UnitPositioned extends PlayerCondition
{
    private Identifier subjectType;
    private Identifier destinationType;
    private Collection<Item> subjectCache;
    private Collection<Item> destinationCache;

    public UnitPositioned(Identifier subjectType, Identifier destinationType, int count) {
        super(Player1);
        this.subjectType = subjectType;
        this.destinationType = destinationType;
    }

    public static UnitPositioned unitRepositionedTo(ItemType subject, ItemType destination) {
        return unitRepositionedTo(subject, destination);
    }

    public static UnitPositioned unitRepositionedTo(ItemType subject, ItemType destination, int count) {
        return new UnitPositioned(subject, destination, count);
    }

    @Override
    protected boolean applicable(EventQueue queue) {
        return queue.hasEvents(MoveEvent.class);
    }

    @Override
    protected boolean evaluate(ItemRoot state) {
        for (Item destination: getDestinations(state)) {
            for (Item subject: getSubjects(state)) {
                if (overlaps(destination, subject)) {
                    return true;
                }
            }
        }
        return false;
    }

    private Collection<Item> getDestinations(ItemRoot state) {
        if (destinationCache == null) {
            destinationCache = state.findAll(withType(destinationType));
        }
        return destinationCache;
    }

    private Collection<Item> getSubjects(ItemRoot state) {
        if (subjectCache == null) {
            subjectCache = state.findAll(withType(subjectType));
        }
        return subjectCache;
    }
}
