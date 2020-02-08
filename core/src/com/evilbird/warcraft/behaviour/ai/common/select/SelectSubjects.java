/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ai.common.select;

import com.badlogic.gdx.ai.btree.LeafTask;
import com.badlogic.gdx.ai.btree.Task;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectComposite;

import javax.inject.Inject;
import java.util.Collection;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Predicate;

import static com.badlogic.gdx.ai.btree.Task.Status.FAILED;
import static com.badlogic.gdx.ai.btree.Task.Status.SUCCEEDED;
import static com.evilbird.engine.common.function.Consumers.discard;
import static com.evilbird.engine.common.function.Functions.supply;
import static com.evilbird.engine.common.function.Predicates.accept;

/**
 * A {@link LeafTask} implementation that selects a collection of
 * {@link GameObject GameObjects} based on a given {@link Predicate condition}.
 *
 * @param <T> type of the blackboard object used by the task.
 *           
 * @author Blair Butterworth
 */
public class SelectSubjects<T> extends LeafTask<T>
{
    protected transient Function<T, Predicate<GameObject>> conditionSupplier;
    protected transient Function<T, GameObjectComposite> targetSupplier;
    protected transient BiConsumer<T, Collection<GameObject>> resultConsumer;

    @Inject
    public SelectSubjects() {
        this.conditionSupplier = supply(accept());
        this.resultConsumer = discard();
    }

    @Override
    public Status execute() {
        T data = getObject();
        Collection<GameObject> subjects = selectSubjects(data);
        resultConsumer.accept(data, subjects);
        return subjects.isEmpty() ? FAILED : SUCCEEDED;
    }

    public SelectSubjects<T> when(Predicate<GameObject> condition) {
        this.conditionSupplier = data -> condition;
        return this;
    }

    public SelectSubjects<T> when(Function<T, Predicate<GameObject>> conditionSupplier) {
        this.conditionSupplier = conditionSupplier;
        return this;
    }

    public SelectSubjects<T> guard(Task<T> guard) {
        super.setGuard(guard);
        return this;
    }

    public SelectSubjects<T> into(BiConsumer<T, Collection<GameObject>> receiver) {
        this.resultConsumer = receiver;
        return this;
    }

    public SelectSubjects<T> from(Function<T, GameObjectComposite> targetSupplier) {
        this.targetSupplier = targetSupplier;
        return this;
    }

    protected Collection<GameObject> selectSubjects(T data) {
        GameObjectComposite container = targetSupplier.apply(data);
        Predicate<GameObject> condition = conditionSupplier.apply(data);
        return container.findAll(condition);
    }

    @Override
    protected Task<T> copyTo(Task<T> task) {
        SelectSubjects<T> selectSubjects = (SelectSubjects<T>)task;
        selectSubjects.conditionSupplier = this.conditionSupplier;
        selectSubjects.resultConsumer = this.resultConsumer;
        selectSubjects.targetSupplier = this.targetSupplier;
        return selectSubjects;
    }
}

