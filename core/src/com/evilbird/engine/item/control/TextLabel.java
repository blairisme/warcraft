package com.evilbird.engine.item.control;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.Align;
import com.evilbird.engine.item.Item;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class TextLabel extends Item
{
    private Label label;

    @Override
    protected Actor initializeDelegate()
    {
        label = new Label("", getDefaultStyle());
        label.setAlignment(Align.center);
        return label;
    }

    public void setColor(Color color)
    {
        label.setColor(color);
    }

    public void setText(String text)
    {
        label.setText(text);
        label.layout();
    }

    private LabelStyle getDefaultStyle()
    {
        Color labelColor = Color.WHITE;
        BitmapFont labelFont = new BitmapFont();
        return new LabelStyle(labelFont, labelColor);
    }
}
