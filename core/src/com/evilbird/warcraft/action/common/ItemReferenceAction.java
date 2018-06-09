package com.evilbird.warcraft.action.common;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.engine.action.common.ClearAction;
import com.evilbird.engine.common.function.Function;
import com.evilbird.engine.common.function.Supplier;
import com.evilbird.engine.item.Item;

import java.util.concurrent.CancellationException;


public class ItemReferenceAction extends Action
{
    private Action delegate;
    private Supplier<Item> itemSupplier;
    private Function<Item, Action> actionSupplier;

    public ItemReferenceAction(Supplier<Item> itemSupplier, Function<Item, Action> actionSupplier) {
        this.delegate = null;
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
            throw new CancellationException(); //TODO: Replace with getActor().clearActions() - doesnt work because composite actions dont pass actor to their children
        }
        delegate = actionSupplier.apply(item);
    }
}
