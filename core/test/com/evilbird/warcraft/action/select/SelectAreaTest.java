/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.select;

import com.evilbird.engine.action.Action;
import com.evilbird.test.testcase.ActionTestCase;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertTrue;

/**
 * Instances of this unit test validate the {@link SelectArea} class.
 *
 * @author Blair Butterworth
 */
public class SelectAreaTest extends ActionTestCase
{
    @Override
    protected Action newAction() {
        SelectArea action = new SelectArea(Mockito.mock(SelectReporter.class));
        action.setIdentifier(SelectActions.SelectBoxBegin);
        return action;
    }

    @Override
    protected Enum newIdentifier() {
        return SelectActions.SelectBoxBegin;
    }

    @Test
    public void beginTest() {
        assertTrue(action.act(1));
    }

    @Test
    public void updateTest() {

    }

    @Test
    public void removeTest() {

    }
}