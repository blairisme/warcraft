/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ui.interaction;

import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.action.ActionFactory;
import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.common.lang.TextIdentifier;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.device.UserInputType;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectType;
import com.evilbird.test.data.item.TestItems;
import com.evilbird.test.utils.MockProvider;
import com.evilbird.warcraft.action.camera.CameraActions;
import com.evilbird.warcraft.object.data.camera.CameraType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.inject.Provider;
import java.util.Collection;
import java.util.Collections;

/**
 * Instances of this unit test validate the {@link Interactions} class.
 *
 * @author Blair Butterworth
 */
public class CameraInteractionsTest
{
    private CameraInteractions interactions;
    private ActionFactory actions;
    private Provider<InteractionDefinition> factory;

    @Before
    public void setup() {
        actions = Mockito.mock(ActionFactory.class);
        factory = new MockProvider<>(() -> new InteractionDefinition(actions));
        interactions = new CameraInteractions(factory);
    }

    @Test
    public void getInteractionsTest() {
        assertInteraction(CameraActions.Pan, UserInputType.Drag, CameraType.Camera, null);
    }

    private void assertInteraction(ActionIdentifier action, UserInputType input, GameObjectType target, GameObjectType selected) {
        UserInput userInput = new UserInput(input, new Vector2(1, 2), 1);
        GameObject targetGameObject = TestItems.newItem(new TextIdentifier("test-target"), target);
        GameObject selectedGameObject = selected != null ? TestItems.newItem(new TextIdentifier("test-selected"), selected) : null;

        InteractionDefinition result = (InteractionDefinition)interactions.getInteraction(userInput, targetGameObject, selectedGameObject);
        Assert.assertNotNull(result);

        Collection<ActionIdentifier> actual = result.getActions();
        Assert.assertEquals(1, actual.size());
        Assert.assertTrue(actual.contains(action));
    }

    private void assertInteractions(Collection<ActionIdentifier> expected, UserInputType input, GameObjectType target, GameObjectType selected) {
        UserInput userInput = new UserInput(input, new Vector2(1, 2), 1);
        GameObject targetGameObject = TestItems.newItem(new TextIdentifier("test-target"), target);
        GameObject selectedGameObject = TestItems.newItem(new TextIdentifier("test-selected"), selected);

        InteractionDefinition result = (InteractionDefinition)interactions.getInteraction(userInput, targetGameObject, selectedGameObject);
        Collection<ActionIdentifier> actual = result != null ? result.getActions() : Collections.emptyList();

        Assert.assertEquals(expected.size(), actual.size());
        Assert.assertTrue(actual.containsAll(expected));
    }
}