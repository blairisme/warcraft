/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.spell.buff;

import com.evilbird.engine.object.GameObjectFactory;
import com.evilbird.warcraft.action.spell.SpellAction;
import com.evilbird.warcraft.data.spell.Spell;
import com.evilbird.warcraft.object.effect.EffectType;

import javax.inject.Inject;

/**
 * A spell that "transfers" health its target to the caster.
 *
 * @author Blair Butterworth
 */
public class DeathCoilSpell extends SpellAction
{
    @Inject
    public DeathCoilSpell(GameObjectFactory factory) {
        super(Spell.DeathCoil, EffectType.Spell, factory);
    }

    @Override
    protected void initialize() {
        super.initialize();
    }
}
