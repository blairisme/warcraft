/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.unit.building;

import com.evilbird.engine.common.graphics.renderable.Renderable;
import com.evilbird.warcraft.object.unit.UnitStyle;

/**
 * Defines the visual and auditory presentation of a {@link Building}.
 *
 * @author Blair Butterworth
 */
public class BuildingStyle extends UnitStyle
{
    /**
     * A {@link Renderable} drawn on top of the {@link Building} when it has
     * been lightly damaged.
     */
    public Renderable lightDamage;

    /**
     * A {@link Renderable} drawn on top of the {@link Building} when it has
     * been heavily damaged.
     */
    public Renderable heavyDamage;

    /**
     * Creates a new instance of this class with no animations, sounds or
     * damage texture.
     */
    public BuildingStyle() {
        super();
        this.lightDamage = null;
        this.heavyDamage = null;
    }

    /**
     * Creates a new instance of this class given another style whose
     * attributes will be copied into the new style. Subsequent changes to the
     * new style will not be reflected in the style it was copied from.
     *
     * @param style a {@link BuildingStyle} whose style attributes will be
     *              copied.
     *
     * @throws NullPointerException thrown if the given style is {@code null}.
     */
    public BuildingStyle(BuildingStyle style) {
        super(style);
        this.lightDamage = style.lightDamage;
        this.heavyDamage = style.heavyDamage;
    }
}
