/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.camera;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.common.inject.InjectedPool;
import com.evilbird.warcraft.action.ActionProvider;
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
    public Action get(ActionIdentifier action) {
        Validate.isInstanceOf(CameraActions.class, action);
        switch((CameraActions)action) {
            case Pan: return panPool.obtain();
            case Zoom: return zoomPool.obtain();
            default: throw new UnsupportedOperationException();
        }
    }
}
