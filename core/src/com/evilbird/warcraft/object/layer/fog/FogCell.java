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
import com.evilbird.warcraft.object.layer.LayerGroupCell;
import com.evilbird.warcraft.object.layer.LayerGroupStyle;
import com.evilbird.warcraft.object.layer.LayerType;

/**
 * Represents one cell in the fog layer, decorating it as an {@link GameObject} and
 * storing whether or not it has been revealed.
 *
 * @author Blair Butterworth
 */
public class FogCell extends LayerGroupCell
{
    protected static final transient float FULL_VALUE = 1;
    protected static final transient float EMPTY_VALUE = 0;
    protected static final transient float DEFAULT_VALUE = FULL_VALUE;

    public FogCell(LayerGroupStyle style, GridPoint2 location) {
        this(style, location, DEFAULT_VALUE);
    }

    public FogCell(LayerGroupStyle style, GridPoint2 location, float value) {
        super(style, location, value);
        setType(LayerType.OpaqueFogSection);
    }

    public void conceal() {
        setValue(FULL_VALUE);
    }

    public boolean isRevealed() {
        return value == EMPTY_VALUE;
    }

    public void reveal() {
        setValue(EMPTY_VALUE);
    }

    @Override
    public void showEmpty() {
        super.showEmpty();
        setTouchable(Touchable.disabled);
    }

    @Override
    public void showFull() {
        super.showFull();
        setTouchable(Touchable.enabled);
    }
}
