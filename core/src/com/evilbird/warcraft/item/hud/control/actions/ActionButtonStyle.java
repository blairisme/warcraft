/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.hud.control.actions;

import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

import java.util.Map;

public class ActionButtonStyle
{
    public Drawable background;
    public Map<ActionButtonType, Drawable> icons;
    public Map<ActionButtonType, Drawable> disabledIcons;
}
