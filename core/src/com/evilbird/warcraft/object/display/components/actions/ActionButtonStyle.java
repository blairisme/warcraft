/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.display.components.actions;

import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.evilbird.warcraft.object.display.components.common.IconSet;

/**
 * Defines the visual and auditory presentation of an {@link ActionButton}.
 *
 * @author Blair Butterworth
 */
public class ActionButtonStyle
{
    public Drawable background;
    public IconSet icons;
    public IconSet disabledIcons;
}
