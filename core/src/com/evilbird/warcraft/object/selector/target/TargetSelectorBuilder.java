/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.selector.target;

import com.badlogic.gdx.scenes.scene2d.Touchable;

/**
 * Creates new {@link TargetSelector} objects, whose visual and audible
 * presentation are defined by the given {@link TargetSelectorAssets}.
 *
 * @author Blair Butterworth
 */
public class TargetSelectorBuilder
{
    private TargetSelectorAssets assets;

    public TargetSelectorBuilder(TargetSelectorAssets assets) {
        this.assets = assets;
    }

    public TargetSelector build() {
        TargetSelector result = new TargetSelector();
        result.setSize(128, 128);
        result.setTouchable(Touchable.enabled);
        result.setVisible(true);
        result.setTexture(assets.getRuneSelect());
        return result;
    }
}
