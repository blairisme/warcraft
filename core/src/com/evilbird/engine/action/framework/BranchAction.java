package com.evilbird.engine.action.framework;

import com.badlogic.gdx.scenes.scene2d.Action;

import java.util.function.Supplier;

public class BranchAction extends Action
{
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
        if (decision.get()) {
            return trueAction.act(delta);
        }
        else {
            return falseAction.act(delta);
        }
    }
}
