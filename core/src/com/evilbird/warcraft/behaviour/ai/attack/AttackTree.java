/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ai.attack;

import com.badlogic.gdx.ai.btree.Task;
import com.evilbird.engine.behaviour.framework.tree.CompositeTask;
import com.evilbird.engine.events.Events;
import com.evilbird.engine.object.GameObject;
import com.evilbird.warcraft.action.common.create.CreateEvent;
import com.evilbird.warcraft.action.death.RemoveEvent;
import com.evilbird.warcraft.behaviour.ai.PlayerData;
import com.evilbird.warcraft.object.common.capability.OffensiveObject;
import com.evilbird.warcraft.object.common.query.UnitOperations;
import com.evilbird.warcraft.object.data.player.Player;

import javax.inject.Inject;
import javax.inject.Provider;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

/**
 * A behaviour tree that assigns an attack subtree to every attacker assigned
 * to the current player.
 *
 * @author Blair Butterworth
 */
public class AttackTree extends CompositeTask<PlayerData>
{
    private Events events;
    private Predicate<GameObject> condition;
    private Provider<AttackSubTree> factory;
    private Map<GameObject, Task<PlayerData>> tasks;

    @Inject
    public AttackTree(Events events, Provider<AttackSubTree> factory) {
        this.events = events;
        this.factory = factory;
        this.tasks = new HashMap<>();
        this.condition = UnitOperations::isAttacker;
    }

    @Override
    public void start() {
        populateSubTasks();
        super.start();
    }

    @Override
    public void run() {
        evaluateCreateEvents();
        evaluateRemoveEvents();
        super.run();
    }

    protected void populateSubTasks() {
        PlayerData data = getObject();
        Player player = data.getPlayer();

        for (GameObject subject: player.findAll(condition)) {
            AttackSubTree task = addTask(subject);
            addChild(task);
        }
    }

    protected void evaluateCreateEvents() {
        for (CreateEvent event: events.getEvents(CreateEvent.class)) {
            GameObject subject = event.getSubject();

            if (condition.test(subject)) {
                AttackSubTree task = addTask(subject);
                addChild(task);
            }
        }
    }

    protected void evaluateRemoveEvents() {
        for (RemoveEvent event: events.getEvents(RemoveEvent.class)) {
            GameObject subject = event.getSubject();

            if (condition.test(subject)) {
                Task<PlayerData> task = tasks.remove(subject);
                removeChild(task);
            }
        }
    }

    protected AttackSubTree addTask(GameObject subject) {
        AttackSubTree task = factory.get();
        task.setSubject((OffensiveObject)subject);
        tasks.put(subject, task);
        return task;
    }
}
