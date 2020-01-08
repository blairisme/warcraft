/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.scenario.condition;

import com.evilbird.engine.events.EventQueue;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectContainer;
import com.evilbird.engine.object.GameObjectType;
import com.evilbird.warcraft.action.move.MoveEvent;

import java.util.Collection;
import java.util.function.Predicate;

import static com.evilbird.engine.object.utility.GameObjectOperations.overlaps;
import static com.evilbird.engine.object.utility.GameObjectPredicates.withIdStartingWith;
import static com.evilbird.engine.object.utility.GameObjectPredicates.withType;
import static com.evilbird.warcraft.object.data.player.PlayerIds.Player1;

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
    private Predicate<GameObject> subjectCondition;
    private Predicate<GameObject> destinationCondition;

    private Collection<GameObject> subjectCache;
    private Collection<GameObject> destinationCache;

    public UnitPositioned(Predicate<GameObject> subjectCondition, Predicate<GameObject> destinationCondition) {
        super(Player1);
        this.subjectCondition = subjectCondition;
        this.destinationCondition = destinationCondition;
    }

    public static UnitPositioned unitRepositionedTo(String subjectId, GameObjectType destinationType) {
        return new UnitPositioned(withIdStartingWith(subjectId), withType(destinationType));
    }

    public static UnitPositioned unitRepositionedTo(GameObjectType subjectType, GameObjectType destinationType) {
        return new UnitPositioned(withType(subjectType), withType(destinationType));
    }

    public static UnitPositioned unitRepositionedTo(String subjectId, GameObjectType destinationType, int count) {
        return new UnitPositioned(withIdStartingWith(subjectId), withType(destinationType));
    }

    @Override
    protected boolean applicable(EventQueue queue) {
        return queue.hasEvents(MoveEvent.class);
    }

    @Override
    protected boolean evaluate(GameObjectContainer state) {
        for (GameObject destination: getDestinations(state)) {
            for (GameObject subject: getSubjects(state)) {
                if (overlaps(destination, subject)) {
                    return true;
                }
            }
        }
        return false;
    }

    private Collection<GameObject> getDestinations(GameObjectContainer state) {
        if (destinationCache == null) {
            destinationCache = state.findAll(destinationCondition);
        }
        return destinationCache;
    }

    private Collection<GameObject> getSubjects(GameObjectContainer state) {
        if (subjectCache == null) {
            subjectCache = state.findAll(subjectCondition);
        }
        return subjectCache;
    }
}
