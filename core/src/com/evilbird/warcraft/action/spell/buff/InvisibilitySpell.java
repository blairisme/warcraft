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

import javax.inject.Inject;

/**
 * A spell that renders its target invisible to enemy players. The target
 * remains invisible until it moves or attacks. The spell is cast
 * instantaneously and won't be repeated until cast again.
 *
 * @author Blair Butterworth
 */
public class InvisibilitySpell extends SpellAction
{
    @Inject
    public InvisibilitySpell(ItemFactory factory) {
        super(Spell.Invisibility, EffectType.Spell, factory);
    }
}
