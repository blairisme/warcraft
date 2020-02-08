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
import java.util.Comparator;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Predicate;

import static com.evilbird.engine.common.collection.Lists.toList;
import static com.evilbird.engine.common.function.Comparators.equality;
import static com.evilbird.engine.common.function.Functions.supply;

/**
 * A {@link LeafTask} implementation that selects a {@link GameObject} based on
 * a given {@link Predicate condition} and sorted using a {@link Comparator}.
 *
 * @param <T> type of the blackboard object used by the task.
 *
 * @author Blair Butterworth
 */
public class SelectFirstSubject<T> extends SelectSubject<T>
{
    protected transient Function<T, Comparator<GameObject>> comparatorSupplier;

    @Inject
    public SelectFirstSubject() {
        this.comparatorSupplier = supply(equality());
    }

    public SelectFirstSubject<T> sort(Comparator<GameObject> comparator) {
        this.comparatorSupplier = data -> comparator;
        return this;
    }

    public SelectFirstSubject<T> sort(Function<T, Comparator<GameObject>> comparatorSupplier) {
        this.comparatorSupplier = comparatorSupplier;
        return this;
    }

    @Override
    public SelectFirstSubject<T> when(Predicate<GameObject> condition) {
        super.when(condition);
        return this;
    }

    @Override
    public SelectFirstSubject<T> when(Function<T, Predicate<GameObject>> conditionSupplier) {
        super.when(conditionSupplier);
        return this;
    }

    @Override
    public SelectFirstSubject<T> into(BiConsumer<T, GameObject> receiver) {
        super.into(receiver);
        return this;
    }

    @Override
    protected GameObject selectSubject(T data) {
        GameObjectComposite target = targetSupplier.apply(data);

        Predicate<GameObject> condition = conditionSupplier.apply(data);
        List<GameObject> results = toList(target.findAll(condition));

        Comparator<GameObject> comparator = comparatorSupplier.apply(data);
        results.sort(comparator);

        return !results.isEmpty() ? results.get(0) : null;
    }

    @Override
    protected Task<T> copyTo(Task<T> task) {
        SelectFirstSubject<T> selectFirstSubject = (SelectFirstSubject<T>)super.copyTo(task);
        selectFirstSubject.comparatorSupplier = this.comparatorSupplier;
        return selectFirstSubject;
    }
}
