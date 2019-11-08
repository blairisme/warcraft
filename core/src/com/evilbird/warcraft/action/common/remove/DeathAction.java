/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.common.remove;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.common.time.GameTimer;
import com.evilbird.engine.events.Events;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemFactory;
import com.evilbird.engine.item.ItemGroup;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.engine.item.spatial.ItemGraph;
import com.evilbird.engine.item.specialized.Viewable;
import com.evilbird.warcraft.action.selection.SelectEvent;
import com.evilbird.warcraft.common.WarcraftPreferences;
import com.evilbird.warcraft.item.common.capability.PerishableObject;
import com.evilbird.warcraft.item.common.capability.SelectableObject;
import com.evilbird.warcraft.item.unit.Unit;
import com.evilbird.warcraft.item.unit.UnitType;
import com.evilbird.warcraft.item.unit.combatant.RangedCombatant;
import org.apache.commons.lang3.Validate;

import javax.inject.Inject;
import java.util.Collection;
import java.util.Collections;

import static com.evilbird.engine.action.ActionConstants.ActionComplete;
import static com.evilbird.engine.action.ActionConstants.ActionIncomplete;
import static com.evilbird.engine.common.lang.Alignment.Center;
import static com.evilbird.warcraft.item.common.query.UnitOperations.isRanged;
import static com.evilbird.warcraft.item.effect.EffectType.Explosion;
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
    private static final float DEATH_TIME = 1;
    private static final float DECOMPOSE_TIME = 30;

    private Events events;
    private GameTimer deathTimer;
    private GameTimer decomposeTimer;
    private ItemFactory factory;
    private WarcraftPreferences preferences;

    @Inject
    public DeathAction(
        Events events,
        ItemFactory factory,
        WarcraftPreferences preferences)
    {
        this.events = events;
        this.factory = factory;
        this.preferences = preferences;
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
        return ActionIncomplete;
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
        Validate.isInstanceOf(PerishableObject.class, item);
        super.setItem(item);
    }

    private boolean initialized() {
        return deathTimer != null;
    }

    private boolean initialize() {
        PerishableObject subject = (PerishableObject)getItem();
        initializeVisuals(subject);
        initializeSelection(subject);
        initializeGraph(subject);
        initializeTimers(subject);
        initializeAssociation(subject);
        return ActionIncomplete;
    }

    private void initializeVisuals(PerishableObject subject) {
        if (subject instanceof Viewable) {
            assignDeathAnimation((Viewable)subject);
        }
        if (isFabricated(subject)) {
            assignExplosionEffect(subject);
        }
    }

    private boolean isFabricated(Item subject) {
        if (subject instanceof Unit) {
            Unit unit = (Unit)subject;
            UnitType type = (UnitType)unit.getType();
            return type.isSiege() || type.isBuilding();
        }
        return false;
    }

    private void assignDeathAnimation(Viewable subject) {
        if (subject.hasAnimation(Death)) {
            subject.setAnimation(Death);
            subject.setSound(Die, preferences.getEffectsVolume());
            subject.setTouchable(Touchable.disabled);
            subject.setZIndex(0);
        }
    }

    private void assignExplosionEffect(PerishableObject subject) {
        Item explosion = factory.get(Explosion);
        setTarget(explosion);

        Vector2 position = subject.getPosition(Center);
        explosion.setPosition(position, Center);

        ItemGroup parent = subject.getParent();
        parent.addItem(explosion);
    }

    private void initializeSelection(PerishableObject subject) {
        if (subject instanceof SelectableObject) {
            SelectableObject selectable = (SelectableObject)subject;
            selectable.setSelected(false);
            selectable.setSelectable(false);
            events.add(new SelectEvent(selectable, false));
        }
    }

    private void initializeGraph(PerishableObject subject) {
        ItemRoot root = subject.getRoot();
        ItemGraph graph = root.getSpatialGraph();
        graph.removeOccupants(subject);
    }

    private void initializeAssociation(PerishableObject subject) {
        if (isRanged(subject)) {
            RangedCombatant ranged = (RangedCombatant)subject;
            Item associatedItem = ranged.getAssociatedItem();
            if (associatedItem != null) {
                associatedItem.setVisible(false);
            }
        }
    }

    private void initializeTimers(PerishableObject subject) {
        if (isInstantlyDestroyable(subject)) {
            deathTimer = new GameTimer(DEATH_TIME);
            decomposeTimer = new GameTimer(0);
        }
        else  {
            deathTimer = new GameTimer(DEATH_TIME);
            decomposeTimer = new GameTimer(DECOMPOSE_TIME);
        }
    }

    private boolean isInstantlyDestroyable(Item subject) {
        if (subject instanceof Unit) {
            Unit unit = (Unit)subject;
            UnitType type = (UnitType)unit.getType();
            return type.isFlying() || type.isSiege() || type.isSpellCaster();
        }
        return false;
    }

    private boolean decompose() {
        Item item = getItem();
        if (isDecomposable(item)) {
            Unit unit = (Unit)item;
            unit.setAnimation(Decompose);
        }
        return ActionIncomplete;
    }

    private boolean isDecomposable(Item subject) {
        if (subject instanceof Unit) {
            Unit unit = (Unit)subject;
            UnitType type = (UnitType)unit.getType();
            return type.isMelee() || type.isRanged() || type.isNavalUnit();
        }
        return false;
    }

    private boolean remove() {
        Item subject = getItem();
        remove(subject);

        Item explosion = getTarget();
        remove(explosion);

        Collection<Item> associations = getAssociations(subject);
        remove(associations);

        return ActionComplete;
    }
    
    private void remove(Collection<Item> items) {
        for (Item item: items) {
            remove(item);
        }
    }

    private void remove(Item item) {
        if (item != null) {
            ItemGroup parent = item.getParent();
            parent.removeItem(item);
            events.add(new RemoveEvent(item));
        }
    }

    private Collection<Item> getAssociations(Item item) {
        if (item instanceof Unit) {
            Unit unit = (Unit)item;
            return unit.getAssociatedObjects();
        }
        return Collections.emptyList();
    }

}
