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
import com.evilbird.engine.common.lang.GenericIdentifier;
import com.evilbird.engine.common.lang.Identifiable;
import com.evilbird.engine.common.lang.Identifier;

/**
 * Instances of this class represent a base class for {@link Action Actions},
 * containing common methods and properties utilised by many custom actions.
 *
 * @author Blair Butterworth
 */
public abstract class BasicAction extends Action implements Identifiable
{
    private Identifier identifier;
    private Throwable error;

    public BasicAction() {
        identifier = GenericIdentifier.Unknown;
    }

    public void cancel() {
        Actor actor = getActor();
        actor.clearActions();
    }

    @Override
    public void restart() {
        super.restart();
        error = null;
    }

    public Throwable getError() {
        return error;
    }

    public boolean hasError() {
        return getError() != null;
    }

    @Override
    public Identifier getIdentifier() {
        return identifier;
    }

    public void setError(Throwable error) {
        this.error = error;
    }

    public void setIdentifier(Identifier identifier) {
        this.identifier = identifier;
    }
}
