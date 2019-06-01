/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.move;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.framework.LambdaAction;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.action.common.scenario.ScenarioAction;

import javax.inject.Inject;

import static com.evilbird.engine.action.common.AnimateAction.animate;
import static com.evilbird.warcraft.item.unit.UnitAnimation.Idle;

/**
 * Instances of this class stop a moving {@link Item}, returning it to an idle
 * state.
 *
 * @author Blair Butterworth
 */
public class MoveCancel extends ScenarioAction
{
    private transient MoveReporter reporter;

    /**
     * Constructs a new instance of this class given a {@link MoveReporter}
     * used to report the cancellation of the move operation.
     *
     * @param reporter  a {@code MoveReporter} instance. This parameter
     *                  cannot be {@code null}.
     */
    @Inject
    public MoveCancel(MoveReporter reporter) {
        this.reporter = reporter;
        setIdentifier(MoveActions.MoveCancel);
    }

    @Override
    protected void steps(Identifier identifier) {
        then(animate(Idle), moveCancelled());
    }

    private Action moveCancelled(){
        return new LambdaAction((subject, target) -> reporter.onMoveCancelled(subject));
    }
}
