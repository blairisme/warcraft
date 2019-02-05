/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.action.framework;

import com.badlogic.gdx.utils.Pool.Poolable;
import com.evilbird.engine.common.lang.Identifiable;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.item.Item;

public interface Action extends Identifiable, Poolable
{
    boolean act(float delta);

    void cancel();

    void restart();

    Item getItem() ;

    Item getTarget();

    Throwable getError();

    boolean hasError();

    void setItem(Item item);

    void setTarget(Item target);

    void setError(Throwable error);

    void setIdentifier(Identifier identifier);
}
