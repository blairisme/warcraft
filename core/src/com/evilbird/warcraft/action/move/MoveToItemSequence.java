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
import com.evilbird.engine.events.EventQueue;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.action.common.scenario.ScenarioAction;

import javax.inject.Inject;

import static com.evilbird.engine.action.common.AnimateAction.animate;
import static com.evilbird.warcraft.action.move.MoveActions.MoveToItem;
import static com.evilbird.warcraft.action.move.MoveToItemAction.moveToItem;
import static com.evilbird.warcraft.item.common.query.UnitPredicates.isAlive;
import static com.evilbird.warcraft.item.unit.UnitAnimation.Idle;
import static com.evilbird.warcraft.item.unit.UnitAnimation.Move;

/**
 * Instances of this {@link Action action} move an {@link Item} from its
 * current location to a given destination, specified as a {@link Item}. The
 * moving item will be animated with a movement animation.
 *
 * @author Blair Butterworth
 */
public class MoveToItemSequence extends ScenarioAction
{
    @Inject
    public MoveToItemSequence(EventQueue events) {
        scenario(MoveToItem);
        given(isAlive());
        then(animate(Move));
        then(moveToItem(events));
        then(animate(Idle));
    }
}
