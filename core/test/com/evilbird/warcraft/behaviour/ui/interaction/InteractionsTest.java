/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.behaviour.ui.interaction;

import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.action.ActionFactory;
import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.common.lang.TextIdentifier;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.device.UserInputType;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemType;
import com.evilbird.test.data.item.TestItems;
import com.evilbird.test.utils.MockProvider;
import com.evilbird.warcraft.action.camera.CameraActions;
import com.evilbird.warcraft.item.data.DataType;
import com.evilbird.warcraft.item.layer.LayerType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.inject.Provider;
import java.util.Collection;
import java.util.Collections;

import static com.evilbird.engine.device.UserInputType.Action;
import static com.evilbird.warcraft.action.attack.AttackActions.AttackMelee;
import static com.evilbird.warcraft.action.confirm.ConfirmActions.ConfirmTarget;
import static com.evilbird.warcraft.action.gather.GatherActions.GatherGold;
import static com.evilbird.warcraft.item.unit.UnitType.*;
import static java.util.Arrays.asList;

/**
 * Instances of this unit test validate the {@link Interactions} class.
 *
 * @author Blair Butterworth
 */
public class InteractionsTest
{
    private Interactions interactions;
    private InteractionContainer container;
    private ActionFactory actions;
    private Provider<InteractionDefinition> definitions;

    @Before
    public void setup() {
        actions = Mockito.mock(ActionFactory.class);
        definitions = new MockProvider<>(() -> new InteractionDefinition(actions));
        container = new InteractionContainer(definitions);
        interactions = new Interactions(container);
    }

    @Test
    public void getInteractionsTest() {
        assertInteractions(asList(AttackMelee, ConfirmTarget), Action, Grunt, Footman);
        assertInteraction(CameraActions.Pan, UserInputType.Drag, DataType.Camera, null);
    }

    private void assertInteraction(ActionIdentifier action, UserInputType input, ItemType target, ItemType selected) {
        UserInput userInput = new UserInput(input, new Vector2(1, 2), 1);
        Item targetItem = TestItems.newItem(new TextIdentifier("test-target"), target);
        Item selectedItem = selected != null ? TestItems.newItem(new TextIdentifier("test-selected"), selected) : null;

        InteractionDefinition result = (InteractionDefinition)interactions.getInteraction(userInput, targetItem, selectedItem);
        Assert.assertNotNull(result);

        Collection<ActionIdentifier> actual = result.getActions();
        Assert.assertEquals(1, actual.size());
        Assert.assertTrue(actual.contains(action));
    }

    private void assertInteractions(Collection<ActionIdentifier> expected, UserInputType input, ItemType target, ItemType selected) {
        UserInput userInput = new UserInput(input, new Vector2(1, 2), 1);
        Item targetItem = TestItems.newItem(new TextIdentifier("test-target"), target);
        Item selectedItem = TestItems.newItem(new TextIdentifier("test-selected"), selected);

        InteractionDefinition result = (InteractionDefinition)interactions.getInteraction(userInput, targetItem, selectedItem);
        Collection<ActionIdentifier> actual = result != null ? result.getActions() : Collections.emptyList();

        Assert.assertEquals(expected.size(), actual.size());
        Assert.assertTrue(actual.containsAll(expected));
    }
}