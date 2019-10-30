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
 * A spell that deals damage to all game objects in a given area.
 *
 * @author Blair Butterworth
 */
public class BlizzardSpell extends SpellAction
{
    @Inject
    public BlizzardSpell(ItemFactory factory) {
        super(Spell.Blizzard, EffectType.Blizzard, factory);
    }
}
