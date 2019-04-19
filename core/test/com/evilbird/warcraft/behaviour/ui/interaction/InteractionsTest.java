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
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.inject.Provider;
import java.util.Collection;
import java.util.stream.Collectors;

import static com.evilbird.engine.device.UserInputType.Action;
import static com.evilbird.warcraft.action.attack.AttackActions.AttackMelee;
import static com.evilbird.warcraft.action.confirm.ConfirmActions.ConfirmTarget;
import static com.evilbird.warcraft.action.construct.ConstructActions.ConstructBarracks;
import static com.evilbird.warcraft.item.placeholder.PlaceholderType.BarracksPlaceholder;
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
        assertInteraction(ConstructBarracks, Action, BarracksPlaceholder, Peasant);
    }

    private void assertInteraction(ActionIdentifier action, UserInputType input, ItemType target, ItemType selected) {
        UserInput userInput = new UserInput(input, new Vector2(1, 2), 1);
        Item targetItem = TestItems.newItem(new TextIdentifier("test-target"), target);
        Item selectedItem = TestItems.newItem(new TextIdentifier("test-selected"), selected);

        Collection<Interaction> results = interactions.getInteractions(userInput, targetItem, selectedItem);
        Assert.assertEquals(1, results.size());

        InteractionDefinition result = (InteractionDefinition)results.iterator().next();
        Assert.assertEquals(action, result.getAction());
    }

    private void assertInteractions(Collection<ActionIdentifier> actions, UserInputType input, ItemType target, ItemType selected) {
        UserInput userInput = new UserInput(input, new Vector2(1, 2), 1);
        Item targetItem = TestItems.newItem(new TextIdentifier("test-target"), target);
        Item selectedItem = TestItems.newItem(new TextIdentifier("test-selected"), selected);

        Collection<Interaction> results = interactions.getInteractions(userInput, targetItem, selectedItem);
        Collection<ActionIdentifier> actual = results.stream().map(interaction -> ((InteractionDefinition)interaction).getAction()).collect(Collectors.toList());

        Assert.assertEquals(actions.size(), results.size());
        Assert.assertTrue(actual.containsAll(actions));
    }
}