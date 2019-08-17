/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
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