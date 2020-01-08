/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.spell.buff;

import com.evilbird.warcraft.object.common.value.ValueProperty;
import com.evilbird.warcraft.object.unit.combatant.Combatant;
import com.evilbird.warcraft.object.unit.combatant.gatherer.Gatherer;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.Collection;

/**
 * A spell that removes the effects of the slow spell.
 *
 * @author Blair Butterworth
 */
public class SlowCancel extends BuffSpellCancel
{
    @Inject
    public SlowCancel() {
    }

    @Override
    protected Collection<ValueProperty> buffedProperties(Combatant target) {
        Gatherer gatherer = (Gatherer)target;
        return Arrays.asList(
            gatherer.getAttackSpeedProperty(),
            gatherer.getMovementSpeedProperty(),
            gatherer.getGoldGatherSpeedProperty(),
            gatherer.getOilGatherSpeedProperty(),
            gatherer.getWoodGatherSpeedProperty());
    }
}
