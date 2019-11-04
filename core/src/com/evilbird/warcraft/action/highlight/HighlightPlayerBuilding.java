/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.highlight;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.events.Events;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.common.query.UnitOperations;

import javax.inject.Inject;
import java.util.function.Predicate;

import static com.evilbird.engine.common.function.Predicates.both;

/**
 *  An {@link Action} that highlights buildings belonging to the user.
 *
 * @author Blair Butterworth
 */
public class HighlightPlayerBuilding extends HighlightAction
{
    @Inject
    public HighlightPlayerBuilding(Events events) {
        super(events);
        setIdentifier(HighlightActions.HighlightPlayerBuilding);
    }

    @Override
    protected Predicate<Item> getCondition() {
        return both(UnitOperations::isControllable, UnitOperations::isBuilding);
    }
}
