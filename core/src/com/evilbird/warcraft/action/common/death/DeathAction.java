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
import com.evilbird.engine.common.time.GameTimer;
import com.evilbird.engine.events.Events;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemGroup;
import com.evilbird.warcraft.action.common.remove.RemoveEvent;
import com.evilbird.warcraft.action.select.SelectEvent;
import com.evilbird.warcraft.item.unit.Unit;
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
        Validate.isInstanceOf(Unit.class, item);
        super.setItem(item);
    }

    private boolean initialized() {
        return deathTimer != null;
    }

    private boolean initialize() {
        Unit subject = (Unit)getItem();
        initializeVisuals(subject);
        initializeTimers(subject);
        initializeAssociation(subject);
        return ACTION_INCOMPLETE;
    }

    private void initializeVisuals(Unit subject) {
        subject.setAnimation(Death);
        subject.setSound(Die);
        subject.setSelected(false);
        subject.setSelectable(false);
        subject.setTouchable(Touchable.disabled);
        subject.setZIndex(0);
        events.add(new SelectEvent(subject, false));
    }

    private void initializeAssociation(Unit subject) {
        if (isRanged(subject)) {
            Item associatedItem = subject.getAssociatedItem();
            if (associatedItem != null) {
                associatedItem.setVisible(false);
            }
        }
    }

    private void initializeTimers(Unit subject) {
        if (isCombatant(subject)) {
            deathTimer = new GameTimer(1);
            decomposeTimer = new GameTimer(DECOMPOSE_TIME);
        } else {
            deathTimer = new GameTimer(DECOMPOSE_TIME);
            decomposeTimer = new GameTimer(0);
        }
    }

    private boolean decompose() {
        Unit subject = (Unit)getItem();
        if (isCombatant(subject)) {
            subject.setAnimation(Decompose);
        }
        return ACTION_INCOMPLETE;
    }

    private boolean remove() {
        Unit subject = (Unit)getItem();
        remove(subject.getAssociatedItem());
        remove(subject);
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
