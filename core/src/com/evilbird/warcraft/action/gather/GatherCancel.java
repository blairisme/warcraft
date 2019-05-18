/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.gather;

import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.action.common.scenario.ScenarioAction;

import javax.inject.Inject;

import static com.evilbird.engine.action.common.AnimateAction.animate;
import static com.evilbird.warcraft.action.gather.GatherEvents.gatherCancelled;
import static com.evilbird.warcraft.item.unit.UnitAnimation.Idle;

/**
 * Instances of this class stop a given {@link Item} from gathering, retaining
 * resources from the partially completed gathering process.
 *
 * @author Blair Butterworth
 */
public class GatherCancel extends ScenarioAction
{
    private GatherReporter reporter;

    @Inject
    public GatherCancel(GatherReporter reporter) {
        this.reporter = reporter;
    }

    @Override
    protected void steps(Identifier identifier) {
        scenario(GatherActions.GatherCancel);
        then(animate(Idle), gatherCancelled(reporter));
    }
}
