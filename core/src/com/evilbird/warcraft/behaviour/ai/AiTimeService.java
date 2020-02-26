/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ai;

import com.badlogic.gdx.ai.Timepiece;

/**
 * A {@link Timepiece} implementation allowing the game time to be started from
 * a given point, allowing the time field to represent the total time the game
 * has been running.
 *
 * @author Blair Butterworth
 */
public class AiTimeService implements Timepiece
{
    private float deltaTime;
    private float totalTime;

    public AiTimeService() {
        this(0);
    }

    public AiTimeService(float totalTime) {
        this.totalTime = totalTime;
        this.deltaTime = 0f;
    }

    @Override
    public float getTime () {
        return totalTime;
    }

    @Override
    public float getDeltaTime () {
        return deltaTime;
    }

    @Override
    public void update (float deltaTime) {
        this.deltaTime = deltaTime;
        this.totalTime += this.deltaTime;
    }
}
