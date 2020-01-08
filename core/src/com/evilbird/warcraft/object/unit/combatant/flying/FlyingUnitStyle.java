/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.unit.combatant.flying;

import com.evilbird.engine.common.graphics.renderable.Renderable;
import com.evilbird.warcraft.object.unit.Unit;
import com.evilbird.warcraft.object.unit.UnitStyle;

import static com.evilbird.engine.common.graphics.renderable.EmptyRenderable.BlankRenderable;

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
