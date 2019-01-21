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

/**
 * Instances of this class aid navigation through user interface menus.
 *
 * @author Blair Butterworth
 */
public class NavigateAction extends BasicAction
{
    private Navigable navigable;
    private Identifier location;

    public NavigateAction(Navigable navigable, Identifier location) {
        this.navigable = navigable;
        this.location = location;
    }

    @Override
    public boolean act(float delta) {
        navigable.navigate(location);
        return true;
    }
}