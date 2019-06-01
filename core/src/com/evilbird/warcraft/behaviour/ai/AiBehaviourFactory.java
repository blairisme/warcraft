/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
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
