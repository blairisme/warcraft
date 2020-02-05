/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ainew.common.select;

import com.badlogic.gdx.ai.btree.LeafTask;
import com.badlogic.gdx.ai.btree.Task;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectComposite;

import javax.inject.Inject;
import java.util.Collection;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Predicate;

import static com.badlogic.gdx.ai.btree.Task.Status.FAILED;
import static com.badlogic.gdx.ai.btree.Task.Status.SUCCEEDED;
import static com.evilbird.engine.common.collection.Lists.toList;
import static com.evilbird.engine.common.function.Consumers.discard;
import static com.evilbird.engine.common.function.Functions.supply;
import static com.evilbird.engine.common.function.Predicates.accept;

/**
 * A {@link LeafTask} implementation that selects a {@link GameObject} based on
 * a given {@link Predicate condition}.
 *
 * @param <T> type of the blackboard object used by the task.
 *
 * @author Blair Butterworth
 */
public class SelectSubject<T> extends LeafTask<T>
{
    protected transient Function<T, Predicate<GameObject>> conditionSupplier;
    protected transient Function<T, GameObjectComposite> targetSupplier;
    protected transient BiConsumer<T, GameObject> resultConsumer;

    @Inject
    public SelectSubject() {
        this.conditionSupplier = supply(accept());
        this.resultConsumer = discard();
    }

    @Override
    public Status execute() {
        T data = getObject();
        GameObject subject = selectSubject(data);
        resultConsumer.accept(data, subject);
        return subject == null ? FAILED : SUCCEEDED;
    }

    public SelectSubject<T> from(Function<T, GameObjectComposite> targetSupplier) {
        this.targetSupplier = targetSupplier;
        return this;
    }

    public SelectSubject<T> into(BiConsumer<T, GameObject> receiver) {
        this.resultConsumer = receiver;
        return this;
    }

    public SelectSubject<T> when(Predicate<GameObject> condition) {
        this.conditionSupplier = data -> condition;
        return this;
    }

    public SelectSubject<T> when(Function<T, Predicate<GameObject>> conditionSupplier) {
        this.conditionSupplier = conditionSupplier;
        return this;
    }

    protected GameObject selectSubject(T data) {
        GameObjectComposite target = targetSupplier.apply(data);
        Predicate<GameObject> condition = conditionSupplier.apply(data);

        Collection<GameObject> objects = target.findAll(condition);
        List<GameObject> results = toList(objects);

        return !results.isEmpty() ? results.get(0) : null;
    }

    @Override
    protected Task<T> copyTo(Task<T> task) {
        SelectSubject<T> selectSubject = (SelectSubject<T>)task;
        selectSubject.conditionSupplier = this.conditionSupplier;
        selectSubject.resultConsumer = this.resultConsumer;
        return selectSubject;
    }
}

