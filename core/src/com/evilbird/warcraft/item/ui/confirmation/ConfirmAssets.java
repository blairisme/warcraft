/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.ui.confirmation;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.evilbird.engine.common.assets.AssetBundle;

/**
 * Provides access to the assets that are required to display a confirmation
 * user interface effect.
 *
 * @author Blair Butterworth
 */
public class ConfirmAssets extends AssetBundle
{
    public ConfirmAssets(AssetManager assetManager) {
        super(assetManager);
        register("green-cross", "data/textures/common/ui/green_cross.png");
        register("red-cross", "data/textures/common/ui/red_cross.png");
    }

    public Texture getGreenCross() {
        return getTexture("green-cross");
    }

    public Texture getRedCross() {
        return getTexture("red-cross");
    }
}
