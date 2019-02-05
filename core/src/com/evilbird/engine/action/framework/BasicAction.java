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

    public BasicAction() {
        identifier = GenericIdentifier.Unknown;
    }

    public void cancel() {
        Item item = getItem();
        item.clearActions();
    }

    public void restart() {
        super.restart();
        error = null;
    }

    public Throwable getError() {
        return error;
    }

    public boolean hasError() {
        return getError() != null;
    }

    @Override
    public Identifier getIdentifier() {
        return identifier;
    }

    public void setError(Throwable error) {
        this.error = error;
    }

    public void setIdentifier(Identifier identifier) {
        this.identifier = identifier;
    }
}
