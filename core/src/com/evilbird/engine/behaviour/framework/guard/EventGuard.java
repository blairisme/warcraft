/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.behaviour.framework.guard;

import com.badlogic.gdx.ai.btree.LeafTask;
import com.badlogic.gdx.ai.btree.Task;
import com.evilbird.engine.common.function.Predicates;
import com.evilbird.engine.events.Event;
import com.evilbird.engine.events.Events;
import com.evilbird.engine.object.GameObject;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Predicate;

/**
 * A guard task that ensures that succeeds when an event is added matching a
 * given event type.
 *
 * @param <T> type of the blackboard object used by the task.
 *
 * @author Blair Butterworth
 */
public class EventGuard<T> extends LeafTask<T>
{
    private Events events;
    private Predicate<GameObject> condition;
    private Collection<Class<? extends Event>> triggers;

    @Inject
    public EventGuard(Events events) {
        this.events = events;
        this.triggers = new ArrayList<>();
        this.condition = Predicates.accept();
    }

    @Override
    public Status execute() {
        for (Class<? extends Event> eventType: triggers) {
            for (Event event: events.getEvents(eventType)) {
                if (condition.test(event.getSubject())) {
                    return Status.SUCCEEDED;
                }
            }
        }
        return Status.FAILED;
    }

    public EventGuard<T> trigger(Class<? extends Event> trigger) {
        this.triggers.add(trigger);
        return this;
    }

    public EventGuard<T> subject(Predicate<GameObject> condition) {
        this.condition = condition;
        return this;
    }

    @Override
    protected Task<T> copyTo(Task<T> task) {
        EventGuard<T> eventGuard = (EventGuard<T>)task;
        eventGuard.events = this.events;
        eventGuard.triggers = this.triggers;
        eventGuard.condition = this.condition;
        return eventGuard;
    }
}
