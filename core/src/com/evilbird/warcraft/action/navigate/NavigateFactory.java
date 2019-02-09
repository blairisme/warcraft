/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.navigate;

import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.action.common.ActionTarget;
import com.evilbird.engine.action.common.NavigateAction;
import com.evilbird.engine.action.framework.Action;
import com.evilbird.engine.action.utilities.InjectedPool;
import com.evilbird.warcraft.action.ActionProvider;
import org.apache.commons.lang3.Validate;

import javax.inject.Inject;

/**
 * Instances of this class aid navigation through HUD menus.
 *
 * @author Blair Butterworth
 */
public class NavigateFactory implements ActionProvider
{
    private InjectedPool<NavigateAction> pool;

    @Inject
    public NavigateFactory(InjectedPool<NavigateAction> pool) {
        this.pool = pool;
    }

    @Override
    public Action get(ActionIdentifier identifier) {
        Validate.isInstanceOf(NavigateActions.class, identifier);
        NavigateActions navigateAction = (NavigateActions)identifier;

        NavigateAction action = pool.obtain();
        action.setSource(ActionTarget.Parent);
        action.setLocation(navigateAction.getNavigateLocation());
        return action;
    }
}
