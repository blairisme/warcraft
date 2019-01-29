/*
 * Blair Butterworth (c) 2018
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.component;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.engine.action.common.*;
import com.evilbird.engine.action.framework.DelayedAction;
import com.evilbird.engine.action.framework.DelegateAction;
import com.evilbird.engine.action.framework.ParallelAction;
import com.evilbird.engine.action.framework.SequenceAction;
import com.evilbird.engine.action.framework.duration.TimeDuration;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.specialized.animated.Animated;
import com.evilbird.engine.item.specialized.animated.Audible;
import com.evilbird.warcraft.item.unit.UnitAnimation;
import com.evilbird.warcraft.item.unit.UnitSound;

/**
 * Instances of this {@link Action} instruct a given item to die.
 *
 * @author Blair Butterworth
 */
public class DieAction extends DelegateAction
{
    public DieAction(Item item) {
        Action deselect = new SelectAction(item, false);
        Action disable = new DisableAction(item, false);

        Action dieSound = new AudibleAction((Audible)item, UnitSound.Die);
        Action dieAnimation = new AnimateAction((Animated)item, UnitAnimation.Die);
        Action dieWait = new DelayedAction(new TimeDuration(0.5f));
        //Action dieSequence = new SequenceAction(dieAnimation, dieWait);
        Action die = new ParallelAction(deselect, disable, dieSound, dieAnimation, dieWait);

        Action decomposeAnimation = new AnimateAction((Animated)item, UnitAnimation.Decompose);
        Action decomposeWait = new DelayedAction(new TimeDuration(10f));
        Action decompose = new SequenceAction(decomposeAnimation, decomposeWait);

        Action remove = new RemoveAction(item);
        delegate = new SequenceAction(die, decompose, remove);
    }

    @Override
    public boolean act(float delta) {
        return delegate.act(delta);
    }
}
