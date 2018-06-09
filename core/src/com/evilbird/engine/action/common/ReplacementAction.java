package com.evilbird.engine.action.common;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.engine.action.common.ClearAction;
import com.evilbird.engine.action.framework.DelegateAction;
import com.evilbird.engine.item.Item;

public class ReplacementAction extends DelegateAction
{
    private Item item;

    public ReplacementAction(Item item, Action next) {
        super(next);
        this.item = item;
    }

    @Override
    public boolean act(float delta) {
        item.clearActions();
        item.addAction(delegate);
        return true;
    }
}
