/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ui;

import com.evilbird.engine.behaviour.BehaviourElement;
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
public class UiBehaviourFactory implements Provider<BehaviourElement>
{
    private InjectedPool<UiBehaviour> pool;

    @Inject
    public UiBehaviourFactory(InjectedPool<UiBehaviour> pool) {
        this.pool = pool;
    }

    @Override
    public BehaviourElement get() {
        return pool.obtain();
    }
}
