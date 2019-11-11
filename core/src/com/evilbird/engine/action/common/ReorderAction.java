/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.action.common;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectGroup;

/**
 * Instances of this {@link Action} modify an {@link GameObject Items} zIndex.
 *
 * @author Blair Butterworth
 */
public class ReorderAction extends BasicAction
{
    private int index;

    public ReorderAction(int index) {
        this.index = index;
    }

    public static ReorderAction sendToBack() {
        return new ReorderAction(0);
    }

    public static ReorderAction sendToFront() {
        return new ReorderAction(Integer.MAX_VALUE);
    }

    @Override
    public boolean act(float delta) {
        GameObject gameObject = getSubject();
        gameObject.setZIndex(index);

        GameObjectGroup group = gameObject.getParent();
        group.setZIndex(gameObject, index);

        return true;
    }
}
