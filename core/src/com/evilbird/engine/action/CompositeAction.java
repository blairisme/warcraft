package com.evilbird.engine.action;

import com.badlogic.gdx.scenes.scene2d.Action;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class CompositeAction extends Action
{
    private List<Action> sequence;
    private Iterator<Action> iterator;

    public CompositeAction(List<Action> sequence)
    {
        this.sequence = sequence;
        restart();
    }

    public CompositeAction(Action ... sequence)
    {
        this(Arrays.<Action>asList(sequence));
    }

    @Override
    public boolean act(float delta)
    {
        Action current = iterator.next();
        while (current != null)
        {
            while (! current.act(delta));
            current = iterator.hasNext() ? iterator.next() : null;
        }
        return true;
    }

    @Override
    public void restart()
    {
        this.iterator = sequence.iterator();
        for (Action action: sequence)
        {
            action.restart();
        }
    }
}
