/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.move;

import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.framework.ScenarioAction;
import com.evilbird.engine.item.Item;

import javax.inject.Inject;

import static com.evilbird.engine.action.common.AnimateAction.animate;
import static com.evilbird.warcraft.action.move.MoveActions.MoveToLocation;
import static com.evilbird.warcraft.action.move.MoveToVectorAction.moveToCause;
import static com.evilbird.warcraft.item.common.query.UnitPredicates.isAlive;
import static com.evilbird.warcraft.item.unit.UnitAnimation.Idle;
import static com.evilbird.warcraft.item.unit.UnitAnimation.Move;

/**
 * Instances of this {@link Action action} move an {@link Item} from its
 * current location to a given destination, specified as a {@link Vector2}. The
 * moving item will be animated with a movement animation.
 *
 * @author Blair Butterworth
 */
public class MoveToVectorScenario extends ScenarioAction
{
    @Inject
    public MoveToVectorScenario(MoveReporter observer) {
        scenario(MoveToLocation);
        given(isAlive());
        then(animate(Move));
        then(moveToCause(observer));
        then(animate(Idle));
    }
}
