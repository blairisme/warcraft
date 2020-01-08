/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.integration.steps;

import com.evilbird.engine.game.GameEngine;
import com.evilbird.warcraft.integration.device.IntegrationApplication;
import com.evilbird.warcraft.integration.device.IntegrationContext;
import com.evilbird.warcraft.state.WarcraftCampaign;
import cucumber.api.java.en.Given;

/**
 * @author Blair Butterworth
 */
public class ScenarioSteps
{
    private IntegrationContext context;

    public ScenarioSteps(IntegrationContext context) {
        this.context = context;
    }

    @Given("^the user is playing (human campaign|orc campaign|scenario) (.*)$")
    public void initializeUsers(String type, String name) throws Exception {
        GameEngine engine = context.getEngine();
        engine.showState(WarcraftCampaign.Human3);

        IntegrationApplication application = context.getApplication();
        application.update();
    }
}
