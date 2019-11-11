/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.spell.buff;

import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectFactory;
import com.evilbird.warcraft.action.spell.SpellAction;
import com.evilbird.warcraft.item.common.spell.Spell;
import com.evilbird.warcraft.item.effect.EffectType;
import com.evilbird.warcraft.item.unit.Unit;
import com.evilbird.warcraft.item.unit.combatant.SpellCaster;

import javax.inject.Inject;

import static com.evilbird.warcraft.item.common.spell.Spell.Heal;

/**
 * A spell that improves the health of a given game object.
 *
 * @author Blair Butterworth
 */
public class HealSpell extends SpellAction
{
    private float healAmount;
    private float manaCost;

    @Inject
    public HealSpell(GameObjectFactory factory) {
        super(Spell.Heal, EffectType.Heal, factory);
    }

    @Override
    protected void clear() {
        super.clear();
        this.healAmount = 0;
        this.manaCost = 0;
    }

    @Override
    protected void initialize(SpellCaster caster, GameObject target) {
        this.healAmount = getHealAmount(caster, (Unit)target);
        this.manaCost = healAmount * Heal.getManaCost();
        super.initialize(caster, target);
    }

    @Override
    protected void initializeMana(SpellCaster caster, GameObject target, Spell spell) {
        caster.setMana(Math.max(0, caster.getMana() - manaCost));
    }

    @Override
    protected void initializeTarget(SpellCaster caster, GameObject gameObject) {
        Unit target = (Unit) gameObject;
        target.setHealth(Math.min(target.getHealthMaximum(), target.getHealth() + healAmount));
    }

    private float getHealAmount(SpellCaster caster, Unit target) {
        float possible = Math.round(caster.getMana() / Heal.getManaCost());
        float remaining = target.getHealthMaximum() - target.getHealth();
        return Math.min(possible, remaining);
    }
}
