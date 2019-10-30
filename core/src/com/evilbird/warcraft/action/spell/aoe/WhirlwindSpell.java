/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.spell.aoe;

import com.evilbird.engine.item.ItemFactory;
import com.evilbird.warcraft.action.spell.SpellAction;
import com.evilbird.warcraft.item.common.spell.Spell;
import com.evilbird.warcraft.item.effect.EffectType;

import javax.inject.Inject;

/**
 * A spell that deals damage to all game objects in a given area as well as
 * stopping those objects from receiving commands.
 *
 * @author Blair Butterworth
 */
public class WhirlwindSpell extends SpellAction
{
    @Inject
    public WhirlwindSpell(ItemFactory factory) {
        super(Spell.Whirlwind, EffectType.Spell, factory);
    }
}
