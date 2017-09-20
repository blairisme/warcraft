package com.evilbird.engine.action.framework;

import com.badlogic.gdx.scenes.scene2d.Action;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class SequenceAction extends Action
{
    private Action current;
    private List<Action> sequence;
    private Iterator<Action> iterator;

    public SequenceAction(List<Action> sequence)
    {
        this.sequence = sequence;
        restart();
    }

    public SequenceAction(Action ... sequence)
    {
        this(Arrays.<Action>asList(sequence));
    }

    @Override
    public boolean act(float delta)
    {
        boolean result = current.act(delta);
        if (result && iterator.hasNext())
        {
            current = iterator.next();
            result = false;
        }
        return result;
    }

    @Override
    public void restart()
    {
        for (Action action: sequence){
            action.restart();
        }
        this.iterator = sequence.iterator();
        this.current = iterator.next();
    }
}
