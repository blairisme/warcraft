/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.placeholder;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.evilbird.engine.item.Item;

public class Placeholder extends Item
{
    private transient Drawable building;
    private transient Drawable overlay;
    private transient Drawable allowed;
    private transient Drawable prohibited;

    public Placeholder() {
    }

    @Override
    public void draw(Batch batch, float alpha) {
        float x = getX();
        float y = getY();
        float width = getWidth();
        float height = getHeight();
        building.draw(batch, x, y, width, height);
        overlay.draw(batch, x, y, width, height);
    }

    public void setBuildingTexture(Drawable texture) {
        this.building = texture;
    }

    public void setAllowedTexture(Drawable texture) {
        this.allowed = texture;
        this.overlay = texture;
    }

    public void setProhibitedTexture(Drawable texture) {
        this.prohibited = texture;
    }
}
