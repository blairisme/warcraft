/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.integration;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * Test suite containing Cucumber based acceptance tests.
 *
 * @author Blair Butterworth
 */
@RunWith(Cucumber.class)
@CucumberOptions(
    plugin = {"pretty"},
    features={"classpath:features/"},
    glue = { "com.evilbird.warcraft.integration.steps"})
public class IntegrationRunner
{
}