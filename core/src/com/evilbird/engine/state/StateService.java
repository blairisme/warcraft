/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.state;

import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.common.lang.Persisted;

import java.util.List;

public interface StateService extends Persisted
{
    List<Identifier> list(Identifier type);

    State get(Identifier identifier);

    void remove(Identifier identifier);

    void set(Identifier identifier, State state);
}
