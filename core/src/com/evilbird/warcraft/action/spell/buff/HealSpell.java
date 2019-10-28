/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.spell.buff;

import com.evilbird.engine.item.ItemFactory;
import com.evilbird.warcraft.action.spell.SpellAction;
import com.evilbird.warcraft.item.common.spell.Spell;
import com.evilbird.warcraft.item.effect.EffectType;
import com.evilbird.warcraft.item.unit.Unit;

import javax.inject.Inject;

import static com.evilbird.warcraft.item.common.spell.Spell.Heal;

/**
 * A spell that improves the health of a given game object.
 *
 * @author Blair Butterworth
 */
public class HealSpell extends SpellAction
{
    @Inject
    public HealSpell(ItemFactory factory) {
        super(Spell.Heal, EffectType.Heal, factory);
    }

    @Override
    protected void initialize() {
        super.initialize();

        Unit target = (Unit)getTarget();
        target.setHealth(Math.min(target.getHealthMaximum(), target.getHealth() + Heal.getValue()));
    }
}
