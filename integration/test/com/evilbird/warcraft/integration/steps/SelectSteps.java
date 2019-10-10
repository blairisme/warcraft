/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.integration.steps;

import com.evilbird.warcraft.item.common.state.SelectableObject;
import com.evilbird.engine.common.lang.TextIdentifier;
import com.evilbird.engine.game.GameEngine;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.engine.state.State;
import com.evilbird.warcraft.integration.device.IntegrationApplication;
import com.evilbird.warcraft.integration.device.IntegrationContext;
import cucumber.api.java.en.Then;
import org.junit.Assert;

import static com.evilbird.engine.item.utility.ItemPredicates.withId;

/**
 * @author Blair Butterworth
 */
public class SelectSteps
{
    private IntegrationContext context;

    public SelectSteps(IntegrationContext context) {
        this.context = context;
    }

    @Then("^unit \"(.*)\" will be (selected|unselected)$")
    public void assetSelected(String identifier, String selected) {
        IntegrationApplication application = context.getApplication();
        application.update();

        GameEngine engine = context.getEngine();
        State state = engine.getState();
        ItemRoot world = state.getWorld();
        Item item = world.find(withId(new TextIdentifier(identifier)));
        SelectableObject selectable = (SelectableObject)item;
        Assert.assertEquals(selected.equals("selected"), selectable.getSelected());
    }
}
