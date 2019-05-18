/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.placeholder;

import com.evilbird.warcraft.action.common.scenario.ScenarioAction;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.unit.gatherer.Gatherer;

import javax.inject.Inject;

import static com.evilbird.engine.action.common.ActionRecipient.Target;
import static com.evilbird.warcraft.action.common.remove.RemoveAction.remove;
import static com.evilbird.warcraft.action.placeholder.PlaceholderEvents.placeholderRemoved;
import static com.evilbird.warcraft.item.common.query.UnitPredicates.unassignConstruction;

/**
 * Instances of this class stop the use of a placeholder, removing it from the
 * game.
 *
 * @author Blair Butterworth
 */
public class PlaceholderCancel extends ScenarioAction
{
    private PlaceholderReporter reporter;

    @Inject
    public PlaceholderCancel(PlaceholderReporter reporter) {
        this.reporter = reporter;
        setIdentifier(PlaceholderActions.PlaceholderCancel);
    }

    @Override
    protected void steps(Identifier identifier) {
        withTarget(getConstruction());
        then(remove(Target));
        then(unassignConstruction(), placeholderRemoved(reporter));
    }

    private Item getConstruction() {
        Gatherer gatherer = (Gatherer)getItem();
        return gatherer.getConstruction();
    }
}
