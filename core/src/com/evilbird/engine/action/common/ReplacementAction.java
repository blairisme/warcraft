/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.action.common;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.framework.DelegateAction;
import com.evilbird.engine.common.serialization.SerializedConstructor;
import com.evilbird.engine.item.Item;

/**
 * Instances of this {@link Action} replace an items actions with a given
 * action when executed.
 *
 * @author Blair Butterworth
 */
public class ReplacementAction extends DelegateAction
{
    @SerializedConstructor
    private ReplacementAction() {
    }

    public ReplacementAction(Action next) {
        super(next);
    }

    @Override
    public boolean act(float delta) {
        Item item = getItem();
        item.clearActions();
        item.addAction(delegate);
        return true;
    }

    public Action getReplacement() {
        return delegate;
    }
}
