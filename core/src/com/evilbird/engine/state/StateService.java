/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.state;

import com.evilbird.engine.common.lang.Persisted;

import java.util.List;

public interface StateService extends Persisted
{
    List<StateIdentifier> list(StateCategory type);

    State get(StateIdentifier identifier);

    void remove(StateIdentifier identifier);

    void set(StateIdentifier identifier, State state);
}
