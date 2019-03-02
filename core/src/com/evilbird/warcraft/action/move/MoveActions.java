/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.move;

import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.common.serialization.SerializedType;

/**
 * Defines options of specifying movement action varieties.
 *
 * @author Blair Butterworth
 */
@SerializedType("MoveActions")
public enum MoveActions implements ActionIdentifier
{
    MoveToLocation,
    MoveToItem,
    MoveCancel
}
