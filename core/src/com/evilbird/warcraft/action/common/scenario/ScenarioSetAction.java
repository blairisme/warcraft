/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.common.scenario;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.action.framework.ParallelAction;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.common.lang.TextIdentifier;

/**
 * Represents a collection of {@link ScenarioAction scenarios} whose operation
 * is specified in a syntax akin to a Gherkin feature.
 *
 * @author Blair Butterworth
 */
public class ScenarioSetAction extends BasicAction
{
    private transient boolean reevaluate;
    private transient ParallelAction actions;

    public ScenarioSetAction() {
        super();
        reevaluate = false;
        actions = new ParallelAction();
    }

    public void reevaluate() {
        reevaluate = true;
    }

    public void feature(Identifier identifier) {
        setIdentifier(identifier);
    }

    public ScenarioAction scenario(String name) {
        ScenarioAction scenario = new ScenarioAction();
        scenario.setIdentifier(new TextIdentifier(name));
        scenario.setItem(getItem());
        scenario.setTarget(getTarget());
        scenario.setCause(getCause());
        actions.add(scenario);
        return scenario;
    }

    @Override
    public boolean act(float delta) {
        if (actions.isEmpty()){
            features();
        }
        boolean result = actions.act(delta);
        if (result && repeat()) {
            restart();
            result = false;
        }
        return result;
    }

    protected void features() {
    }

    private boolean repeat() {
        if (reevaluate) {
            for (Action action: actions.getActions()) {
                ScenarioAction scenario = (ScenarioAction)action;
                if (scenario.evaluate()) {
                    return true;
                }
            }
        }
        return false;
    }
}
