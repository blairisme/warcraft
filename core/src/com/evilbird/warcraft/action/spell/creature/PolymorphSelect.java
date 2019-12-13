/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.spell.creature;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.events.Events;
import com.evilbird.engine.object.GameObject;
import com.evilbird.warcraft.action.spell.SpellSelect;
import com.evilbird.warcraft.object.common.query.UnitOperations;
import com.evilbird.warcraft.data.spell.Spell;

import javax.inject.Inject;
import java.util.function.Predicate;

import static com.evilbird.engine.common.function.Predicates.both;
import static com.evilbird.engine.common.function.Predicates.not;

/**
 * An {@link Action} that highlights combatants belonging to the user to be the
 * recipient of the polymorph spell.
 *
 * @author Blair Butterworth
 */
public class PolymorphSelect extends SpellSelect
{
    private static final Predicate<GameObject> CONDITION =
        both(not(UnitOperations::isControllable), UnitOperations::isCombatant);

    @Inject
    public PolymorphSelect(Events events) {
        super(events, Spell.Polymorph, CONDITION);
    }
}
