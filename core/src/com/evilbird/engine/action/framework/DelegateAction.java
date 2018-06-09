package com.evilbird.engine.action.framework;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Pool;

public abstract class DelegateAction extends BasicAction
{
    protected Action delegate;

    public DelegateAction() {
    }

    public DelegateAction(Action delegate) {
        this.delegate = delegate;
    }

    @Override
    public void restart() {
        super.restart();
        if (delegate != null) {
            delegate.restart();
        }
    }

    @Override
    public void reset() {
        super.reset();
        if (delegate != null) {
            delegate.reset();
        }
    }

    @Override
    public void setActor(Actor actor) {
        super.setActor(actor);
        if (delegate != null) {
            delegate.setActor(actor);
        }
    }

    @Override
    public void setPool(Pool pool) {
        super.setPool(pool);
        if (delegate != null) {
            delegate.setPool(pool);
        }
    }

    @Override
    public void setTarget(Actor target) {
        super.setTarget(target);
        if (delegate != null) {
            delegate.setTarget(target);
        }
    }
}
