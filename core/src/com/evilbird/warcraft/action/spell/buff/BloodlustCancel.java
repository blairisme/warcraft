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

import javax.inject.Inject;
import java.util.Collection;
import java.util.Collections;

/**
 * A spell that removes the effects of the bloodlust spell from a given combatant.
 *
 * @author Blair Butterworth
 */
public class BloodlustCancel extends BuffSpellCancel
{
    @Inject
    public BloodlustCancel() {
    }

    @Override
    protected Collection<ValueProperty> buffedProperties(Combatant target) {
        return Collections.singletonList(target.getAttackSpeedProperty());
    }
}
