/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.object;

import com.badlogic.gdx.math.Vector2;

import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

/**
 * Implementors of this interface represent a container for a collection of
 * {@link GameObject GameObjects}.
 *
 * @author Blair Butterworth
 */
public interface GameObjectComposite
{
    /**
     * Adds a {@link GameObject} as a child of the {@code GameObjectComposite}.
     *
     * @param objects  the {@code GameObject} to add.
     */
    void addObject(GameObject objects);

    /**
     * Adds a collection of {@link GameObject GameObjects} as children of the
     * {@code GameObjectComposite}.
     *
     * @param objects the {@code GameObjects} to add.
     */
    void addObjects(Collection<GameObject> objects);

    /**
     * Returns the {@link GameObject GameObjects} contained in the
     * {@code GameObjectComposite}.
     *
     * @return the children of the {@code GameObjectComposite}.
     */
    List<GameObject> getObjects();

    /**
     * Determines whether the given {@link GameObject} is contained in the
     * {@code GameObjectComposite}: its one of its children.
     *
     * @param object    the {@code GameObject} to search for.
     *
     * @return  {@code true} if the given {@code GameObject} is contained in
     *          the {@code GameObjectComposite}.
     */
    boolean containsObject(GameObject object);

    /**
     * Removes all {@link GameObject}s from the {@code GameObjectComposite}.
     */
    void removeObjects();

    /**
     * Removes a {@link GameObject} from the {@code GameObjectComposite}.
     *
     * @param gameObject  the item to remove.
     */
    void removeObject(GameObject gameObject);

    /**
     * Returns the first child {@link GameObject} that satisfies the given
     * {@link Predicate}.
     *
     * @param predicate a predicate implementation used to differentiate
     *                  between objects.
     * @return          a game object that satisfies the given predicate.
     */
    GameObject find(Predicate<GameObject> predicate);

    /**
     * Returns all the {@link GameObject Gameobjects} contained in the
     * {@code GameObjectComposite} that satisfy the given {@link Predicate}.
     *
     * @param predicate a predicate implementation used to differentiate
     *                  between items.
     * @return          all game objects that satisfy the given predicate.
     */
    <T extends GameObject> Collection<T> findAll(Predicate<T> predicate);

    /**
     * Returns the {@link GameObject} at the specified location in world
     * coordinates. Hit testing is performed in the order the item were
     * inserted into the root, last inserted actors being tested first.
     *
     * @param coordinates   the world coordinates to test.
     * @param touchable     specifies if hit detection will respect a
     *                      {@code GameObjects} touchability.
     * @return              the game object at the specified location or
     *                      {@code null} if no {@code GameObject} is located
     *                      there.
     */
    GameObject hit(Vector2 coordinates, boolean touchable);
}
