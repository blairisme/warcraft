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
import com.evilbird.engine.action.common.PanAction;
import com.evilbird.warcraft.action.ActionProvider;

import javax.inject.Inject;

/**
 * Instances of this class pan the camera.
 *
 * @author Blair Butterworth
 */
public class CameraPan implements ActionProvider
{
    @Inject
    public CameraPan() {
    }

    @Override
    public Action get(ActionIdentifier action, ActionContext context) {
        return new PanAction(context.getItem(), context.getInput());
    }
}
