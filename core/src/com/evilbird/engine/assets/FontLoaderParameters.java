/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.assets;

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
