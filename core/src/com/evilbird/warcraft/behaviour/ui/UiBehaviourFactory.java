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

/**
 * Instances of this factory create {@link UiBehaviour}, which modify the game
 * state in response to user interaction and updates to the game state that
 * should be presented to the user.
 *
 * @author Blair Butterworth
 */
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
