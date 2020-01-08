/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.integration.device;

/**
 * @author Blair Butterworth
 */
public class MockGraphics extends com.badlogic.gdx.backends.headless.mock.graphics.MockGraphics
{
    private float deltaTime;

    public MockGraphics() {
        deltaTime = 1;
    }

    @Override
    public int getWidth() {
        return 1024;
    }

    @Override
    public int getHeight() {
        return 1024;
    }

    @Override
    public float getDeltaTime() {
        return deltaTime;
    }

    public void setDeltaTime(float time) {
        deltaTime = time;
    }
}
