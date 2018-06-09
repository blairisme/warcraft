package com.evilbird.engine.action.common;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.evilbird.engine.action.common.ClearAction;
import com.evilbird.engine.action.framework.DelegateAction;
import com.evilbird.engine.common.function.Function;
import com.evilbird.engine.common.function.Supplier;
import com.evilbird.engine.item.Item;

import java.util.concurrent.CancellationException;


public class ReferenceAction extends DelegateAction
{
    private Supplier<Item> itemSupplier;
    private Function<Item, Action> actionSupplier;

    public ReferenceAction(Supplier<Item> itemSupplier, Function<Item, Action> actionSupplier) {
        super();
        this.itemSupplier = itemSupplier;
        this.actionSupplier = actionSupplier;
    }

    @Override
    public boolean act(float delta) {
        if (delegate == null) {
            updateDelegate();
        }
        return delegate.act(delta);
    }

    @Override
    public void restart() {
        delegate = null;
    }

    private void updateDelegate() {
        Item item = itemSupplier.get();
        if (item == null) {
            Actor actor = getActor();
            actor.clearActions();
            //throw new CancellationException(); //TODO: Replace with getActor().clearActions() - doesnt work because composite actions dont pass actor to their children
        }
        delegate = actionSupplier.apply(item);
    }
}
