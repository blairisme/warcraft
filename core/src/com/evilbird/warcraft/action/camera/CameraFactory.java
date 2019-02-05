/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.camera;

import com.evilbird.engine.action.ActionContext;
import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.action.common.PanAction;
import com.evilbird.engine.action.framework.Action;
import com.evilbird.engine.action.utilities.InjectedPool;
import com.evilbird.warcraft.action.ActionProvider;
import com.evilbird.warcraft.item.common.capability.Zoomable;
import org.apache.commons.lang3.Validate;

import javax.inject.Inject;

public class CameraFactory implements ActionProvider
{
    private InjectedPool<PanAction> panPool;
    private InjectedPool<ZoomAction> zoomPool;

    @Inject
    public CameraFactory(InjectedPool<PanAction> panPool, InjectedPool<ZoomAction> zoomPool) {
        this.panPool = panPool;
        this.zoomPool = zoomPool;
    }

    @Override
    public Action get(ActionIdentifier action, ActionContext context) {
        Validate.isInstanceOf(CameraActions.class, action);
        switch((CameraActions)action) {
            case Pan: return getPanAction(context);
            case Zoom: return getZoomAction(context);
            default: throw new UnsupportedOperationException();
        }
    }

    private Action getPanAction(ActionContext context) {
        PanAction action = panPool.obtain();
        action.setPositionable(context.getItem());
        action.setPositionDelta(context.getInput().getDelta());
        return action;
    }

    private Action getZoomAction(ActionContext context) {
        ZoomAction action = zoomPool.obtain();
        action.setZoomable((Zoomable)context.getItem());
        action.setZoomEvent(context.getInput());
        return action;
    }
}
