/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
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
