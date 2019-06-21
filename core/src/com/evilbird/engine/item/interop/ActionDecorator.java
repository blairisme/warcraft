/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.item.interop;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.evilbird.engine.action.Action;
import com.evilbird.engine.item.Item;

/**
 * Instances of this class decorate the LibGDX Action class with an {@link Action},
 * allowing the Action to receive events without inheriting from it.
 *
 * @author Blair Butterworth
 */
public class ActionDecorator extends com.badlogic.gdx.scenes.scene2d.Action
{
    private Action delegate;

    public ActionDecorator(Action delegate) {
        this.delegate = delegate;
    }

    public Action getDelegate() {
        return delegate;
    }

    @Override
    public Actor getActor() {
        return delegate.getItem().toActor();
    }

    @Override
    public Actor getTarget() {
        return delegate.getTarget().toActor();
    }

    @Override
    public void setActor(Actor actor) {
        Item item = actor != null ? (Item)actor.getUserObject() : null;
        delegate.setItem(item);
    }

    @Override
    public void setTarget(Actor target) {
        Item item = target != null ? (Item)target.getUserObject() : null;
        delegate.setTarget(item);
    }

    @Override
    public boolean act(float delta) {
        return delegate.act(delta);
    }

    @Override
    public void restart() {
        delegate.restart();
    }

    @Override
    public void reset() {
        delegate.reset();
    }
}
