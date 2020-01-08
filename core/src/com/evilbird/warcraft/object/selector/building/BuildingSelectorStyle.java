/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
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
