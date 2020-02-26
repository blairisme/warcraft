/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ui.objective;

import com.evilbird.warcraft.behaviour.WarcraftBehaviourType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.inject.Provider;

/**
 * Instances of this unit test validate the {@link ObjectiveBehaviourFactory} class.
 *
 * @author Blair Butterworth
 */
public class ObjectiveBehaviourFactoryTest
{
    private ObjectiveBehaviourFactory factory;

    @Before
    public void setup() {
        Provider<ObjectiveBehaviour> provider = () -> Mockito.mock(ObjectiveBehaviour.class);
        factory = new ObjectiveBehaviourFactory(provider);
    }

    @Test
    public void getTest() {
        for (WarcraftBehaviourType type: WarcraftBehaviourType.values()) {
            ObjectiveBehaviour behaviour = (ObjectiveBehaviour)factory.get(type);
            Assert.assertNotNull(type.toString(), behaviour);
            Mockito.verify(behaviour).setWinCondition(Mockito.any());
            Mockito.verify(behaviour).setLoseCondition(Mockito.any());
        }
    }
}