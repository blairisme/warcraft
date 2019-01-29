/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.action.framework;

import com.badlogic.gdx.scenes.scene2d.Action;

import java.util.Arrays;
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
        this(Arrays.<Action>asList(actions));
    }

    public ParallelAction(List<Action> actions) {
        super(actions);
        this.actionCompletion = new HashMap<Action, Boolean>(actions.size());
        restart();
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
        actionCompletion.clear();
        for (Action delegate : delegates) {
            actionCompletion.put(delegate, false);
        }
    }
}