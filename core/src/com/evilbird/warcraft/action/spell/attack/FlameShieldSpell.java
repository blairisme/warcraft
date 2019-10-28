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
 * A spell that surrounds a target with a flame shield, degrading the health of
 * any game objects adjacent to it. Flame shield is temporary effect that wears
 * off after a period of time.
 *
 * @author Blair Butterworth
 */
public class FlameShieldSpell extends SpellAction
{
    @Inject
    public FlameShieldSpell(ItemFactory factory) {
        super(Spell.FlameShield, EffectType.FlameShield, factory);
    }
}
