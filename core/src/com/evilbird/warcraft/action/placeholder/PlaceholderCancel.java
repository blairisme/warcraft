/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.placeholder;

import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.events.EventQueue;
import com.evilbird.engine.events.Events;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.action.common.scenario.ScenarioAction;
import com.evilbird.warcraft.item.unit.Unit;

import javax.inject.Inject;

import static com.evilbird.engine.action.common.ActionRecipient.Target;
import static com.evilbird.warcraft.action.common.associate.AssociateAction.unassociate;
import static com.evilbird.warcraft.action.common.remove.RemoveAction.remove;
import static com.evilbird.warcraft.action.placeholder.PlaceholderEvents.placeholderRemoved;

/**
 * Instances of this class stop the use of a placeholder, removing it from the
 * game.
 *
 * @author Blair Butterworth
 */
public class PlaceholderCancel extends ScenarioAction
{
    private transient Events events;

    @Inject
    public PlaceholderCancel(EventQueue events) {
        this.events = events;
        setIdentifier(PlaceholderActions.PlaceholderCancel);
    }

    @Override
    protected void steps(Identifier identifier) {
        setTarget(getConstruction());
        then(remove(Target));
        then(unassociate(), placeholderRemoved(events));
    }

    private Item getConstruction() {
        Unit unit = (Unit)getItem();
        return unit.getAssociatedItem();
    }
}
