/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.layer.fog;

import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.evilbird.engine.object.GameObject;
import com.evilbird.warcraft.object.layer.LayerCell;
import com.evilbird.warcraft.object.layer.LayerGroup;
import com.evilbird.warcraft.object.layer.LayerType;

/**
 * Represents one cell in the fog layer, decorating it as an {@link GameObject} and
 * storing whether or not it has been revealed.
 *
 * @author Blair Butterworth
 */
public class FogCell extends LayerCell
{
    private static final float FULL_VALUE = 1;
    private static final float EMPTY_VALUE = 0;

    public FogCell(GridPoint2 location, boolean revealed) {
        this(location, revealed ? EMPTY_VALUE : FULL_VALUE);
    }

    public FogCell(GridPoint2 location, float value) {
        super(location, value);
        setType(LayerType.OpaqueFogSection);
    }

    public boolean isRevealed() {
        return value == EMPTY_VALUE;
    }

    public void reveal() {
        setValue(EMPTY_VALUE);
    }

    @Override
    public void setEmpty() {
        LayerGroup group = (LayerGroup)getParent();
        if (group != null) {
            group.setEmptyTexture(location);
            setTouchable(Touchable.disabled);
        }
    }
}
