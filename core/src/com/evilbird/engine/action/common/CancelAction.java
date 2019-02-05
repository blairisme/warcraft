/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.action.common;

import com.evilbird.engine.action.framework.Action;
import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.item.Item;

/**
 * Instances of this class represent an {@link Action} that cancels the
 * <code>Actions</code> assigned to a given {@link Item}.
 *
 * @author Blair Butterworth
 */
public class CancelAction extends BasicAction
{
    private Item item;

    public CancelAction(Item item) {
        this.item = item;
    }

    @Override
    public boolean act(float delta) {
        for (Action action: item.getActions()) {
            action.restart();
        }
        item.clearActions();
        return true;
    }
}