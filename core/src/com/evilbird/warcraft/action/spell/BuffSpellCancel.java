/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.spell;

import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.warcraft.item.common.value.BuffValue;
import com.evilbird.warcraft.item.common.value.Value;
import com.evilbird.warcraft.item.common.value.ValueProperty;
import com.evilbird.warcraft.item.unit.combatant.Combatant;

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
        removeBuff();
        return ActionComplete;
    }

    protected void removeBuff() {
        for (ValueProperty property: buffedProperties((Combatant)getTarget())) {
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
