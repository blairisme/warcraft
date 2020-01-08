/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.integration.steps;

import com.evilbird.engine.object.GameObject;
import com.evilbird.warcraft.object.common.capability.SelectableObject;
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
