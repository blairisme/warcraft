/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.spell.aoe;

import com.evilbird.engine.object.GameObjectFactory;
import com.evilbird.warcraft.object.common.spell.Spell;
import com.evilbird.warcraft.object.effect.EffectType;
import com.evilbird.warcraft.object.unit.UnitType;

import javax.inject.Inject;

/**
 * A spell that creates a magical explosive trap that explodes when any type of
 * game object approaches it. The trap will eventually exploded it unused.
 *
 * @author Blair Butterworth
 */
public class RunesSpell extends AoeSpellAction
{
    @Inject
    public RunesSpell(GameObjectFactory factory, AoeSpellCancel cancel) {
        super(Spell.Runes, EffectType.Spell, UnitType.RuneTrap, factory, cancel);
    }
}
