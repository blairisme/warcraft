/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.common.graphics.renderable;

import com.badlogic.gdx.graphics.g2d.Batch;

/**
 * A {@link Renderable} implementation that doesn't do anything when drawn.
 *
 * @author Blair Butterworth
 */
public class EmptyRenderable extends BaseRenderable
{
    public static final EmptyRenderable BlankRenderable = new EmptyRenderable();

    @Override
    public void draw(Batch batch, float x, float y, float width, float height) {
    }

    @Override
    public void update(float time) {
    }
}
