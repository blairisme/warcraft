/*
 * Blair Butterworth (c) 2018
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
import com.evilbird.engine.action.common.RemoveAction;
import com.evilbird.warcraft.action.ActionProvider;

import javax.inject.Inject;

/**
 * Instances of this class remove a given building site.
 *
 * @author Blair Butterworth
 */
public class PlaceholderCancel implements ActionProvider
{
    @Inject
    public PlaceholderCancel() {
    }

    @Override
    public Action get(ActionIdentifier action, ActionContext context) {
        return new RemoveAction(context.getItem());
    }
}
