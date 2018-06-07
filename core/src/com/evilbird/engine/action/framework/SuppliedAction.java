package com.evilbird.engine.action.framework;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.engine.common.function.Supplier;

public class SuppliedAction extends Action
{
    private Action action;
    private Supplier<Action> supplier;

    public SuppliedAction(Supplier<Action> supplier){
        this.action = null;
        this.supplier = supplier;
    }

    @Override
    public boolean act(float delta) {
        if (action == null) {
            action = supplier.get();
        }
        return action.act(delta);
    }

    @Override
    public void restart() {
        action = null;
    }
}
