package com.evilbird.engine.action.framework;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.engine.common.function.Supplier;

public class BranchAction extends Action
{
    private Action delegate;
    private Action trueAction;
    private Action falseAction;
    private Supplier<Boolean> decision;

    public BranchAction(Supplier<Boolean> decision, Action trueAction, Action falseAction) {
        this.decision = decision;
        this.trueAction = trueAction;
        this.falseAction = falseAction;
    }

    @Override
    public boolean act(float delta) {
        if (delegate == null) {
            delegate = decision.get() == Boolean.TRUE ? trueAction : falseAction;
        }
        return delegate.act(delta);
    }

    @Override
    public void restart() {
        delegate = null;
    }
}
