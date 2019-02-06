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
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.common.lang.Navigable;

import javax.inject.Inject;

/**
 * Instances of this class aid navigation through user interface menus.
 *
 * @author Blair Butterworth
 */
public class NavigateAction extends BasicAction
{
    private Identifier location;
    private ActionTarget source;

    @Inject
    public NavigateAction() {
        source = ActionTarget.Item;
    }

    public void setSource(ActionTarget source) {
        this.source = source;
    }

    public void setLocation(Identifier location) {
        this.location = location;
    }

    @Override
    public boolean act(float delta) {
        Navigable navigable = getNavigable();
        navigable.navigate(location);
        return true;
    }

    private Navigable getNavigable() {
        switch (source) {
            case Item: return (Navigable)getItem();
            case Target: return (Navigable)getTarget();
            case Parent: return (Navigable)getItem().getParent();
            default: throw new UnsupportedOperationException();
        }
    }
}