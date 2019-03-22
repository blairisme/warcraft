/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.common.animation;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.common.AnimateAction;
import com.evilbird.engine.action.framework.DelegateAction;
import com.evilbird.engine.action.framework.ParallelAction;
import com.evilbird.engine.action.framework.SequenceAction;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.item.Item;

/**
 * Instances of this class represent an {@link Action} that moves a given
 * {@link Item} as well as displaying the <code>Items</code> move animation.
 *
 * @author Blair Butterworth
 */
public class AnimatedAction extends DelegateAction
{
    public AnimatedAction(Action action, Identifier startAnimation, Identifier endAnimation) {
        Action animate = new AnimateAction(startAnimation);
        Action initial = new ParallelAction(animate, action);
        Action complete = new AnimateAction(endAnimation);
        delegate = new SequenceAction(initial, complete);
    }
}
