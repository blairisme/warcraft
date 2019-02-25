/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.action;

import com.badlogic.gdx.utils.Pool.Poolable;
import com.evilbird.engine.common.lang.Identifiable;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.item.Item;

/**
 * Instances of this class represent a self contained "bundle" of behaviour
 * that modify the game state is a meaningful manner.
 *
 * @author Blair Butterworth
 */
public interface Action extends Identifiable, Poolable
{
    /**
     * Updates the Action based on time. Typically this is called each frame by
     * {@link Item#update(float)}.
     *
     * @param delta the number of seconds since the last invocation.
     * @return      <code>true</code> if the action is done.
     */
    boolean act(float delta);

    /**
     * Stops the Action.
     */
    void cancel();

    /**
     * Sets the state of the action so it can be run again.
     */
    void restart();

    Item getItem();

    Item getTarget();

    UserInput getCause();

    Throwable getError();

    boolean hasError();

    void setCause(UserInput input);

    void setItem(Item item);

    void setTarget(Item target);

    void setError(Throwable error);

    void setIdentifier(Identifier identifier);
}
