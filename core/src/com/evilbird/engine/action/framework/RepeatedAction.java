package com.evilbird.engine.action.framework;

import com.badlogic.gdx.scenes.scene2d.Action;

import java.util.concurrent.CancellationException;

public class RepeatedAction extends DelegateAction
{
    private static final int INFINITE = -1;
    private int count;
    private int times;

    public RepeatedAction(Action action) {
        this(action, INFINITE);
    }

    public RepeatedAction(Action action, int times) {
        super(action);
        this.times = times;
        this.count = 0;
    }

    @Override
    public boolean act(float delta) {
        if (delegate.act(delta)) {
            return repeat();
        }
        return false;
    }

    private boolean repeat() {
        if (times == INFINITE || count++ < times) {
            delegate.restart();
            return false;
        }
        return true;
    }

    @Override
    public void restart() {
        super.restart();
        count = 0;
    }
}
