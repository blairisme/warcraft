/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.control;

import com.badlogic.gdx.utils.Align;

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
