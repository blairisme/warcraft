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
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemFactory;
import com.evilbird.engine.item.ItemType;
import com.evilbird.warcraft.common.WarcraftPreferences;
import com.evilbird.warcraft.item.effect.EffectType;

import javax.inject.Inject;

/**
 * Instances of this {@link Action} show a confirmation effect over a given {@link Item}.
 *
 * @author Blair Butterworth
 */
public class ConfirmItem extends ConfirmAction
{
    @Inject
    public ConfirmItem(ItemFactory factory, WarcraftPreferences preferences) {
        super(factory, preferences);
        setIdentifier(ConfirmActions.ConfirmTarget);
    }

    @Override
    protected ItemType getEffectType() {
        return EffectType.Confirm;
    }

    @Override
    protected Vector2 getPosition() {
        Item target = getTarget();
        return target.getPosition(Alignment.Center);
    }

    @Override
    protected Alignment getAlignment() {
        return Alignment.Center;
    }
}
