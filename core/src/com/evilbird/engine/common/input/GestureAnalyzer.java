/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.common.input;

import com.badlogic.gdx.input.GestureDetector;

/**
 * Instances of this class detect gestures. Specifically this class extends
 * {@link GestureDetector}, providing touch up events.
 *
 * @author Blair Butterworth
 */
public class GestureAnalyzer extends GestureDetector
{
    private GestureObserver observer;

    public GestureAnalyzer(GestureObserver observer) {
        super(observer);
        this.observer = observer;
    }

    @Override
    public boolean keyDown(int keycode) {
        boolean result = observer.keyDown(keycode);
        if (! result) {
            result = super.keyDown(keycode);
        }
        return result;
    }

    @Override
    public boolean keyUp(int keycode) {
        boolean result = observer.keyUp(keycode);
        if (! result) {
            result = super.keyUp(keycode);
        }
        return result;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        boolean result = super.touchUp(screenX, screenY, pointer, button);
        observer.touchUp(screenX, screenY, pointer, button);
        return result;
    }

    @Override
    public boolean scrolled(int amount) {
        boolean result = super.scrolled(amount);
        observer.scrolled(amount);
        return result;
    }
}
