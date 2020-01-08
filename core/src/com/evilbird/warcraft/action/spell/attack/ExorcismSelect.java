/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.spell.attack;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.events.Events;
import com.evilbird.engine.object.GameObject;
import com.evilbird.warcraft.action.spell.SpellSelect;
import com.evilbird.warcraft.data.spell.Spell;
import com.evilbird.warcraft.object.common.query.UnitOperations;

import javax.inject.Inject;
import java.util.function.Predicate;

import static com.evilbird.engine.common.function.Predicates.both;
import static com.evilbird.engine.common.function.Predicates.not;

/**
 * An {@link Action} that highlights combatants belonging to the user to be the
 * recipient of the exorcism spell.
 *
 * @author Blair Butterworth
 */
public class ExorcismSelect extends SpellSelect
{
    private static final Predicate<GameObject> CONDITION =
        both(not(UnitOperations::isControllable), UnitOperations::isCombatant);

    @Inject
    public ExorcismSelect(Events events) {
        super(events, Spell.Exorcism, CONDITION);
    }
}
