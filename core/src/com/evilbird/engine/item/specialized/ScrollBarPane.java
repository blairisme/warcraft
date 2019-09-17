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
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.FocusListener;
import com.badlogic.gdx.utils.Disposable;
import com.evilbird.engine.common.graphics.TextureUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;

import static org.apache.commons.lang3.reflect.FieldUtils.getDeclaredField;

/**
 * <p>
 * Represents a user interface control that displays a given control, sized to
 * its preferred size, where if the controls's preferred width or height is
 * greater than the size of this scroll pane scrollbars appear allowing the
 * controls content to be viewed.
 * </p>
 * <p>
 * This control also overrides the default {@link ScrollPane} behaviour,
 * showing the scroll knob using its preferred size, rather than the default
 * behaviour which stretches it.
 * </p>
 *
 * @author Blair Butterworth
 */
public class ScrollBarPane extends ScrollPane implements Disposable
{
    private static final Logger logger = LoggerFactory.getLogger(ScrollBarPane.class);

    private Skin skin;
    private ScrollBarPaneStyle style;
    private boolean inFocus;
    private Color borderColour;
    private Drawable borderDrawable;
    private Texture borderTexture;
    private Rectangle horizontalKnobBounds;
    private Rectangle verticalKnobBounds;

    public ScrollBarPane(Actor widget, Skin skin) {
        super(widget, skin);
        this.skin = skin;
        this.horizontalKnobBounds = getPrivateField("hKnobBounds");
        this.verticalKnobBounds = getPrivateField("vKnobBounds");
        setStyle(skin.get("default", ScrollBarPaneStyle.class));
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

    @Override
    public void layout() {
        super.layout();
        ScrollPaneStyle style = getStyle();
        setHorizontalKnobSize(style);
        setVerticalKnobSize(style);
    }

    public void setStyle(String name) {
        setStyle(skin.get(name, ScrollBarPaneStyle.class));
    }

    public void setStyle(ScrollPaneStyle style) {
        super.setStyle(style);
        updateStyle((ScrollBarPaneStyle)style);
    }



    private void setHorizontalKnobSize(ScrollPaneStyle style) {
        if (style.hScrollKnob != null) {
            Drawable drawable = style.hScrollKnob;
            horizontalKnobBounds.height = drawable.getMinHeight();
            horizontalKnobBounds.width = drawable.getMinWidth();
        }
    }

    private void setVerticalKnobSize(ScrollPaneStyle style) {
        if (style.vScrollKnob != null) {
            Drawable drawable = style.vScrollKnob;
            verticalKnobBounds.height = drawable.getMinHeight();
            verticalKnobBounds.width = drawable.getMinWidth();
        }
    }

    private Rectangle getPrivateField(String name) {
        try {
            Field field = getDeclaredField(ScrollPane.class, name, true);
            return (Rectangle)field.get(this);
        }
        catch (IllegalArgumentException | IllegalAccessException e) {
            logger.error("Unable to access " + name, e);
            return new Rectangle();
        }
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

    private void updateStyle(ScrollBarPaneStyle style) {
        this.style = style;
        updateBorderColour();
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