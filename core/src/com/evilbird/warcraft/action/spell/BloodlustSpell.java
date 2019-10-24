/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.spell;

import com.evilbird.engine.item.ItemFactory;
import com.evilbird.warcraft.item.common.spell.Spell;
import com.evilbird.warcraft.item.effect.EffectType;

import javax.inject.Inject;

/**
 * A spell that increases the attack damage of a given combatant. The spell is
 * cast instantaneously and its effects wear of after a period of time.
 *
 * @author Blair Butterworth
 */
public class BloodlustSpell extends SpellAction
{
    @Inject
    public BloodlustSpell(ItemFactory factory) {
        super(Spell.Bloodlust, EffectType.Spell, factory);
    }
}
