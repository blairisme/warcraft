/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.selector.target;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.evilbird.engine.assets.AssetBundle;

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

    public Texture getRuneSelect() {
        return getTexture("rune_select");
    }
}
