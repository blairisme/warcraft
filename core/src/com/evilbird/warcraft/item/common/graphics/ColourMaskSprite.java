/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.common.graphics;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import static com.badlogic.gdx.graphics.Color.WHITE;

/**
 * Represents a sprite that can have a coloured mask applied to it, altering
 * the colours of specific regions of the sprite.
 *
 * @author Blair Butterworth
 */
public class ColourMaskSprite implements Drawable
{
    private Color colour;
    private Drawable base;
    private Drawable mask;

    public ColourMaskSprite(TextureRegion base, Texture mask, Color color) {
        TextureRegion maskRegion = new TextureRegion();
        maskRegion.setRegion(base);
        maskRegion.setTexture(mask);

        this.base = new TextureRegionDrawable(base);
        this.mask = new TextureRegionDrawable(maskRegion);
        this.colour = color;
    }

    public ColourMaskSprite(TextureRegion base, TextureRegion mask, Color colour) {
        this.base = new TextureRegionDrawable(base);
        this.mask = new TextureRegionDrawable(mask);
        this.colour = colour;
    }

    @Override
    public void draw(Batch batch, float x, float y, float width, float height) {
        batch.setColor(WHITE);
        base.draw(batch, x, y, width, height);
        batch.setColor(colour);
        mask.draw(batch, x, y, width, height);
        batch.setColor(WHITE);
    }

    public Color getColour() {
        return colour;
    }

    @Override
    public float getLeftWidth() {
        return base.getLeftWidth();
    }

    @Override
    public float getRightWidth() {
        return base.getRightWidth();
    }

    @Override
    public float getTopHeight() {
        return base.getTopHeight();
    }

    @Override
    public float getBottomHeight() {
        return base.getBottomHeight();
    }

    @Override
    public float getMinWidth() {
        return base.getMinWidth();
    }

    @Override
    public float getMinHeight() {
        return base.getMinHeight();
    }

    public void setColour(Color colour) {
        this.colour = colour;
    }

    @Override
    public void setLeftWidth(float leftWidth) {
        base.setLeftWidth(leftWidth);
        mask.setLeftWidth(leftWidth);
    }

    @Override
    public void setRightWidth(float rightWidth) {
        base.setRightWidth(rightWidth);
        mask.setRightWidth(rightWidth);
    }

    @Override
    public void setTopHeight(float topHeight) {
        base.setTopHeight(topHeight);
        mask.setTopHeight(topHeight);
    }

    @Override
    public void setBottomHeight(float bottomHeight) {
        base.setBottomHeight(bottomHeight);
        mask.setBottomHeight(bottomHeight);
    }

    @Override
    public void setMinWidth(float minWidth) {
        base.setMinWidth(minWidth);
        mask.setMinWidth(minWidth);
    }

    @Override
    public void setMinHeight(float minHeight) {
        base.setMinHeight(minHeight);
        mask.setMinHeight(minHeight);
    }
}
