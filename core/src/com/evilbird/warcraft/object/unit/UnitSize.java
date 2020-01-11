/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.unit;

import com.badlogic.gdx.math.GridPoint2;

/**
 * Defines unit assets sizes, usually referring to textures.
 *
 * @author Blair Butterworth
 */
public enum UnitSize
{
    ExtraSmall  (32, 32, "extra_small"),
    Small       (64, 64, "small"),
    SubMedium   (72, 72, "sub_medium"),
    Medium      (80, 80, "medium"),
    SuperMedium (88, 88, "super_medium"),
    Large       (96, 96, "large"),
    ExtraLarge  (128, 128, "extra_large");

    private GridPoint2 size;
    private String label;

    UnitSize(int width, int height, String label) {
        this.size = new GridPoint2(width, height);
        this.label = label;
    }

    /**
     * Returns the dimensions of the unit size, specified in pixels.
     */
    public GridPoint2 getDimensions() {
        return size;
    }

    /**
     * Returns a label describing the unit size.
     */
    public String getLabel() {
        return label;
    }
}
