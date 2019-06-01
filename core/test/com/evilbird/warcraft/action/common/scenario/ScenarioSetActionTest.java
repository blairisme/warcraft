/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.common.scenario;

import com.evilbird.test.testcase.GameTestCase;
import org.junit.Before;

/**
 * Instances of this unit test validate the {@link ScenarioSetAction} class.
 *
 * @author Blair Butterworth
 */
public class ScenarioSetActionTest extends GameTestCase
{
    private ScenarioAction scenario;
    private ScenarioSetAction scenarioSet;

    @Before
    public void setup() {
        scenarioSet = new ScenarioSetAction();
        scenario = scenarioSet.scenario("test");

    }
}