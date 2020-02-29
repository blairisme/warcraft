/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.camera;

import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.action.Action;
import com.evilbird.engine.device.DeviceDisplay;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectContainer;
import com.evilbird.test.data.item.TestCombatants;
import com.evilbird.test.testcase.ActionTestCase;
import com.evilbird.warcraft.object.data.camera.Camera;
import com.evilbird.warcraft.object.data.camera.CameraType;
import com.evilbird.warcraft.object.unit.combatant.Combatant;
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
    protected GameObject newItem() {
        Combatant combatant = TestCombatants.newTestCombatant("item");

        camera = new Camera(Mockito.mock(DeviceDisplay.class));
        camera.setType(CameraType.Camera);

        GameObjectContainer root = combatant.getRoot();
        root.addObject(camera);

        return combatant;
    }

    @Test
    public void actTest() {
        gameObject.setPosition(100, 100);
        gameObject.setSize(32, 32);

        assertTrue(action.run(1));
        assertEquals(new Vector2(84, 84), camera.getPosition());
    }
}