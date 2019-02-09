/*
 * Blair Butterworth (c) 2018
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action;

import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.action.framework.Action;

/**
 * Implementors of this interface provide a method that creates an action given
 * a type identifier and contextual information.
 *
 * @author Blair Butterworth
 */
public interface ActionProvider
{
    Action get(ActionIdentifier action);
}
