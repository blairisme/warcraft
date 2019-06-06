/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.placeholder;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.common.lang.TextIdentifier;
import com.evilbird.engine.events.EventQueue;
import com.evilbird.engine.item.Item;
import com.evilbird.test.data.item.TestGatherers;
import com.evilbird.test.data.item.TestPlaceholders;
import com.evilbird.test.testcase.ActionTestCase;
import com.evilbird.warcraft.item.ui.placement.Placeholder;
import com.evilbird.warcraft.item.unit.UnitType;
import com.evilbird.warcraft.item.unit.gatherer.Gatherer;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import static com.evilbird.warcraft.item.ui.placement.PlaceholderType.BarracksPlaceholder;

/**
 * Instances of this unit test validate the {@link PlaceholderCreate} class.
 *
 * @author Blair Butterworth
 */
public class PlaceholderCreateTest extends ActionTestCase
{
    private EventQueue reporter;

    @Override
    protected Action newAction() {
        reporter = Mockito.mock(EventQueue.class);
        PlaceholderCreate action = new PlaceholderCreate(reporter);
        action.setIdentifier(PlaceholderActions.AddBarracksPlaceholder);
        return action;
    }

    @Override
    protected Enum newIdentifier() {
        return PlaceholderActions.AddBarracksPlaceholder;
    }

    protected Item newItem() {
        return TestGatherers.newTestGatherer(new TextIdentifier("item"), UnitType.Peasant);
    }

    @Test
    public void actTest() {
        Placeholder placeholder = TestPlaceholders.newTestPlaceholder("placeholder");
        Mockito.when(itemFactory.newItem(BarracksPlaceholder)).thenReturn(placeholder);

        Assert.assertFalse(action.act(1));
        Assert.assertTrue(player.getItems().stream().anyMatch(item -> item == placeholder));

        Assert.assertTrue(action.act(1));
        Assert.assertEquals(placeholder, ((Gatherer)item).getAssociatedItem());
//        Mockito.verify(reporter).add(new PlaceholderEvent(item, placeholder, PlaceholderStatus.Added));
    }
}