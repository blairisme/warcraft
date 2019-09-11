/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
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
