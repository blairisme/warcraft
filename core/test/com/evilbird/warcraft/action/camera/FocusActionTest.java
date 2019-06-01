/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.camera;

import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.action.Action;
import com.evilbird.engine.device.DeviceDisplay;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.test.data.item.TestCombatants;
import com.evilbird.test.testcase.ActionTestCase;
import com.evilbird.warcraft.item.data.DataType;
import com.evilbird.warcraft.item.data.camera.Camera;
import com.evilbird.warcraft.item.unit.combatant.Combatant;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Instances of this unit test validate the {@link FocusAction} class.
 *
 * @author Blair Butterworth
 */
public class FocusActionTest extends ActionTestCase
{
    private Camera camera;

    @Override
    protected Action newAction() {
        return new FocusAction();
    }

    @Override
    protected Enum newIdentifier() {
        return CameraActions.Focus;
    }

    @Override
    protected Item newItem() {
        Combatant combatant = TestCombatants.newTestCombatant("item");

        camera = new Camera(Mockito.mock(DeviceDisplay.class));
        camera.setType(DataType.Camera);

        ItemRoot root = combatant.getRoot();
        root.addItem(camera);

        return combatant;
    }

    @Test
    public void actTest() {
        item.setPosition(100, 100);
        item.setSize(32, 32);

        assertTrue(action.act(1));
        assertEquals(new Vector2(84, 84), camera.getPosition());
    }
}