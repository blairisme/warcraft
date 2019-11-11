/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.confirm;

import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.action.Action;
import com.evilbird.engine.common.lang.Alignment;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectFactory;
import com.evilbird.engine.object.GameObjectType;
import com.evilbird.warcraft.common.WarcraftPreferences;
import com.evilbird.warcraft.item.effect.EffectType;

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
