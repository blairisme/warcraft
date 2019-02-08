/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.common.production;

import com.evilbird.engine.action.framework.DelegateAction;
import com.evilbird.engine.action.framework.SequenceAction;
import com.evilbird.engine.action.framework.duration.TimeDuration;

/**
 * Instances of this action toggle the state indicating that a building is
 * producing something for a given duration.
 *
 * @author Blair Butterworth
 */
public class ProduceAction extends DelegateAction
{
    private ProgressAction progress;

    public ProduceAction() {
        ProducingAction before = new ProducingAction(true);
        ProducingAction after = new ProducingAction(false);
        progress = new ProgressAction();
        delegate = new SequenceAction(before, progress, after);
    }

    public void setDuration(TimeDuration duration) {
        progress.setDuration(duration);
    }
}
