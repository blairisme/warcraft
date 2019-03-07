/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.assets;

import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

public class FontLoaderParameters extends AssetLoaderParameters<BitmapFont>
{
    private FreeTypeFontParameter fontStyle;

    public FontLoaderParameters(FreeTypeFontParameter fontStyle) {
        this.fontStyle = fontStyle;
    }

    public FreeTypeFontParameter getFontStyle() {
        return fontStyle;
    }
}
