/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.layer.fog;

import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.evilbird.engine.object.GameObject;
import com.evilbird.warcraft.item.layer.LayerGroup;
import com.evilbird.warcraft.item.layer.LayerGroupCell;
import com.evilbird.warcraft.item.layer.LayerType;

/**
 * Represents one cell in the fog layer, decorating it as an {@link GameObject} and
 * storing whether or not it has been revealed.
 *
 * @author Blair Butterworth
 */
public class FogCell extends LayerGroupCell
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
