/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.spell.attack;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.events.Events;
import com.evilbird.engine.object.GameObject;
import com.evilbird.warcraft.action.spell.SpellSelect;
import com.evilbird.warcraft.object.common.query.UnitOperations;
import com.evilbird.warcraft.data.spell.Spell;

import javax.inject.Inject;
import java.util.function.Predicate;

import static com.evilbird.engine.common.function.Predicates.both;

/**
 * An {@link Action} that highlights combatants belonging to the user to be the
 * recipient of the flame shield spell.
 *
 * @author Blair Butterworth
 */
public class FlameShieldSelect extends SpellSelect
{
    private static final Predicate<GameObject> CONDITION =
        both(UnitOperations::isControllable, UnitOperations::isCombatant);

    @Inject
    public FlameShieldSelect(Events events) {
        super(events, Spell.FlameShield, CONDITION);
    }
}
