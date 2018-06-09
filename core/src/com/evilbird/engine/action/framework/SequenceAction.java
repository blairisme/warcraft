package com.evilbird.engine.action.framework;

import com.badlogic.gdx.scenes.scene2d.Action;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class SequenceAction extends CompositeAction
{
    private Action current;
    private Iterator<Action> iterator;

    public SequenceAction(List<Action> sequence) {
        super(sequence);
        restart();
    }

    public SequenceAction(Action ... sequence) {
        this(Arrays.<Action>asList(sequence));
    }

    @Override
    public boolean act(float delta) {
        boolean result = current.act(delta);
        if (result && iterator.hasNext())
        {
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
}
