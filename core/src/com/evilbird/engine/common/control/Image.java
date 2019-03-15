/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.control;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.evilbird.engine.item.ItemBasic;

public class Image extends ItemBasic
{
    protected Drawable image;
    protected Drawable background;
    protected int padding;

    public Image() {
        this.image = null;
        this.background = null;
        this.padding = 0;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    public void setBackground(Drawable background) {
        this.background = background;
    }

    public void setPadding(int padding) {
        this.padding = padding;
    }

    @Override
    public void draw(Batch batch, float alpha) {
        if (background != null){
            float x = getX();
            float y = getY();
            float width = getWidth();
            float height = getHeight();
            background.draw(batch, x, y, width, height);
        }
        if (image != null){
            float x = getX() + padding;
            float y = getY() + padding;
            float width = getWidth() - (padding * 2);
            float height = getHeight() - (padding * 2);
            image.draw(batch, x, y, width, height);
        }
    }
}
