/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.action.common;

import com.evilbird.engine.action.Action;

/**
 * Defines option for specifying the recipient of an {@link Action Actions}
 * operation.
 *
 * @author Blair Butterworth
 */
public enum ActionRecipient
{
    Subject,
    Target,
    Parent,
    Player
}
