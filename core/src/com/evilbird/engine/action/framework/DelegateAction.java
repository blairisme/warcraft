/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.action.framework;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Pool;
import com.evilbird.engine.common.lang.Identifier;

/**
 * Instances of this {@link Action} provide the basic implementation of an
 * <code>Action</code> which defers its operation to another <code>Action</code>.
 *
 * @author Blair Butterworth
 */
public abstract class DelegateAction extends BasicAction
{
    protected Action delegate;

    public DelegateAction() {
    }

    public DelegateAction(Action delegate) {
        this.delegate = delegate;
    }

    @Override
    public Identifier getIdentifier() {
        if (delegate instanceof BasicAction) {
            return ((BasicAction)delegate).getIdentifier();
        }
        return super.getIdentifier();
    }

    @Override
    public void setIdentifier(Identifier identifier) {
        super.setIdentifier(identifier);
        if (delegate instanceof BasicAction) {
            ((BasicAction)delegate).setIdentifier(identifier);
        }
    }

    @Override
    public void restart() {
        super.restart();
        if (delegate != null) {
            delegate.restart();
        }
    }

    @Override
    public void reset() {
        super.reset();
        if (delegate != null) {
            delegate.reset();
        }
    }

    @Override
    public Actor getActor() {
        if (delegate != null) {
            return delegate.getActor();
        }
        return super.getActor();
    }

    @Override
    public void setActor(Actor actor) {
        super.setActor(actor);
        if (delegate != null) {
            delegate.setActor(actor);
        }
    }

    @Override
    public Pool getPool() {
        if (delegate != null) {
            return delegate.getPool();
        }
        return super.getPool();
    }

    @Override
    public void setPool(Pool pool) {
        super.setPool(pool);
        if (delegate != null) {
            delegate.setPool(pool);
        }
    }

    @Override
    public Actor getTarget() {
        if (delegate != null) {
            return delegate.getTarget();
        }
        return super.getTarget();
    }

    @Override
    public void setTarget(Actor target) {
        super.setTarget(target);
        if (delegate != null) {
            delegate.setTarget(target);
        }
    }

    @Override
    public Throwable getError() {
        if (delegate instanceof BasicAction) {
            return ((BasicAction)delegate).getError();
        }
        return null;
    }

    @Override
    public void setError(Throwable error) {
        if (delegate instanceof BasicAction) {
            ((BasicAction)delegate).setError(error);
        }
    }
}
