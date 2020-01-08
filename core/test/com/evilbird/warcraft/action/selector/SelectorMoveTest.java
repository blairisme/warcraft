/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.selector;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.device.UserInputType;
import com.evilbird.engine.object.GameObject;
import com.evilbird.test.data.item.TestPlaceholders;
import com.evilbird.test.testcase.GameTestCase;
import com.evilbird.test.verifier.EqualityVerifier;
import com.evilbird.warcraft.object.selector.building.BuildingSelector;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Instances of this unit test validate the {@link SelectorMove} class.
 *
 * @author Blair Butterworth
 */
public class SelectorMoveTest extends GameTestCase
{
    private SelectorMove action;
    private BuildingSelector selector;
    private UserInput userInput;

    @Before
    public void setup() {
        selector = TestPlaceholders.newTestPlaceholder("repositionaction");
        action = new SelectorMove();
        action.setSubject(selector);
        action.setCause(userInput);
    }

    @Test
    public void actTest() {
        selector.setPosition(0, 0);
        selector.setSize(32, 32);

        userInput = userInput(40, 40);
        action.setCause(userInput);

        action.act(1);
        Assert.assertEquals(new Vector2(32, 32), selector.getPosition());

        userInput = userInput(70, 70);
        action.setCause(userInput);

        action.act(1);
        Assert.assertEquals(new Vector2(64, 64), selector.getPosition());
    }

    @Test
    public void equalsTest() {
        EqualityVerifier.forClass(SelectorMove.class)
            .withMockedTransientFields(GameObject.class)
            .withMockedType(Pool.class)
            .excludeTransientFields()
            .verify();
    }

    private UserInput userInput(int x, int y) {
        return new UserInput(UserInputType.Drag, new Vector2(x, Gdx.graphics.getHeight() - y + 1), 1);
    }
}