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
import com.evilbird.warcraft.action.ActionProvider;
import com.evilbird.warcraft.action.component.ZoomAction;
import com.evilbird.warcraft.item.common.capability.Zoomable;

import javax.inject.Inject;

/**
 * Instances of this class zoom the camera.
 *
 * @author Blair Butterworth
 */
public class CameraZoom implements ActionProvider
{
    @Inject
    public CameraZoom() {
    }

    @Override
    public Action get(ActionIdentifier action, ActionContext context) {
        return new ZoomAction((Zoomable)context.getItem(), context.getInput());
    }
}
