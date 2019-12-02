/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.spell.attack;

import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectFactory;
import com.evilbird.warcraft.action.spell.SpellAction;
import com.evilbird.warcraft.object.common.spell.Spell;
import com.evilbird.warcraft.object.effect.EffectType;
import com.evilbird.warcraft.object.unit.Unit;
import com.evilbird.warcraft.object.unit.UnitType;
import com.evilbird.warcraft.object.unit.conjured.ConjuredObject;

import javax.inject.Inject;

/**
 * A spell that surrounds a target with a flame shield, degrading the health of
 * any game objects adjacent to it. Flame shield is temporary effect that wears
 * off after a period of time.
 *
 * @author Blair Butterworth
 */
public class FlameShieldSpell extends SpellAction
{
    private FlameShieldCancel cancelAction;

    @Inject
    public FlameShieldSpell(GameObjectFactory factory, FlameShieldCancel cancelAction) {
        super(Spell.FlameShield, EffectType.Spell, factory);
        this.cancelAction = cancelAction;
    }

    @Override
    protected void initialize() {
        super.initialize();
        addEffect();
    }

    private void addEffect() {
        GameObject caster = getSubject();
        Unit target = (Unit)getTarget();

        ConjuredObject flameShield = (ConjuredObject)factory.get(UnitType.FlameShield);
//        target.setEffect(flameShield); //TODO

        cancelAction.setSubject(caster);
        cancelAction.setTarget(flameShield);
        flameShield.addAction(cancelAction, spell.getEffectDuration());
    }
}
