/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.spell.attack;

import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.warcraft.object.common.value.AbsoluteBuffValue;
import com.evilbird.warcraft.object.common.value.Value;
import com.evilbird.warcraft.object.unit.Unit;

import javax.inject.Inject;

import static com.evilbird.engine.action.ActionConstants.ActionComplete;

/**
 * A spell that removes the effects of the unholy armour spell.
 *
 * @author Blair Butterworth
 */
public class UnholyArmourCancel extends BasicAction
{
    @Inject
    public UnholyArmourCancel() {
    }

    @Override
    public boolean act(float delta) {
        Unit target = (Unit)getTarget();
        removeBuff(target);
        return ActionComplete;
    }

    private void removeBuff(Unit target) {
        Value oldValue = target.getArmourValue();
        Value newValue = removeBuff(oldValue);
        target.setArmour(newValue);
    }

    private Value removeBuff(Value value) {
        if (value instanceof AbsoluteBuffValue) {
            AbsoluteBuffValue buff = (AbsoluteBuffValue)value;
            return buff.getOriginal();
        }
        return value;
    }
}
