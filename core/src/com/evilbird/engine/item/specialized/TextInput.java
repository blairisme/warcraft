/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.item.specialized;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.FocusListener;
import com.badlogic.gdx.utils.Disposable;
import com.evilbird.engine.common.graphics.TextureUtils;

/**
 * <p>
 * Represents a simple text input control.
 * </p>
 * <p>
 *  This control also overrides the default {@link TextField} behaviour,
 *  allowing the colour of the controls border to be set.
 * </p>
 *
 * @author Blair Butterworth
 */
public class TextInput extends TextField implements Disposable
{
    private Skin skin;
    private TextInputStyle style;
    private boolean inFocus;
    private Color borderColour;
    private Drawable borderDrawable;
    private Texture borderTexture;

    public TextInput(String text, Skin skin) {
        super(text, skin);
        this.skin = skin;
        addListener(new FocusObserver());
    }

    @Override
    public void dispose() {
        if (borderTexture != null) {
            borderTexture.dispose();
            borderDrawable = null;
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        if (borderDrawable != null) {
            borderDrawable.draw(batch, getX(), getY(), getWidth(), getHeight());
        }
    }

    public void setStyle(String name) {
        setStyle(skin.get(name, TextInputStyle.class));
    }

    @Override
    public void setStyle(TextFieldStyle fieldStyle) {
        super.setStyle(fieldStyle);
        if (fieldStyle instanceof TextInputStyle) {
            style = (TextInputStyle)fieldStyle;
            updateBorderColour();
        }
    }

    @Override
    protected void sizeChanged() {
        super.sizeChanged();
        updateBorder();
    }

    private void updateBorder() {
        dispose();
        if (borderColour != null) {
            borderTexture = TextureUtils.getTexture((int)getWidth(), (int)getHeight(), borderColour);
            borderDrawable = TextureUtils.getDrawable(borderTexture);
        }
    }

    private void updateBorderColour() {
        if (style != null) {
            this.borderColour = inFocus ? style.borderColourFocused : style.borderColour;
            updateBorder();
        }
    }

    private class FocusObserver extends FocusListener
    {
        @Override
        public void keyboardFocusChanged(FocusEvent event, Actor actor, boolean focused) {
            inFocus = focused;
            updateBorderColour();
        }
    }
}
