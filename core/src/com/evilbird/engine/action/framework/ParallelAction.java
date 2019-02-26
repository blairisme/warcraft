/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.action.framework;

import com.evilbird.engine.action.Action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Instances of this {@link Action} execute a set of delegate actions all at
 * the same time.
 *
 * @author Blair Butterworth
 */
public class ParallelAction extends CompositeAction
{
    private Map<Action, Boolean> actionCompletion;

    public ParallelAction(Action... actions) {
        super(actions);
        this.actionCompletion = new HashMap<>(actions.length);
        resetCompletion();
    }

    public ParallelAction(List<Action> actions) {
        super(actions);
        this.actionCompletion = new HashMap<>(actions.size());
        resetCompletion();
    }

    @Override
    public boolean act(float delta) {
        boolean result = true;
        for (Entry<Action, Boolean> entry : actionCompletion.entrySet()) {
            boolean complete = entry.getValue();
            if (!complete) {
                Action action = entry.getKey();
                complete = action.act(delta);
                entry.setValue(complete);
            }
            result &= complete;
        }
        return result;
    }

    @Override
    public void restart() {
        super.restart();
        resetCompletion();
    }

    @Override
    public void reset() {
        super.reset();
        resetCompletion();
    }

    private void resetCompletion() {
        actionCompletion.clear();
        for (Action delegate : actions) {
            actionCompletion.put(delegate, false);
        }
    }
}