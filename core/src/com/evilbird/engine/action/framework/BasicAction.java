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

public abstract class BasicAction extends Action implements Identifiable
{
    private Identifier identifier;

    public BasicAction() {
        identifier = GenericIdentifier.Unknown;
    }

    public void cancel() {
        Actor actor = getActor();
        actor.clearActions();
    }

    @Override
    public Identifier getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Identifier identifier) {
        this.identifier = identifier;
    }
}
