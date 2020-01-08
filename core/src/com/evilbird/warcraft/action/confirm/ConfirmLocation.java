/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.confirm;

import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.action.Action;
import com.evilbird.engine.common.lang.Alignment;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectContainer;
import com.evilbird.engine.object.GameObjectFactory;
import com.evilbird.engine.object.GameObjectType;
import com.evilbird.warcraft.common.WarcraftPreferences;
import com.evilbird.warcraft.object.effect.EffectType;

import javax.inject.Inject;

/**
 * Instances of this {@link Action} show a confirmation effect at a given
 * location.
 *
 * @author Blair Butterworth
 */
public class ConfirmLocation extends ConfirmAction
{
    @Inject
    public ConfirmLocation(GameObjectFactory factory, WarcraftPreferences preferences) {
        super(factory, preferences);
        setIdentifier(ConfirmActions.ConfirmLocation);
    }

    @Override
    protected GameObjectType getEffectType() {
        return EffectType.Confirm;
    }

    @Override
    protected Vector2 getPosition() {
        GameObject gameObject = getSubject();
        GameObjectContainer root = gameObject.getRoot();
        UserInput cause = getCause();
        Vector2 position = root.unproject(cause.getPosition());
        return new Vector2(position.x - 16, position.y - 16);
    }

    @Override
    protected Alignment getAlignment() {
        return Alignment.BottomLeft;
    }
}
