/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ainew.common;

import com.evilbird.engine.events.Events;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectComposite;
import com.evilbird.warcraft.action.common.create.CreateEvent;
import com.evilbird.warcraft.action.death.RemoveEvent;

import javax.inject.Inject;
import java.util.Collection;
import java.util.function.Predicate;

/**
 * @author Blair Butterworth
 */
public class GameStateView
{
    private Events events;
    private GameObjectComposite container;
    private Predicate<GameObject> condition;
    private Collection<GameObject> cache;

    @Inject
    public GameStateView(Events events) {
        this.events = events;
    }

    public Collection<GameObject> getResult() {
        return cache;
    }

    public void setTarget(GameObjectComposite container) {
        this.container = container;
    }

    public void setCondition(Predicate<GameObject> condition) {
        this.condition = condition;
    }

    public void update() {
        populateCache();
        evaluateCreateEvents();
        evaluateRemoveEvents();
    }

    private void populateCache() {
        if (cache == null) {
            cache = container.findAll(condition);
        }
    }

    private void evaluateCreateEvents() {
        for (CreateEvent event: events.getEvents(CreateEvent.class)) {
            GameObject subject = event.getSubject();
            if (condition.test(subject)) {
                cache.add(subject);
            }
        }
    }

    private void evaluateRemoveEvents() {
        for (RemoveEvent event: events.getEvents(RemoveEvent.class)) {
            GameObject subject = event.getSubject();
            if (condition.test(subject)) {
                cache.remove(subject);
            }
        }
    }
}
