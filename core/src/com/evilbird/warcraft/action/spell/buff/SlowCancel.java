/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.spell.buff;

import com.evilbird.warcraft.item.common.value.ValueProperty;
import com.evilbird.warcraft.item.unit.combatant.Combatant;
import com.evilbird.warcraft.item.unit.gatherer.Gatherer;

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
