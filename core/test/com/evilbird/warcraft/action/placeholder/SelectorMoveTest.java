/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.placeholder;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.device.UserInputType;
import com.evilbird.engine.item.Item;
import com.evilbird.test.data.item.TestPlaceholders;
import com.evilbird.test.testcase.GameTestCase;
import com.evilbird.test.verifier.EqualityVerifier;
import com.evilbird.warcraft.action.selector.SelectorMove;
import com.evilbird.warcraft.item.ui.placement.Placeholder;
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
    private Placeholder placeholder;
    private UserInput userInput;

    @Before
    public void setup() {
        placeholder = TestPlaceholders.newTestPlaceholder("repositionaction");
        action = new SelectorMove();
        action.setItem(placeholder);
        action.setCause(userInput);
    }

    @Test
    public void actTest() {
        placeholder.setPosition(0, 0);
        placeholder.setSize(32, 32);

        userInput = userInput(40, 40);
        action.setCause(userInput);

        action.act(1);
        Assert.assertEquals(new Vector2(32, 32), placeholder.getPosition());

        userInput = userInput(70, 70);
        action.setCause(userInput);

        action.act(1);
        Assert.assertEquals(new Vector2(64, 64), placeholder.getPosition());
    }

    @Test
    public void equalsTest() {
        EqualityVerifier.forClass(SelectorMove.class)
            .withMockedTransientFields(Item.class)
            .excludeTransientFields()
            .verify();
    }

    private UserInput userInput(int x, int y) {
        return new UserInput(UserInputType.Drag, new Vector2(x, Gdx.graphics.getHeight() - y + 1), 1);
    }
}