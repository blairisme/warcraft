/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.scenario;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.inject.Provider;

/**
 * Instances of this unit test validate the {@link ScenarioBehaviourFactory} class.
 *
 * @author Blair Butterworth
 */
public class ScenarioBehaviourFactoryTest
{
    private ScenarioBehaviourFactory factory;

    @Before
    public void setup() {
        Provider<ScenarioBehaviour> provider = () -> Mockito.mock(ScenarioBehaviour.class);
        factory = new ScenarioBehaviourFactory(provider);
    }

    @Test
    public void getTest() {
        for (ScenarioBehaviours type: ScenarioBehaviours.values()) {
            ScenarioBehaviour behaviour = (ScenarioBehaviour)factory.get(type);
            Assert.assertNotNull(type.toString(), behaviour);
            Mockito.verify(behaviour).setWinCondition(Mockito.any());
            Mockito.verify(behaviour).setLoseCondition(Mockito.any());
        }
    }
}