/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.camera;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.common.inject.InjectedPool;
import com.evilbird.warcraft.action.ActionProvider;
import org.apache.commons.lang3.Validate;

import javax.inject.Inject;

/**
 * Instances of this factory create {@link PanAction}s and {@link ZoomAction}s.
 *
 * @author Blair Butterworth
 */
public class CameraFactory implements ActionProvider
{
    private InjectedPool<PanAction> panPool;
    private InjectedPool<ZoomAction> zoomPool;
    private InjectedPool<FocusAction> focusPool;

    @Inject
    public CameraFactory(
        InjectedPool<FocusAction> focusPool,
        InjectedPool<PanAction> panPool,
        InjectedPool<ZoomAction> zoomPool)
    {
        this.focusPool = focusPool;
        this.panPool = panPool;
        this.zoomPool = zoomPool;
    }

    @Override
    public Action get(ActionIdentifier action) {
        Validate.isInstanceOf(CameraActions.class, action);
        switch((CameraActions)action) {
            case Focus: return focusPool.obtain();
            case Pan: return panPool.obtain();
            case Zoom: return zoomPool.obtain();
            default: throw new UnsupportedOperationException();
        }
    }
}
