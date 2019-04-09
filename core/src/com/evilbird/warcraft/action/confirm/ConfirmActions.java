/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.confirm;

import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.common.serialization.SerializedType;

/**
 * Defines options of specifying confirmation effect varieties.
 *
 * @author Blair Butterworth
 */
@SerializedType("ConfirmActions")
public enum ConfirmActions implements ActionIdentifier
{
    ConfirmLocation,
    ConfirmTarget,
}
