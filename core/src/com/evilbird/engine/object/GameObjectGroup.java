/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.object;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.evilbird.engine.common.collection.CollectionUtils;
import com.evilbird.engine.common.serialization.SerializedInitializer;
import com.evilbird.engine.object.interop.GroupDecorator;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

/**
 * Represents a logical grouping of {@link GameObject GameObjects}.
 *
 * @author Blair Butterworth
 */
public class GameObjectGroup extends BasicGameObject implements GameObjectComposite
{
    protected boolean fill;
    protected List<GameObject> objects;
    protected transient Collection<GameObjectGroupObserver> observers;

    public GameObjectGroup() {
        this.fill = false;
        this.objects = new ArrayList<>();
        this.observers = new ArrayList<>();
    }

    /**
     * Adds a {@link GameObject} as a child of this Group.
     *
     * @param object the item to set.
     */
    public void addObject(GameObject object) {
        int index = Math.min(object.getZIndex(), objects.size());
        Group group = (Group)delegate;
        group.addActorAt(index, object.toActor());
        object.setParent(this);
        object.setRoot(getRoot());
        objects.add(index, object);
        CollectionUtils.forEach(observers, it -> it.objectAdded(object));
    }

    /**
     * Adds a collection of {@link GameObject objects} as children of the 
     * GameObjectGroup.
     *
     * @param objects  the objects to add.
     */
    @Override
    public void addObjects(Collection<GameObject> objects) {
        for (GameObject object : objects) {
            addObject(object);
        }
    }

    /**
     * Removes a {@link GameObject} from this group.
     *
     * @param object the objects to remove.
     */
    public void removeObject(GameObject object) {
        object.toActor().remove();
        objects.remove(object);
        CollectionUtils.forEach(observers, it -> it.objectRemoved(object));
    }

    /**
     * Removes all {@link GameObject}s from this group.
     */
    public void removeObjects() {
        Group group = (Group)delegate;
        group.clearChildren();
        objects.clear();
        CollectionUtils.forEach(observers, GameObjectGroupObserver::objectsCleared);
    }

    /**
     * Returns a collection containing the children of the GameObjectGroup.
     */
    public List<GameObject> getObjects() {
        return objects;
    }

    /**
     * Determines whether the given {@link GameObject} is contained in the
     * {@code GameObjectGroup}: its one of its children.
     */
    public boolean containsObject(GameObject object) {
        return objects.contains(object);
    }

    /**
     * Returns whether the {@code GameObjectGroup}has been assigned any child Items.
     */
    public boolean hasObjects() {
        return !objects.isEmpty();
    }

    /**
     * Adds an {@link GameObjectGroupObserver observer} to the collection of
     * observers that will be notified when an {@link GameObject} is added or
     * removed from the GameObjectGroup.
     *
     * @param observer an {@code GameObjectGroupObserver}.
     */
    public void addObserver(GameObjectGroupObserver observer) {
        this.observers.add(observer);
        for (GameObject gameObject : objects) {
            if (gameObject instanceof GameObjectGroup) {
                GameObjectGroup group = (GameObjectGroup) gameObject;
                group.addObserver(observer);
            }
        }
    }

    /**
     * Removes an {@link GameObjectGroupObserver observer} from the collection of
     * observers that are be notified when an {@link GameObject} is added or
     * removed from the GameObjectGroup.
     *
     * @param observer an {@code GameObjectGroupObserver}.
     */
    public void removeObserver(GameObjectGroupObserver observer) {
        this.observers.remove(observer);
        for (GameObject gameObject : objects) {
            if (gameObject instanceof GameObjectGroup) {
                GameObjectGroup group = (GameObjectGroup) gameObject;
                group.removeObserver(observer);
            }
        }
    }

    /**
     * Whether the {@code GameObjectGroup} should resize itself to fill its parent.
     */
    public boolean getFillParent() {
        return this.fill;
    }

    /**
     * Instructs the {@code GameObjectGroup} to resize to fill its parent.
     *
     * @param fill {@code true} if the {@code GameObjectGroup} should fill its parent.
     */
    public void setFillParent(boolean fill) {
        this.fill = fill;
    }

    /**
     * Sets the most distant ancestor of the Item in the Item hierarchy.
     *
     * @param root an {@link GameObjectContainer} instance.
     */
    @Override
    public void setRoot(GameObjectContainer root) {
        super.setRoot(root);
        CollectionUtils.forEach(objects, objects -> objects.setRoot(root));
    }

    /**
     * Modifies the zIndex of the given {@link GameObject}. The zIndex defines 
     * an objects position when rendering or during hit detection.
     *
     * @param object    the object whose zIndex will be modified.
     * @param zIndex    the new zIndex of the objects.
     */
    public void setZIndex(GameObject object, int zIndex) {
        if (objects.contains(object)) {
            int index = Math.min(zIndex, objects.size());

            Group group = (Group) delegate;
            group.removeActor(object.toActor());
            group.addActorAt(index, object.toActor());

            objects.remove(object);
            objects.add(index, object);
        }
    }

    /**
     * Returns the {@link GameObject} at the specified location in world coordinates.
     * Hit testing is performed in the order the objects were inserted into the
     * group, last inserted actors being tested first, with the GameObjectGroup
     * itself tested last.
     *
     * @param coordinates         the world coordinates to test.
     * @param respectTouchability specifies if hit detection will respect the
     *                            objectss touchability.
     *
     * @return  the object at the specified location. This method may return
     *          {@code null} if no object can be located.
     */
    @Override
    public GameObject hit(Vector2 coordinates, boolean respectTouchability) {
        if (respectTouchability && delegate.getTouchable() == Touchable.disabled) {
            return null;
        }
        GameObject childHit = childHit(coordinates, respectTouchability);
        if (childHit != null) {
            return childHit;
        }
        return super.hit(coordinates, respectTouchability);
    }

    /**
     * Returns the {@link GameObject} at the specified location in world coordinates.
     * Hit testing is performed only on the child objects contained the GameObjectGroup
     * in the order the object were inserted into the group, last inserted actors
     * being tested first. This method does not respect the touchability of the
     * GameObjectGroup, only its children.
     *
     * @param coordinates         the world coordinates to test.
     * @param respectTouchability specifies if hit detection will respect the
     *                            objects touchability.
     *
     * @return  the object at the specified location. This method may return
     *          {@code null} if no object can be located.
     */
    protected GameObject childHit(Vector2 coordinates, boolean respectTouchability) {
        for (int objectIndex = objects.size() - 1; objectIndex >= 0; objectIndex--) {
            GameObject gameObject = objects.get(objectIndex);
            Vector2 localCoordinates = gameObject.parentToLocalCoordinates(coordinates);
            GameObject hit = gameObject.hit(localCoordinates, respectTouchability);
            if (hit != null) {
                return hit;
            }
        }
        return null;
    }

    /**
     * Returns the first child {@link GameObject} that satisfies the given
     * {@link Predicate}.
     *
     * @param predicate a predicate implementation used to differentiate
     *                  between objects.
     * @return a child object satisfying the given predicate.
     */
    public GameObject find(Predicate<GameObject> predicate) {
        return find(this, predicate);
    }

    private GameObject find(GameObjectComposite group, Predicate<GameObject> predicate) {
        for (GameObject gameObject : group.getObjects()) {
            if (predicate.test(gameObject)) {
                return gameObject;
            }
            if (gameObject instanceof GameObjectComposite) {
                GameObject result = find((GameObjectComposite) gameObject, predicate);
                if (result != null) {
                    return result;
                }
            }
        }
        return null;
    }

    /**
     * Returns the all child {@link GameObject}s that satisfy the given
     * {@link Predicate}.
     *
     * @param predicate a predicate implementation used to differentiate
     *                  between objects.
     * @return all child objects satisfying the given predicate.
     */
    public <T extends GameObject> Collection<T> findAll(Predicate<T> predicate) {
        return findAll(this, predicate);
    }

    @SuppressWarnings("unchecked")
    private <T extends GameObject> Collection<T> findAll(GameObjectComposite group, Predicate<T> predicate) {
        Collection<T> result = new ArrayList<>();
        for (GameObject gameObject : group.getObjects()) {
            if (predicate.test((T)gameObject)) {
                result.add((T)gameObject);
            }
            if (gameObject instanceof GameObjectComposite) {
                result.addAll(findAll((GameObjectComposite)gameObject, predicate));
            }
        }
        return result;
    }

    @Override
    public void update(float delta) {
        GameObjectGroup parent = getParent();
        if (fill && parent != null) {
            float parentWidth = parent.getWidth();
            float parentHeight = parent.getHeight();
            if (parentWidth != getWidth() || parentHeight != getHeight()) {
                setSize(parentWidth, parentHeight);
            }
        }
        super.update(delta);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .appendSuper("group")
            .append("objects", objects)
            .append("fill", fill)
            .toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (obj.getClass() != getClass()) { return false; }

        GameObjectGroup objectGroup = (GameObjectGroup)obj;
        return new EqualsBuilder()
            .appendSuper(super.equals(obj))
            .append(fill, objectGroup.fill)
            .append(objects, objectGroup.objects)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .appendSuper(super.hashCode())
            .append(fill)
            .append(objects)
            .toHashCode();
    }

    @Override
    protected Actor newDelegate() {
        return new GroupDecorator(this);
    }

    @SerializedInitializer
    protected void updateDelegate() {
        super.updateDelegate();
        Group group = (Group)delegate;

        for (GameObject gameObject : objects) {
            group.addActor(gameObject.toActor());
            gameObject.setParent(this);
        }
    }
}
