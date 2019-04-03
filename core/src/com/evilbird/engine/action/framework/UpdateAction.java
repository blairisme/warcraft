/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.action.framework;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.common.ActionTarget;
import com.evilbird.engine.item.Item;

import java.util.function.Supplier;

/**
 * An {@link Action} that updates the item or target of a given {@link Action}
 * action when run, before delegating execution to the Action.
 *
 * @author Blair Butterworth
 */
public class UpdateAction extends DelegateAction
{
    private boolean applied;
    private ActionTarget type;
    private Supplier<Item> supplier;

    public UpdateAction(Action client, Supplier<Item> supplier, ActionTarget type) {
        super(client);
        this.applied = false;
        this.type = type;
        this.supplier = supplier;
    }

    @Override
    public boolean act(float delta) {
        if (! applied) {
            applied = true;
            update();
        }
        return delegate.act(delta);
    }

    @Override
    public void restart() {
        super.restart();
        applied = false;
    }

    @Override
    public void reset() {
        super.reset();
        applied = false;
    }

    private void update() {
        if (type == ActionTarget.Item) {
            setItem(supplier.get());
        }
        else if (type == ActionTarget.Target) {
            setTarget(supplier.get());
        }
        else {
            throw new UnsupportedOperationException();
        }
    }
}
