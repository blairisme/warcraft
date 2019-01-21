/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.item.control;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class ImageButton extends Image
{
    private Drawable disabledImage;

    public ImageButton() {
        super();
    }

    public void setDisabledImage(Drawable disabledImage) {
        this.disabledImage = disabledImage;
    }

    @Override
    public void draw(Batch batch, float alpha) {
        super.draw(batch, alpha);
        if (getTouchable() == false && disabledImage != null){
            float x = getX() + padding;
            float y = getY() + padding;
            float width = getWidth() - (padding * 2);
            float height = getHeight() - (padding * 2);
            disabledImage.draw(batch, x, y, width, height);
        }
    }
}
