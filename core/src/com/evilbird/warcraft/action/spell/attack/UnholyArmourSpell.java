/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.spell.attack;

import com.evilbird.engine.item.ItemFactory;
import com.evilbird.warcraft.action.spell.SpellAction;
import com.evilbird.warcraft.item.common.spell.Spell;
import com.evilbird.warcraft.item.effect.EffectType;

import javax.inject.Inject;

/**
 * A spell that surrounds a target with a unholy armour, making it invulnerable
 * for a period of time. This spell is cast instantaneously and requires that
 * the target lose a portion of its health to receive the armour.
 *
 * @author Blair Butterworth
 */
public class UnholyArmourSpell extends SpellAction
{
    @Inject
    public UnholyArmourSpell(ItemFactory factory) {
        super(Spell.UnholyArmour, EffectType.Spell, factory);
    }
}
