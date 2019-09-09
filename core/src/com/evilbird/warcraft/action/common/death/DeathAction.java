/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.common.death;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.common.lang.Destroyable;
import com.evilbird.engine.common.lang.Selectable;
import com.evilbird.engine.common.time.GameTimer;
import com.evilbird.engine.events.Events;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemGroup;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.engine.item.spatial.ItemGraph;
import com.evilbird.engine.item.specialized.Viewable;
import com.evilbird.warcraft.action.common.remove.RemoveEvent;
import com.evilbird.warcraft.action.select.SelectEvent;
import com.evilbird.warcraft.item.unit.Unit;
import com.evilbird.warcraft.item.unit.combatant.Combatant;
import com.evilbird.warcraft.item.unit.combatant.RangedCombatant;
import org.apache.commons.lang3.Validate;

import javax.inject.Inject;

import static com.evilbird.engine.action.ActionConstants.ACTION_COMPLETE;
import static com.evilbird.engine.action.ActionConstants.ACTION_INCOMPLETE;
import static com.evilbird.warcraft.item.common.query.UnitOperations.isCombatant;
import static com.evilbird.warcraft.item.common.query.UnitOperations.isRanged;
import static com.evilbird.warcraft.item.unit.UnitAnimation.Death;
import static com.evilbird.warcraft.item.unit.UnitAnimation.Decompose;
import static com.evilbird.warcraft.item.unit.UnitSound.Die;

/**
 * Instances of this {@link Action} animate and remove an item after its been
 * killed.
 *
 * @author Blair Butterworth
 */
public class DeathAction extends BasicAction
{
    private static final float DECOMPOSE_TIME = 30;

    private Events events;
    private GameTimer deathTimer;
    private GameTimer decomposeTimer;

    @Inject
    public DeathAction(Events events) {
        this.events = events;
    }

    @Override
    public boolean act(float time) {
        if (! initialized()) {
            return initialize();
        }
        if (!deathTimer.complete() && deathTimer.advance(time)) {
            return decompose();
        }
        else if (deathTimer.complete() && decomposeTimer.advance(time)) {
            return remove();
        }
        return ACTION_INCOMPLETE;
    }

    @Override
    public void reset() {
        super.reset();
        deathTimer = null;
        decomposeTimer = null;
    }

    @Override
    public void restart() {
        super.restart();
        throw new UnsupportedOperationException();
    }

    @Override
    public void setItem(Item item) {
        Validate.isInstanceOf(Destroyable.class, item);
        super.setItem(item);
    }

    private boolean initialized() {
        return deathTimer != null;
    }

    private boolean initialize() {
        Destroyable subject = (Destroyable)getItem();
        initializeVisuals(subject);
        initializeSelection(subject);
        initializeGraph(subject);
        initializeTimers(subject);
        initializeAssociation(subject);
        return ACTION_INCOMPLETE;
    }

    private void initializeVisuals(Destroyable subject) {
        if (subject instanceof Viewable) {
            Viewable viewable = (Viewable)subject;
            viewable.setAnimation(Death);
            viewable.setSound(Die);
            viewable.setTouchable(Touchable.disabled);
            viewable.setZIndex(0);
        }
    }

    private void initializeSelection(Destroyable subject) {
        if (subject instanceof Selectable) {
            Selectable selectable = (Selectable)subject;
            selectable.setSelected(false);
            selectable.setSelectable(false);
            events.add(new SelectEvent(selectable, false));
        }
    }

    private void initializeGraph(Destroyable subject) {
        ItemRoot root = subject.getRoot();
        ItemGraph graph = root.getSpatialGraph();
        graph.removeOccupants(subject);
    }

    private void initializeAssociation(Destroyable subject) {
        if (isRanged(subject)) {
            RangedCombatant ranged = (RangedCombatant)subject;
            Item associatedItem = ranged.getAssociatedItem();
            if (associatedItem != null) {
                associatedItem.setVisible(false);
            }
        }
    }

    private void initializeTimers(Destroyable subject) {
        if (isCombatant(subject)) {
            deathTimer = new GameTimer(1);
            decomposeTimer = new GameTimer(DECOMPOSE_TIME);
        } else {
            deathTimer = new GameTimer(DECOMPOSE_TIME);
            decomposeTimer = new GameTimer(0);
        }
    }

    private boolean decompose() {
        Destroyable subject = (Destroyable)getItem();
        if (isCombatant(subject)) {
            Combatant combatant = (Combatant)subject;
            combatant.setAnimation(Decompose);
        }
        return ACTION_INCOMPLETE;
    }

    private boolean remove() {
        Destroyable subject = (Destroyable)getItem();
        remove(subject);

        if (subject instanceof Unit) {
            Unit unit = (Unit)subject;
            remove(unit.getAssociatedItem());
        }
        return ACTION_COMPLETE;
    }

    private void remove(Item item) {
        if (item != null) {
            ItemGroup parent = item.getParent();
            parent.removeItem(item);
            events.add(new RemoveEvent(item));
        }
    }
}
