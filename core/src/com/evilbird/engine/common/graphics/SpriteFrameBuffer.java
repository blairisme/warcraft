/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.common.graphics;

import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;

/**
 * A {@link FrameBuffer} specialization that allows the contents of the
 * FrameBuffer to be obtained in the form of a texture, which callers are then
 * responsible for disposing.
 *
 * @author Blair Butterworth
 */
public class SpriteFrameBuffer extends FrameBuffer
{
    private boolean managingColourBuffer;

    /**
     * Creates a new SpriteFrameBuffer with the given format, dimensions,and
     * whether or not to attach a depth buffer.
     */
    public SpriteFrameBuffer(Format format, int width, int height, boolean hasDepth) {
        super(format, width, height, hasDepth);
        this.managingColourBuffer = true;
    }

    /**
     * Returns the contents of the SpriteFrameBuffer in texture form. Once
     * called, clients are responsible for disposing of the resulting texture.
     */
    public Texture obtainTexture() {
        managingColourBuffer = false;
        return getColorBufferTexture();
    }

    @Override
    protected void disposeColorTexture(Texture texture) {
        if(managingColourBuffer) {
            texture.dispose();
        }
    }
}
