package com.evilbird.engine.action.framework;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.engine.common.function.Supplier;

public class OptionalAction extends BranchAction
{
    public OptionalAction(Supplier<Boolean> decision, Action trueAction) {
        super(decision, trueAction, new EmptyAction());
    }
}
