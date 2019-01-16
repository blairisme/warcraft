package com.evilbird.engine.action.framework;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Pool;

import java.util.ArrayList;
import java.util.List;

public abstract class CompositeAction extends BasicAction
{
    protected List<Action> delegates;

    public CompositeAction() {
        delegates = new ArrayList<>();
    }

    public CompositeAction(List<Action> delegates) {
        this.delegates = delegates;
    }

    @Override
    public void restart() {
        super.restart();
        for (Action delegate: delegates) {
            delegate.restart();
        }
    }

    @Override
    public void reset() {
        super.reset();
        for (Action delegate: delegates) {
            delegate.reset();
        }
        delegates.clear();
    }

    @Override
    public void setActor(Actor actor) {
        super.setActor(actor);
        for (Action delegate: delegates) {
            delegate.setActor(actor);
        }
    }

    @Override
    public void setPool(Pool pool) {
        super.setPool(pool);
        for (Action delegate: delegates) {
            delegate.setPool(pool);
        }
    }

    @Override
    public void setTarget(Actor target) {
        super.setTarget(target);
        for (Action delegate: delegates) {
            delegate.setTarget(target);
        }
    }
}
