/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.selector.target;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.evilbird.engine.common.assets.AssetBundle;

/**
 * Provides access to the assets that are required to display target selectors.
 *
 * @author Blair Butterworth
 */
public class TargetSelectorAssets extends AssetBundle
{
    public TargetSelectorAssets(AssetManager assetManager) {
        super(assetManager);
        register("rune_select", "data/textures/common/menu/rune_select.png");;
    }

    public Drawable getRuneSelect() {
        return getDrawable("rune_select");
    }
}
