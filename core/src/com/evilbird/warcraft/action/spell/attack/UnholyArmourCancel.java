/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.spell.attack;

import com.evilbird.engine.action.ActionResult;
import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.warcraft.object.common.value.AbsoluteBuffValue;
import com.evilbird.warcraft.object.common.value.Value;
import com.evilbird.warcraft.object.unit.Unit;

import javax.inject.Inject;

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
    public ActionResult act(float delta) {
        Unit target = (Unit)getTarget();
        removeBuff(target);
        return ActionResult.Complete;
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
