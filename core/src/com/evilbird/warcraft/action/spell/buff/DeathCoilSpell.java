/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
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
