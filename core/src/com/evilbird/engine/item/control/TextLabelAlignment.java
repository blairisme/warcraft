package com.evilbird.engine.item.control;

import com.badlogic.gdx.utils.Align;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public enum TextLabelAlignment
{
    Left(Align.left),
    Center(Align.center);

    private int gdxEquivalent;

    private TextLabelAlignment(int gdxEquivalent)
    {
        this.gdxEquivalent = gdxEquivalent;
    }

    public int getGdxEquivalent()
    {
        return gdxEquivalent;
    }
}
