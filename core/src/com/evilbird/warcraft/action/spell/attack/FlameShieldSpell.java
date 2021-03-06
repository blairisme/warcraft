/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.spell.attack;

import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectFactory;
import com.evilbird.engine.object.GameObjectGroup;
import com.evilbird.warcraft.action.spell.SpellAction;
import com.evilbird.warcraft.data.spell.Spell;
import com.evilbird.warcraft.object.effect.EffectType;
import com.evilbird.warcraft.object.unit.Unit;
import com.evilbird.warcraft.object.unit.UnitType;
import com.evilbird.warcraft.object.unit.conjured.flameshield.FlameShield;

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
        setEffect(target);
        setExpiry(caster, target);
    }

    private void setEffect(Unit target) {
        GameObjectGroup group = target.getParent();
        FlameShield flameShield = (FlameShield)factory.get(UnitType.FlameShield);
        flameShield.setTarget(target);

        group.addObject(flameShield);
        target.setEffect(flameShield);
    }

    private void setExpiry(GameObject caster, Unit target) {
        cancelAction.setSubject(caster);
        cancelAction.setTarget(target);
        target.addAction(cancelAction, spell.getEffectDuration());
    }
}
