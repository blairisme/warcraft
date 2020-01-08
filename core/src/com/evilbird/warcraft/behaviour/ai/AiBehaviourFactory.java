/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ai;

import com.evilbird.engine.behaviour.Behaviour;
import com.evilbird.engine.common.inject.IdentifiedProvider;
import com.evilbird.engine.common.lang.Identifier;
import org.apache.commons.lang3.Validate;

import javax.inject.Inject;
import javax.inject.Provider;

/**
 * Instances of this factory create {@link AiBehaviour} objects, whose
 * operation will differ based on the given {@link AiBehaviours} value.
 *
 * @author Blair Butterworth
 */
public class AiBehaviourFactory implements IdentifiedProvider<Behaviour>
{
    private Provider<AiBehaviour> factory;

    @Inject
    public AiBehaviourFactory(Provider<AiBehaviour> factory) {
        this.factory = factory;
    }

    @Override
    public Behaviour get(Identifier identifier) {
        Validate.isInstanceOf(AiBehaviours.class, identifier);
        return factory.get();
    }
}
