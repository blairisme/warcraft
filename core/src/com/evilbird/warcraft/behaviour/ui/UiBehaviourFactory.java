/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.behaviour.ui;

import com.evilbird.engine.behaviour.Behaviour;
import com.evilbird.engine.common.inject.InjectedPool;

import javax.inject.Inject;
import javax.inject.Provider;

public class UiBehaviourFactory implements Provider<Behaviour>
{
    private InjectedPool<UiBehaviour> pool;

    @Inject
    public UiBehaviourFactory(InjectedPool<UiBehaviour> pool) {
        this.pool = pool;
    }

    @Override
    public Behaviour get() {
        return pool.obtain();
    }
}
