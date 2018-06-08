package com.evilbird.engine.action.framework;

import com.badlogic.gdx.scenes.scene2d.Action;

import java.util.concurrent.CancellationException;

public class RepeatedAction extends Action
{
    private static final int INFINITE = -1;

    private Action action;
    private int count;
    private int times;

    public RepeatedAction(Action action) {
        this(action, INFINITE);
    }

    public RepeatedAction(Action action, int times) {
        this.action = action;
        this.times = times;
        this.count = 0;
    }

    @Override
    public boolean act(float delta) {
        try {
            if (action.act(delta)) {
                return repeat();
            }
            return false;
        }
        catch (CancellationException e){ //TODO: remove - exceptions shouldn't be used for non-exceptional functionality
            return true;
        }
    }

    private boolean repeat() {
        if (times == INFINITE || count++ < times) {
            action.restart();
            return false;
        }
        return true;
    }

    @Override
    public void restart() {
        action.restart();
        count = 0;
    }
}
