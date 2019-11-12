/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.integration.steps;

import com.evilbird.engine.object.GameObject;
import com.evilbird.warcraft.item.common.capability.SelectableObject;
import com.evilbird.engine.common.lang.TextIdentifier;
import com.evilbird.engine.game.GameEngine;
import com.evilbird.engine.object.GameObjectContainer;
import com.evilbird.engine.state.State;
import com.evilbird.warcraft.integration.device.IntegrationApplication;
import com.evilbird.warcraft.integration.device.IntegrationContext;
import cucumber.api.java.en.Then;
import org.junit.Assert;

import static com.evilbird.engine.object.utility.GameObjectPredicates.withId;

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
        GameObjectContainer world = state.getWorld();
        GameObject gameObject = world.find(withId(new TextIdentifier(identifier)));
        SelectableObject selectable = (SelectableObject) gameObject;
        Assert.assertEquals(selected.equals("selected"), selectable.getSelected());
    }
}
