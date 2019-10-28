/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.spell;

import com.evilbird.warcraft.item.common.value.ValueProperty;
import com.evilbird.warcraft.item.unit.combatant.Combatant;

import javax.inject.Inject;
import java.util.Collection;
import java.util.Collections;

/**
 * A spell that removes the effects of the bloodlust spell from a given combatant.
 *
 * @author Blair Butterworth
 */
public class BloodlustSpellCancel extends BuffSpellCancel
{
    @Inject
    public BloodlustSpellCancel() {
    }

    @Override
    protected Collection<ValueProperty> buffedProperties(Combatant target) {
        return Collections.singletonList(target.getAttackSpeedProperty());
    }
}
