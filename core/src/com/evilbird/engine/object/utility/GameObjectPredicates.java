/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.object.utility;

import com.badlogic.gdx.math.Rectangle;
import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.device.UserInputType;
import com.evilbird.engine.object.GameObject;
import org.apache.commons.lang3.ArrayUtils;

import java.util.function.Predicate;

import static com.evilbird.engine.object.utility.GameObjectOperations.hasIdStartingWith;

/**
 * Defines commonly used {@link Predicate Predicates} that operate on
 * {@link GameObject Items}.
 *
 * @author Blair Butterworth
 */
public class GameObjectPredicates
{
    private GameObjectPredicates() {
        throw new UnsupportedOperationException();
    }

    public static Predicate<GameObject> withIdStartingWith(String id) {
        return (item) -> item != null && hasIdStartingWith(item, id);
    }

    public static Predicate<GameObject> itemWithId(final Identifier id) {
        return (item) -> item != null && id.equals(item.getIdentifier());
    }

    public static Predicate<GameObject> withId(final Identifier id) {
        return itemWithId(id);
    }

    public static Predicate<GameObject> itemWithType(final Identifier type) {
        return (item) -> item != null && type.equals(item.getType());
    }

    public static Predicate<GameObject> withType(final Identifier type) {
        return itemWithType(type);
    }

    public static Predicate<GameObject> withClazz(final Class<?> type) {
        return (item) -> item != null && type.isAssignableFrom(item.getClass());
    }

    public static Predicate<GameObject> touchableWithType(Identifier type) {
        return item -> item != null && item.getTouchable() && type.equals(item.getType());
    }

    public static Predicate<GameObject> isVisible() {
        return GameObject::getVisible;
    }

    public static Predicate<GameObject> isIdle() {
        return GameObjectOperations::isIdle;
    }

    public static Predicate<GameObject> isNear(GameObject target) {
        return item -> GameObjectOperations.isNear(item, item.getWidth(), target);
    }

    public static Predicate<GameObject> hasType(Identifier type) {
        return item -> item != null && item.getType() == type;
    }

    public static Predicate<GameObject> hasType(Identifier ... types) {
        return item -> item != null && ArrayUtils.contains(types, item.getType());
    }

    public static Predicate<UserInput> hasType(UserInputType type) {
        return input -> input != null && input.getType() == type;
    }

    public static Predicate<Action> hasType(ActionIdentifier type) {
        return action -> action != null && action.getIdentifier() == type;
    }

    public static Predicate<GameObject> hasAction(Predicate<Action> condition) {
        return item -> GameObjectOperations.hasAction(item, condition);
    }

    public static Predicate<GameObject> hasAction(Identifier identifier) {
        return item -> GameObjectOperations.hasAction(item, identifier);
    }

    public static Predicate<GameObject> overlapping(GameObject target) {
        return overlapping(target.getBounds());
    }

    public static Predicate<GameObject> overlapping(Rectangle target) {
        return item -> item != null && target.overlaps(item.getBounds());
    }

    public static Predicate<GameObject> touchable() {
        return item -> item != null && item.getTouchable();
    }
}
