/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.sequence;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.engine.action.ActionContext;
import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.action.common.NavigateAction;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.common.lang.Navigable;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.action.ActionProvider;
import com.evilbird.warcraft.action.identifier.NavigateActions;

import javax.inject.Inject;

/**
 * Instances of this class aid navigation through HUD menus.
 *
 * @author Blair Butterworth
 */
public class Navigate implements ActionProvider
{
    @Inject
    public Navigate() {
    }

    @Override
    public Action get(ActionIdentifier action, ActionContext context) {
        Item target = context.getTarget();
        Navigable navigable = (Navigable)target.getParent();

        NavigateActions navigateAction = (NavigateActions)action;
        Identifier location = navigateAction.getNavigateLocation();

        return new NavigateAction(navigable, location);
    }
}
