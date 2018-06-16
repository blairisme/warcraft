package com.evilbird.engine.item.control;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
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
        if (disabledImage != null){
            float x = getX();
            float y = getY();
            float width = getWidth();
            float height = getHeight();
            disabledImage.draw(batch, x, y, width, height);
        }
    }
}
