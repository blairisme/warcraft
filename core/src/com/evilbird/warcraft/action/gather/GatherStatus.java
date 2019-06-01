/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.gather;

/**
 * Specifies the set of options for gathering state.
 *
 * @author Blair Butterworth
 */
public enum GatherStatus
{
    ObtainStarted,
    ObtainComplete,
    DepositStarted,
    DepositComplete,
    Cancelled
}
