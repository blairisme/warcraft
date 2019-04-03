/*
 * Blair Butterworth (c) 2018
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.attack;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.common.AnimateAction;
import com.evilbird.engine.action.common.AudibleAction;
import com.evilbird.engine.action.common.DisableAction;
import com.evilbird.engine.action.common.RemoveAction;
import com.evilbird.engine.action.framework.DelayedAction;
import com.evilbird.engine.action.framework.DelegateAction;
import com.evilbird.engine.action.framework.ParallelAction;
import com.evilbird.engine.action.framework.SequenceAction;
import com.evilbird.warcraft.action.select.SelectAction;
import com.evilbird.warcraft.item.unit.UnitAnimation;
import com.evilbird.warcraft.item.unit.UnitSound;

/**
 * Instances of this {@link Action} instruct a given item to die.
 *
 * @author Blair Butterworth
 */
public class DeathAction extends DelegateAction
{
    public DeathAction() {
        Action deselect = new SelectAction(false, null);
        Action disable = new DisableAction(false);

        Action dieSound = new AudibleAction(UnitSound.Die);
        Action dieAnimation = new AnimateAction(UnitAnimation.Die);
        Action dieWait = new DelayedAction(1);
        Action die = new ParallelAction(deselect, disable, dieSound, dieAnimation, dieWait);

        Action decomposeAnimation = new AnimateAction(UnitAnimation.Decompose);
        Action decomposeWait = new DelayedAction(10);
        Action decompose = new SequenceAction(decomposeAnimation, decomposeWait);

        Action remove = new RemoveAction();
        delegate = new SequenceAction(die, decompose, remove);
    }

    @Override
    public boolean act(float delta) {
        return delegate.act(delta);
    }
}
