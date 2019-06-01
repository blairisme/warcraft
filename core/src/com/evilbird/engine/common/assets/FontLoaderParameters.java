/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.assets;

import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

/**
 * Instances of this class provide parameters that customize the execution of
 * the {@link FontLoader} class. Specifically this class allows the size of the
 * font to specified when loaded.
 *
 * @author Blair Butterworths
 */
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
