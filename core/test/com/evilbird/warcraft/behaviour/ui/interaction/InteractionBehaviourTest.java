/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.behaviour.ui.interaction;

import com.evilbird.engine.events.EventQueue;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Instances of this unit test validate the {@link InteractionBehaviour} class.
 *
 * @author Blair Butterworth
 */
public class InteractionBehaviourTest
{
    private EventQueue events;
    private Interactions interactions;
    private InteractionBehaviour behaviour;

    @Before
    public void setup() {
        events = Mockito.mock(EventQueue.class);
        interactions = Mockito.mock(Interactions.class);
        behaviour = new InteractionBehaviour(interactions, events);
    }

    @Test
    public void updateTest() {

    }
}