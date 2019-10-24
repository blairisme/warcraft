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
 * A spell that creates a magical explosive trap that explodes when any type of
 * game object approaches it. The trap will eventually exploded it unused.
 *
 * @author Blair Butterworth
 */
public class RunesSpell extends SpellAction
{
    @Inject
    public RunesSpell(ItemFactory factory) {
        super(Spell.Runes, EffectType.Rune, factory);
    }
}
