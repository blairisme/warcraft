/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.spell.buff;

import com.evilbird.engine.common.time.GameTimer;
import com.evilbird.engine.object.GameObjectFactory;
import com.evilbird.warcraft.action.spell.SpellAction;
import com.evilbird.warcraft.item.common.spell.Spell;
import com.evilbird.warcraft.item.common.value.BuffValue;
import com.evilbird.warcraft.item.common.value.Value;
import com.evilbird.warcraft.item.common.value.ValueProperty;
import com.evilbird.warcraft.item.effect.EffectType;
import com.evilbird.warcraft.item.unit.Unit;
import com.evilbird.warcraft.item.unit.combatant.Combatant;

import java.util.Collection;

/**
 * A base class for spells that modifies the attributes of a given game object.
 *
 * @author Blair Butterworth
 */
public abstract class BuffSpellAction extends SpellAction
{
    private BuffSpellCancel cancel;

    public BuffSpellAction(Spell spell, EffectType effect, GameObjectFactory factory, BuffSpellCancel cancel) {
        super(spell, effect, factory);
        this.cancel = cancel;
    }

    @Override
    protected void initialize() {
        super.initialize();
        setBuff();
        setBuffCancel();
    }

    protected abstract Collection<ValueProperty> buffedProperties(Combatant target);

    protected void setBuff() {
        for (ValueProperty property: buffedProperties((Combatant)getTarget())) {
            Value oldValue = property.getValue();
            Value newValue = setBuff(spell, oldValue);
            property.setValue(newValue);
        }
    }

    protected Value setBuff(Spell spell, Value value) {
        if (!(value instanceof BuffValue)) {
            return new BuffValue(spell.getValue(), value);
        }
        return value;
    }

    protected void setBuffCancel() {
        Unit caster = (Unit) getSubject();
        Unit target = (Unit)getTarget();

        cancel.setItem(caster);
        cancel.setTarget(target);
        target.addAction(cancel, new GameTimer(spell.getEffectDuration()));
    }
}
