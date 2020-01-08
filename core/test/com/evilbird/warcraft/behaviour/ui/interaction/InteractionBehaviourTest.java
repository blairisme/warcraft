/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
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