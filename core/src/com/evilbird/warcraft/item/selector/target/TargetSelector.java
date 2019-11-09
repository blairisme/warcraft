/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.selector.target;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.evilbird.engine.item.ItemBasic;

/**
 * Represents a visual guide used to select game world locations for use by
 * actions.
 *
 * @author Blair Butterworth
 */
public class TargetSelector extends ItemBasic
{
    private Drawable texture;

    public TargetSelector() {
    }

    @Override
    public void draw(Batch batch, float alpha) {
        texture.draw(batch, getX(), getY(), getWidth(), getHeight());
    }

    public void setTexture(Drawable texture) {
        this.texture = texture;
    }
}
