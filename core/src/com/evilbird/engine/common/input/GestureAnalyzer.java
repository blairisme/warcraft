/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
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
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        boolean result = super.touchUp(screenX, screenY, pointer, button);
        observer.touchUp(screenX, screenY, pointer, button);
        return result;
    }
}
