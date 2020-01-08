/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.object.interop;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.evilbird.engine.action.Action;
import com.evilbird.engine.object.GameObject;

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
        return delegate.getSubject().toActor();
    }

    @Override
    public Actor getTarget() {
        return delegate.getTarget().toActor();
    }

    @Override
    public void setActor(Actor actor) {
        GameObject gameObject = actor != null ? (GameObject)actor.getUserObject() : null;
        delegate.setSubject(gameObject);
    }

    @Override
    public void setTarget(Actor target) {
        GameObject gameObject = target != null ? (GameObject)target.getUserObject() : null;
        delegate.setTarget(gameObject);
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
