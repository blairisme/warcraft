/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.select;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.evilbird.engine.action.Action;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.device.UserInputType;
import com.evilbird.engine.events.EventQueue;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.test.testcase.ActionTestCase;
import com.evilbird.warcraft.action.selection.SelectActions;
import com.evilbird.warcraft.action.selection.SelectArea;
import com.evilbird.warcraft.item.unit.Unit;
import org.junit.Test;
import org.mockito.Mockito;

import static com.evilbird.engine.item.utility.ItemPredicates.withType;
import static com.evilbird.warcraft.item.ui.selection.SelectionType.SelectionBox;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Instances of this unit test validate the {@link com.evilbird.warcraft.action.selection.SelectArea} class.
 *
 * @author Blair Butterworth
 */
public class SelectAreaTest extends ActionTestCase
{
    @Override
    protected Action newAction() {
        com.evilbird.warcraft.action.selection.SelectArea action = new SelectArea(Mockito.mock(EventQueue.class));
        action.setIdentifier(com.evilbird.warcraft.action.selection.SelectActions.SelectBoxBegin);
        return action;
    }

    @Override
    protected Enum newIdentifier() {
        return com.evilbird.warcraft.action.selection.SelectActions.SelectBoxBegin;
    }

    @Test
    public void beginTest() {
        Item box = getSelectionBox();
        assertNull(box);

        action.setIdentifier(com.evilbird.warcraft.action.selection.SelectActions.SelectBoxBegin);
        assertTrue(action.act(1));

        box = getSelectionBox();
        assertNotNull(box);
    }

    private int toScreen(int position) {
        return Gdx.graphics.getHeight() - 1 - position;
    }

    @Test
    public void updateTest() {
        Vector2 input1 = new Vector2(20, toScreen(20));
        Vector2 input2 = new Vector2(50, toScreen(50));

        action.setIdentifier(com.evilbird.warcraft.action.selection.SelectActions.SelectBoxResize);
        action.setCause(new UserInput(UserInputType.PressDrag, input2, input1, 1));

        assertTrue(action.act(1));
        Item box = getSelectionBox();

        assertEquals(new Vector2(20, 20), box.getPosition());
        assertEquals(new Vector2(30, 30), box.getSize());
    }

    @Test
    public void removeTest() {
        Unit unit = (Unit)item;
        unit.setPosition(30, 30);
        unit.setSize(10, 10);
        unit.setSelected(false);
        unit.setSelectable(true);
        unit.setTouchable(Touchable.enabled);
        item.getRoot().addItem(item);

        Vector2 input1 = new Vector2(35, toScreen(35));
        Vector2 input2 = new Vector2(50, toScreen(50));

        action.setIdentifier(SelectActions.SelectBoxEnd);
        action.setCause(new UserInput(UserInputType.PressUp, input1, input2, 1));

        assertTrue(action.act(1));
        assertTrue(unit.getSelected());
    }

    private Item getSelectionBox() {
        ItemRoot root = item.getRoot();
        return root.find(withType(SelectionBox));
    }
}