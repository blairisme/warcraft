/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.behaviour.ui.interaction;

import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.device.UserInputType;
import com.evilbird.engine.item.Item;
import com.evilbird.test.utils.MockProvider;
import com.evilbird.warcraft.action.move.MoveActions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.inject.Provider;

/**
 * Instances of this unit test validate the {@link InteractionContainer} class.
 *
 * @author Blair Butterworth
 */
public class InteractionContainerTest
{
    private InteractionContainer container;
    private Provider<InteractionDefinition> definitions;

    @Before
    public void setup() {
        definitions = new MockProvider<>(InteractionDefinition.class);
        container = new InteractionContainer(definitions);
    }

    @Test
    public void addActionTest() {
        InteractionDefinition result = container.addAction(MoveActions.MoveToItem);
        Assert.assertNotNull(result);
        Mockito.verify(result).forAction(MoveActions.MoveToItem);
        Mockito.verify(result).forInput(UserInputType.Action);
        Mockito.verify(result).assignedTo(InteractionAssignment.Item);
        Mockito.verify(result).appliedAs(InteractionDisplacement.Replacement);
    }

    @Test
    public void getInteractionsTest() {
        InteractionDefinition interaction1 = container.addAction(MoveActions.MoveToItem);
        InteractionDefinition interaction2 = container.addAction(MoveActions.MoveToLocation);
        InteractionDefinition interaction3 = container.addAction(MoveActions.MoveCancel);

        UserInput input = Mockito.mock(UserInput.class);
        Item target = Mockito.mock(Item.class);
        Item selected = Mockito.mock(Item.class);

        Mockito.when(interaction1.applies(input, target, selected)).thenReturn(false);
        Mockito.when(interaction2.applies(input, target, selected)).thenReturn(true);
        Mockito.when(interaction3.applies(input, target, selected)).thenReturn(false);

        Interaction expected = interaction2;
        Interaction actual = container.getInteraction(input, target, selected);
        Assert.assertEquals(expected, actual);
    }
}