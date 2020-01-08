/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
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
        result.setZIndex(Integer.MAX_VALUE);
        return result;
    }
}
