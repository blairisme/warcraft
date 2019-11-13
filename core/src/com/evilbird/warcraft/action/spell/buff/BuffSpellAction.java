/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.spell.buff;

import com.evilbird.engine.common.graphics.Renderable;
import com.evilbird.engine.common.time.GameTimer;
import com.evilbird.engine.object.GameObjectFactory;
import com.evilbird.warcraft.action.spell.SpellAction;
import com.evilbird.warcraft.object.badge.BadgeType;
import com.evilbird.warcraft.object.common.spell.Spell;
import com.evilbird.warcraft.object.common.value.BuffValue;
import com.evilbird.warcraft.object.common.value.Value;
import com.evilbird.warcraft.object.common.value.ValueProperty;
import com.evilbird.warcraft.object.effect.EffectType;
import com.evilbird.warcraft.object.unit.combatant.Combatant;
import com.evilbird.warcraft.object.unit.combatant.SpellCaster;

import java.util.Collection;

/**
 * A base class for spells that modifies the attributes of a given game object.
 *
 * @author Blair Butterworth
 */
public abstract class BuffSpellAction extends SpellAction
{
    private BadgeType badge;
    private BuffSpellCancel cancel;

    public BuffSpellAction(
        Spell spell,
        EffectType effect,
        BadgeType badge,
        GameObjectFactory factory,
        BuffSpellCancel cancel)
    {
        super(spell, effect, factory);
        this.badge = badge;
        this.cancel = cancel;
    }

    @Override
    protected void initialize() {
        super.initialize();
        SpellCaster caster = (SpellCaster)getSubject();
        Combatant target = (Combatant)getTarget();
        setBadge(target);
        setBuff(target);
        setBuffCancel(caster, target);
    }

    protected void setBadge(Combatant target) {
        if (badge != null) {
            target.setEffect((Renderable)factory.get(badge));
        }
    }

    protected abstract Collection<ValueProperty> buffedProperties(Combatant target);

    protected void setBuff(Combatant target) {
        for (ValueProperty property: buffedProperties(target)) {
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

    protected void setBuffCancel(SpellCaster caster, Combatant target) {
        cancel.setItem(caster);
        cancel.setTarget(target);
        target.addAction(cancel, new GameTimer(spell.getEffectDuration()));
    }
}
