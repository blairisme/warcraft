/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.spell.attack;

import com.evilbird.engine.object.GameObjectFactory;
import com.evilbird.warcraft.action.common.remove.DeathAction;
import com.evilbird.warcraft.action.spell.SpellAction;
import com.evilbird.warcraft.item.common.value.AbsoluteBuffValue;
import com.evilbird.warcraft.item.common.value.Value;
import com.evilbird.warcraft.item.effect.EffectType;
import com.evilbird.warcraft.item.unit.Unit;

import javax.inject.Inject;

import static com.evilbird.engine.object.utility.GameObjectOperations.assignIfAbsent;
import static com.evilbird.warcraft.item.common.spell.Spell.UnholyArmour;

/**
 * A spell that surrounds a target with a unholy armour, making it invulnerable
 * for a period of time. This spell is cast instantaneously and requires that
 * the target lose a portion of its health to receive the armour.
 *
 * @author Blair Butterworth
 */
public class UnholyArmourSpell extends SpellAction
{
    private transient DeathAction death;
    private transient UnholyArmourCancel cancel;

    @Inject
    public UnholyArmourSpell(GameObjectFactory factory, DeathAction death, UnholyArmourCancel cancel) {
        super(UnholyArmour, EffectType.Spell, factory);
        this.death = death;
        this.cancel = cancel;
    }

    @Override
    protected void initialize() {
        super.initialize();
        Unit target = (Unit)getTarget();
        setHealth(target);
        setBuff(target);
        setNextAction(target);
    }

    private void setHealth(Unit target) {
        target.setHealth(Math.max(0, target.getHealth() - spell.getValue()));
    }

    private void setBuff(Unit target) {
        Value oldValue = target.getArmourValue();
        AbsoluteBuffValue newValue = new AbsoluteBuffValue(Integer.MAX_VALUE, oldValue);
        target.setArmour(newValue);
    }

    private void setNextAction(Unit target) {
        if (target.getHealth() == 0) {
            assignIfAbsent(target, death);
        } else {
            target.addAction(cancel, spell.getEffectDuration());
        }
    }
}
