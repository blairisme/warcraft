/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.unit;

import java.util.function.Supplier;

import static java.lang.Integer.MAX_VALUE;

/**
 * Defines categories for specifying the rendering order of units.
 *
 * @author Blair Butterworth
 */
public enum UnitZIndex implements Supplier<Integer>
{
    ResourceIndex    (0),
    BuildingIndex    (0),
    UnitIndex        (MAX_VALUE / 3),
    ConjuredIndex    (MAX_VALUE / 2),
    FlyingIndex      (MAX_VALUE);

    private int index;

    UnitZIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public Integer get() {
        return index;
    }
}
