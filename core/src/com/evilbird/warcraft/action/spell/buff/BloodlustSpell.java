/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.spell.buff;

import com.evilbird.engine.object.GameObjectFactory;
import com.evilbird.warcraft.data.spell.Spell;
import com.evilbird.warcraft.object.badge.BadgeType;
import com.evilbird.warcraft.object.common.value.ValueProperty;
import com.evilbird.warcraft.object.effect.EffectType;
import com.evilbird.warcraft.object.unit.combatant.Combatant;

import javax.inject.Inject;
import java.util.Collection;
import java.util.Collections;

/**
 * A spell that increases the attack damage of a given combatant. The spell is
 * cast instantaneously and its effects wear of after a period of time.
 *
 * @author Blair Butterworth
 */

public class BloodlustSpell extends BuffSpellAction
{
    @Inject
    public BloodlustSpell(GameObjectFactory factory, BloodlustCancel cancel) {
        super(Spell.Bloodlust, EffectType.Spell, BadgeType.BloodlustBadge, factory, cancel);
    }

    @Override
    protected Collection<ValueProperty> buffedProperties(Combatant target) {
        return Collections.singletonList(target.getAttackSpeedProperty());
    }
}
