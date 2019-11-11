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
import com.evilbird.engine.object.GameObject;
import com.evilbird.test.data.item.TestGatherers;
import com.evilbird.test.data.item.TestPlaceholders;
import com.evilbird.test.testcase.ActionTestCase;
import com.evilbird.warcraft.action.selector.SelectorActions;
import com.evilbird.warcraft.action.selector.SelectorCreate;
import com.evilbird.warcraft.action.selector.SelectorEvents;
import com.evilbird.warcraft.item.selector.building.BuildingSelector;
import com.evilbird.warcraft.item.unit.UnitType;
import com.evilbird.warcraft.item.unit.gatherer.Gatherer;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;

import static com.evilbird.warcraft.item.selector.SelectorType.BarracksSelector;

/**
 * Instances of this unit test validate the {@link SelectorCreate} class.
 *
 * @author Blair Butterworth
 */
public class SelectorCreateTest extends ActionTestCase
{
    private SelectorEvents reporter;

    @Override
    protected Action newAction() {
        reporter = Mockito.mock(SelectorEvents.class);
        SelectorCreate action = new SelectorCreate(reporter, objectFactory);
        action.setIdentifier(SelectorActions.ShowBarracksSelector);
        return action;
    }

    @Override
    protected Enum newIdentifier() {
        return SelectorActions.ShowBarracksSelector;
    }

    protected GameObject newItem() {
        return TestGatherers.newTestGatherer(new TextIdentifier("item"), UnitType.Peasant);
    }

    @Test
    @Ignore
    public void actTest() {
        BuildingSelector selector = TestPlaceholders.newTestPlaceholder("placeholder");
        Mockito.when(objectFactory.get(BarracksSelector)).thenReturn(selector);

        Assert.assertFalse(action.act(1));
        Assert.assertTrue(player.getObjects().stream().anyMatch(item -> item == selector));

        Assert.assertTrue(action.act(1));
        Assert.assertEquals(selector, ((Gatherer) gameObject).getAssociatedItem());
//        Mockito.verify(reporter).add(new PlaceholderEvent(item, placeholder, PlaceholderStatus.Added));
    }
}