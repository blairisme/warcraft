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
import java.util.Iterator;
import java.util.List;

/**
 * Instances of this {@link Action} execute a set of delegate actions in
 * sequence, one after another until complete.
 *
 * @author Blair Butterworth
 */
public class SequenceAction extends CompositeAction
{
    private Action current;
    private Iterator<Action> iterator;

    public SequenceAction(Action... sequence) {
        this(Arrays.<Action>asList(sequence));
    }

    public SequenceAction(List<Action> sequence) {
        super(sequence);
        restart();
    }

    @Override
    public boolean act(float delta) {
        boolean result = current.act(delta);
        if (result && iterator.hasNext()) {
            current = iterator.next();
            result = false;
        }
        return result;
    }

    @Override
    public void restart() {
        super.restart();
        this.iterator = delegates.iterator();
        this.current = iterator.next();
    }

    public Action getCurrent() {
        return current;
    }
}
