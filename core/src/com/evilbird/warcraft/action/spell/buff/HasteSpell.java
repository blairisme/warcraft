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
import com.evilbird.warcraft.object.unit.combatant.gatherer.Gatherer;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.Collection;

/**
 * A spell that increases the speed for a given targets movement and attack.
 * The spell is cast instantaneously and wears off after a period of time.
 *
 * @author Blair Butterworth
 */
public class HasteSpell extends BuffSpellAction
{
    @Inject
    public HasteSpell(GameObjectFactory factory, HasteCancel cancel) {
        super(Spell.Haste, EffectType.Spell, BadgeType.HasteBadge, factory, cancel);
    }

    @Override
    protected Collection<ValueProperty> buffedProperties(Combatant target) {
        if (target instanceof Gatherer) {
            return gathererProperties((Gatherer)target);
        }
        return combatantProperties(target);
    }

    protected Collection<ValueProperty> combatantProperties(Combatant combatant) {
        return Arrays.asList(
            combatant.getAttackSpeedProperty(),
            combatant.getMovementSpeedProperty());
    }

    private Collection<ValueProperty> gathererProperties(Gatherer gatherer) {
        return Arrays.asList(
            gatherer.getAttackSpeedProperty(),
            gatherer.getMovementSpeedProperty(),
            gatherer.getGoldGatherSpeedProperty(),
            gatherer.getOilGatherSpeedProperty(),
            gatherer.getWoodGatherSpeedProperty());
    }
}
