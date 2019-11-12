/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit;

import com.badlogic.gdx.graphics.Texture;
import com.evilbird.engine.common.graphics.Renderable;
import com.evilbird.engine.object.AnimatedObjectStyle;

import java.util.Collections;
import java.util.Map;

import static com.evilbird.engine.common.graphics.EmptyRenderable.BlankRenderable;

/**
 * Defines the visual and auditory presentation of a {@link Unit}.
 *
 * @author Blair Butterworth
 */
public class UnitStyle extends AnimatedObjectStyle
{
    /**
     * The {@link Renderable} drawn below a {@link Unit} when its highlighted.
     */
    public Renderable highlight;

    /**
     * The {@link Renderable} drawn below a {@link Unit} when its selected.
     */
    public Renderable selection;

    /**
     * A map of {@link Texture Textures} used to associate highlight masks with
     * {@link Unit} {@code Textures}.
     */
    public Map<Texture, Texture> masks;

    /**
     * Creates a new instance of this class with no animations, sounds or
     * selection texture.
     */
    public UnitStyle() {
        super();
        selection = BlankRenderable;
        highlight = BlankRenderable;
        masks = Collections.emptyMap();
    }

    /**
     * Creates a new instance of this class with given another style whose
     * attributes will be copied into the new style. Subsequent changes to the
     * new style will not be reflected in the style it was copied from.
     *
     * @param style a {@link UnitStyle} whose style attributes will be
     *              copied.
     *
     * @throws NullPointerException thrown if the given style is {@code null}.
     */
    public UnitStyle(UnitStyle style) {
        super(style);
        highlight = style.highlight;
        selection = style.selection;
        masks = style.masks;
    }
}
