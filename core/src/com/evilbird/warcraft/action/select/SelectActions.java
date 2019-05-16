/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.select;

import com.evilbird.engine.action.ActionIdentifier;

/**
 * Defines options of specifying selection action varieties.
 *
 * @author Blair Butterworth
 */
public enum SelectActions implements ActionIdentifier
{
    SelectToggle,
    SelectBoxBegin,
    SelectBoxResize,
    SelectBoxEnd
}
