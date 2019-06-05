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
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemGroup;

/**
 * Instances of this {@link Action} modify an {@link Item Items} zIndex.
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

    @Override
    public boolean act(float delta) {
        Item item = getItem();
        item.setZIndex(index);

        ItemGroup group = item.getParent();
        group.setZIndex(item, index);

        return false;
    }
}
