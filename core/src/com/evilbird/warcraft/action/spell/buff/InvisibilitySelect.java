/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.spell.buff;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.events.Events;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.action.spell.SpellSelect;
import com.evilbird.warcraft.item.common.query.UnitOperations;
import com.evilbird.warcraft.item.common.spell.Spell;

import javax.inject.Inject;
import java.util.function.Predicate;

import static com.evilbird.engine.common.function.Predicates.both;

/**
 * An {@link Action} that highlights combatants belonging to the user to be the
 * recipient of the invisibility spell.
 *
 * @author Blair Butterworth
 */
public class InvisibilitySelect extends SpellSelect
{
    private static final Predicate<Item> CONDITION =
        both(UnitOperations::isControllable, UnitOperations::isCombatant);

    @Inject
    public InvisibilitySelect(Events events) {
        super(events, Spell.Invisibility, CONDITION);
    }
}
