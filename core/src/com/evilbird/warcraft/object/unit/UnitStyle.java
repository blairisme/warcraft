/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.unit;

import com.badlogic.gdx.graphics.Texture;
import com.evilbird.engine.common.graphics.renderable.Renderable;
import com.evilbird.engine.object.AnimatedObjectStyle;

import java.util.Collections;
import java.util.Map;

import static com.evilbird.engine.common.graphics.renderable.EmptyRenderable.BlankRenderable;

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
     * Creates a new instance of this class given another style whose
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
