/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.behaviour.framework.select;

import com.badlogic.gdx.ai.btree.LeafTask;
import com.evilbird.engine.object.GameObject;
import org.apache.commons.lang3.RandomUtils;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

import static com.evilbird.engine.common.collection.Lists.toList;

/**
 * A {@link LeafTask} implementation that selects a collection of
 * {@link GameObject GameObjects} based on a given {@link Predicate condition},
 * which is further refined by then selecting a random sample of a set size.
 *
 * @param <T> type of the blackboard object used by the task.
 *
 * @author Blair Butterworth
 */
public class SelectRandomSubjects<T> extends SelectSubjects<T>
{
    protected transient int size;

    @Inject
    public SelectRandomSubjects() {
        this.size = 0;
    }

    public SelectRandomSubjects<T> size(int size) {
        this.size = size;
        return this;
    }

    @Override
    protected Collection<GameObject> selectSubjects(T data) {
        Collection<GameObject> subjects = super.selectSubjects(data);
        if (size < subjects.size()) {
            return randomSubset(subjects, size);
        }
        return subjects;
    }

    protected Collection<GameObject> randomSubset(Collection<GameObject> collection, int size) {
        List<GameObject> elements = toList(collection);
        Collection<GameObject> subset = new ArrayList<>(size);
        for (int i = 0; i < size; ++i) {
            GameObject element = elements.remove(RandomUtils.nextInt(0, elements.size()));
            subset.add(element);
        }
        return subset;
    }
}
