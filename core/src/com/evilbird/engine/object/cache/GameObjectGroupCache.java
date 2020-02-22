/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.object.cache;

import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.common.collection.Maps;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectGroup;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.inject.Inject;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.function.Predicate;

import static com.evilbird.engine.common.collection.CollectionUtils.first;
import static com.evilbird.engine.common.collection.Lists.asList;
import static com.evilbird.engine.common.collection.Maps.removeOrDefault;
import static com.evilbird.engine.object.cache.CacheRetentionType.Modify;
import static com.evilbird.engine.object.cache.CacheRetentionType.Update;
import static java.util.Collections.emptyList;

/**
 * A {@link GameObjectGroup} extension that caches object queries.
 *
 * @author Blair Butterworth
 */
public class GameObjectGroupCache extends GameObjectGroup
{
    private transient final Cache<Predicate<GameObject>, Collection<GameObject>> cache;
    private transient final Map<CacheRetentionType, Collection<Predicate<GameObject>>> retention;

    /**
     * Creates a new instance of this class with an empty cache.
     */
    @Inject
    public GameObjectGroupCache() {
        this.retention = new HashMap<>();
        this.cache = CacheBuilder.newBuilder()
            .maximumSize(100)
            .build();
    }

    /**
     * Adds a {@link GameObject} as a child of this Group.
     *
     * @param object the item to set.
     */
    @Override
    public void addObject(GameObject object) {
        cache.invalidateAll(removeOrDefault(retention, Modify, emptyList()));
        super.addObject(object);
    }

    /**
     * Removes a {@link GameObject} from this group.
     *
     * @param object the objects to remove.
     */
    @Override
    public void removeObject(GameObject object) {
        cache.invalidateAll(removeOrDefault(retention, Modify, emptyList()));
        super.removeObject(object);
    }

    /**
     * Removes all {@link GameObject}s from this group.
     */
    @Override
    public void removeObjects() {
        cache.invalidateAll();
        super.removeObjects();
    }

    /**
     * Returns the first child {@link GameObject} that satisfies the given
     * {@link Predicate}.
     *
     * @param predicate a predicate implementation used to differentiate
     *                  between items.
     * @return a child item satisfying the given predicate.
     */
    @Override
    public GameObject find(Predicate<GameObject> predicate) {
        try {
            return first(cache.get(predicate, () -> findImpl(predicate)));
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    private Collection<GameObject> findImpl(Predicate<GameObject> predicate) {
        Maps.compute(retention, getRetentionType(predicate), (k, v) -> asList(v, predicate));
        return Collections.singletonList(super.find(predicate));
    }

    /**
     * Returns the all child {@link GameObject}s that satisfy the given
     * {@link Predicate}.
     *
     * @param predicate a predicate implementation used to differentiate
     *                  between items.
     * @return all child items satisfying the given predicate.
     */
    public Collection<GameObject> findAll(Predicate<GameObject> predicate) {
        try {
            return cache.get(predicate, () -> findAllImpl(predicate));
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    private Collection<GameObject> findAllImpl(Predicate<GameObject> predicate) {
        Maps.compute(retention, getRetentionType(predicate), (k, v) -> asList(v, predicate));
        return super.findAll(predicate);
    }

    /**
     * Returns the {@link GameObject} at the specified location in world coordinates.
     * Hit testing is performed in the order the objects were inserted into the
     * group, last inserted actors being tested first, with the GameObjectGroup
     * itself tested last.
     *
     * @param coordinates the world coordinates to test.
     * @param touch       specifies if hit detection will respect the objects
     *                    touchability.
     * @return the object at the specified location. This method may return
     * {@code null} if no object can be located.
     */
    @Override
    public GameObject hit(Vector2 coordinates, boolean touch) {
        try {
            HitCacheEntry predicate = new HitCacheEntry(coordinates, touch);
            return first(cache.get(predicate, () -> hitImpl(predicate)));
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    private Collection<GameObject> hitImpl(HitCacheEntry predicate) {
        Maps.compute(retention, Update, (k, v) -> asList(v, predicate));
        return Collections.singletonList(super.hit(predicate.coordinates, predicate.touch));
    }

    @Override
    public void update(float delta) {
        cache.invalidateAll(removeOrDefault(retention, Update, emptyList()));
        super.update(delta);
    }

    private CacheRetentionType getRetentionType(Predicate<GameObject> condition) {
        Class<?> clazz = condition.getClass();
        if (clazz.isAnnotationPresent(CacheRetention.class)) {
            CacheRetention retention = clazz.getAnnotation(CacheRetention.class);
            return retention.value();
        }
        return Update;
    }

    private static class HitCacheEntry implements Predicate<GameObject> {
        private Vector2 coordinates;
        private boolean touch;

        public HitCacheEntry(Vector2 coordinates, boolean touch) {
            this.coordinates = coordinates;
            this.touch = touch;
        }

        @Override
        public boolean test(GameObject object) {
            return object.hit(coordinates, touch) != null;
        }

        @Override
        public boolean equals(Object object) {
            if (object == null) { return false; }
            if (object == this) { return true; }
            if (object.getClass() != getClass()) { return false; }

            HitCacheEntry that = (HitCacheEntry)object;
            return new EqualsBuilder()
                .append(touch, that.touch)
                .append(coordinates, that.coordinates)
                .isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37)
                .append(coordinates)
                .append(touch)
                .toHashCode();
        }
    }
}
