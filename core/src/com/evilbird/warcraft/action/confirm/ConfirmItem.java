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
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectFactory;
import com.evilbird.engine.object.GameObjectType;
import com.evilbird.warcraft.common.WarcraftPreferences;
import com.evilbird.warcraft.object.effect.EffectType;

import javax.inject.Inject;

/**
 * Instances of this {@link Action} show a confirmation effect over a given {@link GameObject}.
 *
 * @author Blair Butterworth
 */
public class ConfirmItem extends ConfirmAction
{
    @Inject
    public ConfirmItem(GameObjectFactory factory, WarcraftPreferences preferences) {
        super(factory, preferences);
        setIdentifier(ConfirmActions.ConfirmTarget);
    }

    @Override
    protected GameObjectType getEffectType() {
        return EffectType.Confirm;
    }

    @Override
    protected Vector2 getPosition() {
        GameObject target = getTarget();
        return target.getPosition(Alignment.Center);
    }

    @Override
    protected Alignment getAlignment() {
        return Alignment.Center;
    }
}
