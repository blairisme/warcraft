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
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

/**
 * Defines the visual and auditory presentation of a {@link Unit}.
 *
 * @author Blair Butterworth
 */
public class UnitStyle
{
    /**
     * The graphic used to represent the {@link Unit} in the selection panel.
     */
    public Drawable icon;

    /**
     * The texture drawn below a {@link Unit} when its selected.
     */
    public Texture selection;
}
