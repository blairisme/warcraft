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
 * A spell that "transfers" health its target to the caster.
 *
 * @author Blair Butterworth
 */
public class DeathCoilSpell extends SpellAction
{
    @Inject
    public DeathCoilSpell(ItemFactory factory) {
        super(Spell.DeathCoil, EffectType.Spell, factory);
    }
}
