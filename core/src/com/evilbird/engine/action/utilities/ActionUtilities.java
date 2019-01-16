/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.action.utilities;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.engine.common.lang.Identifiable;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.item.Item;

/**
 * Provides common utility functions for working with {@link Action Actions}.
 *
 * @author Blair Butterworth
 */
public class ActionUtilities
{
    private ActionUtilities(){
        throw new UnsupportedOperationException();
    }

    public static boolean hasIdentifiedAction(Item item, Identifier identifier) {
        return hasIdentifiedAction(item.getActions(), identifier);
    }

    public static boolean hasIdentifiedAction(Iterable<Action> actions, Identifier identifier) {
        for (Action action: actions) {
            if (action instanceof Identifiable) {
                Identifiable identifiable = (Identifiable)action;
                if (identifiable.getIdentifier() == identifier) {
                    return true;
                }
            }
        }
        return false;
    }
}
