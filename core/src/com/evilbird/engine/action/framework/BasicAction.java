/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.action.framework;

import com.badlogic.gdx.utils.Pool.Poolable;
import com.evilbird.engine.action.Action;
import com.evilbird.engine.common.lang.GenericIdentifier;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.item.Item;

/**
 * Instances of this class represent a base class for {@link com.evilbird.engine.action.Action Actions},
 * containing common methods and properties utilised by many custom actions.
 *
 * @author Blair Butterworth
 */
public abstract class BasicAction implements Action, Poolable
{
    private Identifier identifier;
    private Item item;
    private Item target;
    private Throwable error;
    private UserInput cause;

    public BasicAction() {
        identifier = GenericIdentifier.Unknown;
    }

    @Override
    public void cancel() {
        Item item = getItem();
        item.clearActions();
    }

    public void reset() {
        error = null;
    }

    @Override
    public void restart() {
        error = null;
    }

    @Override
    public Item getItem() {
        return item;
    }

    @Override
    public Item getTarget() {
        return target;
    }

    @Override
    public UserInput getCause() {
        return cause;
    }

    @Override
    public Throwable getError() {
        return error;
    }

    @Override
    public Identifier getIdentifier() {
        return identifier;
    }

    @Override
    public boolean hasError() {
        return getError() != null;
    }

    @Override
    public void setCause(UserInput cause) {
        this.cause = cause;
    }

    @Override
    public void setItem(Item item) {
        this.item = item;
    }

    @Override
    public void setTarget(Item target) {
        this.target = target;
    }

    @Override
    public void setError(Throwable error) {
        this.error = error;
    }

    @Override
    public void setIdentifier(Identifier identifier) {
        this.identifier = identifier;
    }
}
