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
 * A spell that creates an creature, an eye of kilrogg, that an move about the
 * world acting as a scout. The creature is temporary and will be disappear
 * after a period of time.
 *
 * @author Blair Butterworth
 */
public class EyeOfKilroggSpell extends SpellAction
{
    @Inject
    public EyeOfKilroggSpell(ItemFactory factory) {
        super(Spell.EyeOfKilrogg, EffectType.Spell, factory);
    }
}
