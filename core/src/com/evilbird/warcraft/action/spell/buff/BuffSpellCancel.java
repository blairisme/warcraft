/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.spell.buff;

import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectGroup;
import com.evilbird.warcraft.object.common.value.BuffValue;
import com.evilbird.warcraft.object.common.value.Value;
import com.evilbird.warcraft.object.common.value.ValueProperty;
import com.evilbird.warcraft.object.unit.combatant.Combatant;

import java.util.Collection;

import static com.evilbird.engine.action.ActionConstants.ActionComplete;

/**
 * A spell that removes the effects of a buff spell from a given combatant.
 *
 * @author Blair Butterworth
 */
public abstract class BuffSpellCancel extends BasicAction
{
    protected abstract Collection<ValueProperty> buffedProperties(Combatant target);

    @Override
    public boolean act(float delta) {
        Combatant target = (Combatant)getTarget();
        removeBadge(target);
        removeBuff(target);
        return ActionComplete;
    }

    protected void removeBadge(Combatant target) {
        GameObject effect = target.getEffect();
        GameObjectGroup parent = effect.getParent();
        parent.removeObject(effect);
        target.setEffect(null);
    }

    protected void removeBuff(Combatant target) {
        for (ValueProperty property: buffedProperties(target)) {
            Value oldValue = property.getValue();
            Value newValue = removeBuff(oldValue);
            property.setValue(newValue);
        }
    }

    protected Value removeBuff(Value value) {
        if (value instanceof BuffValue) {
            BuffValue buff = (BuffValue)value;
            return buff.getOriginal();
        }
        return value;
    }
}
