/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.unit.combatant.flying;

import com.evilbird.engine.common.graphics.Renderable;
import com.evilbird.warcraft.object.unit.Unit;
import com.evilbird.warcraft.object.unit.UnitStyle;

import static com.evilbird.engine.common.graphics.EmptyRenderable.BlankRenderable;

/**
 * Defines the visual and auditory presentation of a {@link Unit}.
 *
 * @author Blair Butterworth
 */
public class FlyingUnitStyle extends UnitStyle
{
    /**
     * A {@link Renderable} drawn below a {@link FlyingUnit} representing its
     * shadow.
     */
    public Renderable shadow;

    /**
     * Creates a new instance of this class with no animations, sounds or
     * selection texture.
     */
    public FlyingUnitStyle() {
        super();
        shadow = BlankRenderable;
    }

    /**
     * Creates a new instance of this class given another style whose
     * attributes will be copied into the new style. Subsequent changes to the
     * new style will not be reflected in the style it was copied from.
     *
     * @param style a {@link FlyingUnitStyle} whose style attributes will be
     *              copied.
     *
     * @throws NullPointerException thrown if the given style is {@code null}.
     */
    public FlyingUnitStyle(FlyingUnitStyle style) {
        super(style);
        shadow = style.shadow;
    }

    public FlyingUnitStyle(UnitStyle style) {
        super(style);
        shadow = BlankRenderable;
    }
}
