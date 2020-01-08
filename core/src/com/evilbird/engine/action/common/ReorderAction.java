/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
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
