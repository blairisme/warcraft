/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.action.framework;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.engine.common.function.ResettableSupplier;
import com.evilbird.engine.common.function.Supplier;
import com.evilbird.engine.common.function.Suppliers;

/**
 * Instances of this {@link Action} execute a given action repeatedly, until
 * (optionally) instructed to stop.
 *
 * @author Blair Butterworth
 */
public class RepeatedAction extends DelegateAction
{
    private Supplier<Boolean> repeat;

    public RepeatedAction(Action action) {
        this(action, Suppliers.isTrue());
    }

    public RepeatedAction(Action action, ResettableSupplier<Boolean> repeat) {
        this(action, (Supplier<Boolean>)repeat);
    }

    public RepeatedAction(Action action, Supplier<Boolean> repeat) {
        super(action);
        this.repeat = repeat;
    }

    @Override
    public boolean act(float delta) {
        if (delegate.act(delta)) {
            return repeat();
        }
        return false;
    }

    private boolean repeat() {
        if (repeat.get()) {
            delegate.restart();
            return false;
        }
        return true;
    }

    @Override
    public void restart() {
        super.restart();
        if (repeat instanceof ResettableSupplier) {
            ((ResettableSupplier)repeat).reset();
        }
    }
}
