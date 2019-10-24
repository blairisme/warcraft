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
 * A spell that increases the speed for a given targets movement and attack.
 * The spell is cast instantaneously and wears off after a period of time.
 *
 * @author Blair Butterworth
 */
public class HasteSpell extends SpellAction
{
    @Inject
    public HasteSpell(ItemFactory factory) {
        super(Spell.Haste, EffectType.Spell, factory);
    }
}
