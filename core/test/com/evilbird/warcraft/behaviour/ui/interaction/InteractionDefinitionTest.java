/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.behaviour.ui.interaction;

import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.ActionFactory;
import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.device.UserInputType;
import com.evilbird.engine.item.Item;
import com.evilbird.test.testcase.GameTestCase;
import com.evilbird.warcraft.action.attack.AttackActions;
import com.evilbird.warcraft.action.construct.ConstructActions;
import com.evilbird.warcraft.item.unit.combatant.Combatant;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static com.evilbird.engine.common.lang.GenericIdentifier.Unknown;
import static com.evilbird.engine.item.utility.ItemPredicates.withClazz;
import static com.evilbird.test.data.action.TestBasicActions.newBasicAction;
import static com.evilbird.test.data.item.TestBuildings.newTestBuilding;
import static com.evilbird.test.data.item.TestCombatants.newTestCombatant;
import static com.evilbird.warcraft.item.unit.UnitType.Farm;
import static com.evilbird.warcraft.item.unit.UnitType.Footman;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Instances of this unit test validate the {@link InteractionDefinition} class.
 *
 * @author Blair Butterworth
 */
public class InteractionDefinitionTest extends GameTestCase
{
    private ActionFactory factory;
    private InteractionDefinition definition;

    @Before
    public void setup() {
        factory = Mockito.mock(ActionFactory.class);
        definition = new InteractionDefinition(factory);
    }

    @Test
    public void targetTest() {
        definition.whenTarget(Footman);
        assertTrue(definition.applies(null, newTestCombatant(Unknown, Footman), null));
        assertFalse(definition.applies(null, newTestCombatant(Unknown, Farm), null));
    }

    @Test
    public void targetConditionTest() {
        definition.whenTarget(withClazz(Combatant.class));
        assertTrue(definition.applies(null, newTestCombatant("footman"), null));
        assertFalse(definition.applies(null, newTestBuilding("farm"), null));
    }

    @Test (expected = NullPointerException.class)
    public void targetNullTest() {
        definition.whenTarget((Identifier)null);
    }

    @Test
    public void selectedTest() {
        definition.whenSelected(Footman);
        assertTrue(definition.applies(null, null, newTestCombatant(Unknown, Footman)));
        assertFalse(definition.applies(null, null, newTestCombatant(Unknown, Farm)));
    }

    @Test (expected = NullPointerException.class)
    public void selectedNullTest() {
        definition.whenSelected((Identifier)null);
    }

    @Test
    public void selectedConditionTest() {
        definition.whenSelected(withClazz(Combatant.class));
        assertTrue(definition.applies(null, null, newTestCombatant("footman")));
        assertFalse(definition.applies(null, null, newTestBuilding("farm")));
    }

    @Test
    public void inputTest() {
        definition.forInput(UserInputType.Action);
        assertTrue(definition.applies(new UserInput(UserInputType.Action, Vector2.Zero, 1), null, null));
        assertFalse(definition.applies(new UserInput(UserInputType.Drag, Vector2.Zero, 1), null, null));
    }

    @Test (expected = NullPointerException.class)
    public void inputNullTest() {
        definition.forInput((UserInputType) null);
    }

    @Test
    public void inputConditionTest() {
        definition.forInput(input -> input.getPosition() == Vector2.Zero);
        assertTrue(definition.applies(new UserInput(UserInputType.Action, Vector2.Zero, 1), null, null));
        assertFalse(definition.applies(new UserInput(UserInputType.Drag, new Vector2(1, 1), 1), null, null));
    }

    @Test
    public void actionTest() {
        definition.withAction(AttackActions.Attack);
        definition.appliedTo(InteractionApplicability.Target);

        assertTrue(definition.applies(null, newTestItemWithAction(AttackActions.Attack), null));
        assertFalse(definition.applies(null, newTestItemWithAction(ConstructActions.ConstructBarracks), null));
    }

    @Test (expected = NullPointerException.class)
    public void actionNullTest() {
        definition.withAction((ActionIdentifier) null);
    }

    @Test
    public void actionConditionTest() {
        definition.withAction(action -> action.getIdentifier() == ConstructActions.ConstructBarracks);
        definition.appliedTo(InteractionApplicability.Target);

        assertTrue(definition.applies(null, newTestItemWithAction(ConstructActions.ConstructBarracks), null));
        assertFalse(definition.applies(null, newTestItemWithAction(AttackActions.Attack), null));
    }

    private Item newTestItemWithAction(ActionIdentifier actionIdentifier){
        Item result = newTestCombatant(Unknown, Footman);
        result.addAction(newBasicAction(actionIdentifier));
        return result;
    }

    @Test
    public void applyTest() {
        Action action = newBasicAction();
        Mockito.when(factory.newAction(ConstructActions.ConstructBarracks)).thenReturn(action);

        UserInput input = new UserInput(UserInputType.Action, Vector2.Zero, 1);
        Item target = newTestCombatant("footman");
        Item selected = newTestBuilding("farm");

        definition.forAction(ConstructActions.ConstructBarracks);
        definition.appliedTo(InteractionApplicability.Target);
        definition.appliedAs(InteractionDisplacement.Addition);
        definition.assignedTo(InteractionAssignment.Item);
        definition.apply(input, target, selected);

        assertTrue(target.getActions().contains(action));
    }
}