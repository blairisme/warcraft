/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.selector.building;

import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

/**
 * Defines the visual and auditory presentation of a {@link BuildingSelector}.
 *
 * @author Blair Butterworth
 */
public class BuildingSelectorStyle
{
    public transient Drawable building;
    public transient Drawable allowed;
    public transient Drawable prohibited;
}
