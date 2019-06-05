/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.placeholder;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.common.lang.TextIdentifier;
import com.evilbird.engine.events.EventQueue;
import com.evilbird.engine.item.Item;
import com.evilbird.test.data.item.TestGatherers;
import com.evilbird.test.data.item.TestPlaceholders;
import com.evilbird.test.testcase.ActionTestCase;
import com.evilbird.warcraft.item.placeholder.Placeholder;
import com.evilbird.warcraft.item.unit.UnitType;
import com.evilbird.warcraft.item.unit.gatherer.Gatherer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Instances of this unit test validate the {@link PlaceholderCancel} class.
 *
 * @author Blair Butterworth
 */
public class PlaceholderCancelTest extends ActionTestCase
{
    private Placeholder placeholder;
    private EventQueue reporter;

    @Before
    public void setup() {
        reporter = Mockito.mock(EventQueue.class);
        placeholder = TestPlaceholders.newTestPlaceholder("placeholder");
        super.setup();
    }

    @Override
    protected Action newAction() {
        return new PlaceholderCancel(reporter);
    }

    @Override
    protected Enum newIdentifier() {
        return PlaceholderActions.PlaceholderCancel;
    }

    protected Item newItem() {
        Gatherer gatherer = TestGatherers.newTestGatherer(new TextIdentifier("item"), UnitType.Peasant);
        gatherer.setAssociatedItem(placeholder);
        return gatherer;
    }

    @Test
    public void actTest() {
        Assert.assertFalse(action.act(1));
        Assert.assertTrue(player.getItems().stream().noneMatch(item -> item == placeholder));

        Assert.assertTrue(action.act(1));
        Assert.assertNull(((Gatherer)item).getAssociatedItem());
//        Mockito.verify(reporter).add(new PlaceholderEvent(item, placeholder, PlaceholderStatus.Removed));
    }
}