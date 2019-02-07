/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.action.common;

import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemGroup;

import javax.inject.Inject;

public class RemoveAction extends BasicAction
{
    private ActionTarget source;

    @Inject
    public RemoveAction() {
        this(ActionTarget.Item);
    }

    public RemoveAction(ActionTarget source) {
        this.source = source;
    }

    @Override
    public boolean act(float delta) {
        Item item = getRemoveItem();
        ItemGroup parent = item.getParent();
        parent.removeItem(item);
        return true;
    }

    private Item getRemoveItem() {
        switch (source) {
            case Item: return getItem();
            case Target: return getTarget();
            default: throw new UnsupportedOperationException();
        }
    }
}
