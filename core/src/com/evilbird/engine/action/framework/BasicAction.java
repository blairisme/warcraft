/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.action.framework;

import com.evilbird.engine.common.lang.GenericIdentifier;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemAction;

/**
 * Instances of this class represent a base class for {@link Action Actions},
 * containing common methods and properties utilised by many custom actions.
 *
 * @author Blair Butterworth
 */
public abstract class BasicAction extends ItemAction implements Action
{
    private Identifier identifier;
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

    @Override
    public void restart() {
        super.restart();
        error = null;
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
    public void setError(Throwable error) {
        this.error = error;
    }

    @Override
    public void setIdentifier(Identifier identifier) {
        this.identifier = identifier;
    }
}
