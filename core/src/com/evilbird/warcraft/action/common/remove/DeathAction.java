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
import com.evilbird.engine.object.AnimatedObject;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectContainer;
import com.evilbird.engine.object.GameObjectFactory;
import com.evilbird.engine.object.GameObjectGroup;
import com.evilbird.engine.object.spatial.GameObjectGraph;
import com.evilbird.warcraft.action.selection.SelectEvent;
import com.evilbird.warcraft.common.WarcraftPreferences;
import com.evilbird.warcraft.object.common.capability.PerishableObject;
import com.evilbird.warcraft.object.common.capability.SelectableObject;
import com.evilbird.warcraft.object.unit.Unit;
import com.evilbird.warcraft.object.unit.UnitType;
import com.evilbird.warcraft.object.unit.combatant.RangedCombatant;
import org.apache.commons.lang3.Validate;

import javax.inject.Inject;
import java.util.Collection;
import java.util.Collections;

import static com.evilbird.engine.action.ActionConstants.ActionComplete;
import static com.evilbird.engine.action.ActionConstants.ActionIncomplete;
import static com.evilbird.engine.common.lang.Alignment.Center;
import static com.evilbird.warcraft.object.common.query.UnitOperations.isRanged;
import static com.evilbird.warcraft.object.effect.EffectType.Explosion;
import static com.evilbird.warcraft.object.unit.UnitAnimation.Death;
import static com.evilbird.warcraft.object.unit.UnitAnimation.Decompose;
import static com.evilbird.warcraft.object.unit.UnitSound.Die;

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
    private GameObjectFactory factory;
    private WarcraftPreferences preferences;

    @Inject
    public DeathAction(
        Events events,
        GameObjectFactory factory,
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
    public void setItem(GameObject gameObject) {
        Validate.isInstanceOf(PerishableObject.class, gameObject);
        super.setItem(gameObject);
    }

    private boolean initialized() {
        return deathTimer != null;
    }

    private boolean initialize() {
        PerishableObject subject = (PerishableObject) getSubject();
        initializeVisuals(subject);
        initializeSelection(subject);
        initializeGraph(subject);
        initializeTimers(subject);
        initializeAssociation(subject);
        return ActionIncomplete;
    }

    private void initializeVisuals(PerishableObject subject) {
        if (subject instanceof AnimatedObject) {
            assignDeathAnimation((AnimatedObject)subject);
        }
        if (isFabricated(subject)) {
            assignExplosionEffect(subject);
        }
    }

    private boolean isFabricated(GameObject subject) {
        if (subject instanceof Unit) {
            Unit unit = (Unit)subject;
            UnitType type = (UnitType)unit.getType();
            return type.isSiege() || type.isBuilding();
        }
        return false;
    }

    private void assignDeathAnimation(AnimatedObject subject) {
        if (subject.hasAnimation(Death)) {
            subject.setAnimation(Death);
            subject.setSound(Die, preferences.getEffectsVolume());
            subject.setTouchable(Touchable.disabled);
            subject.setZIndex(0);
        }
    }

    private void assignExplosionEffect(PerishableObject subject) {
        GameObject explosion = factory.get(Explosion);
        setTarget(explosion);

        Vector2 position = subject.getPosition(Center);
        explosion.setPosition(position, Center);

        GameObjectGroup parent = subject.getParent();
        parent.addObject(explosion);
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
        GameObjectContainer root = subject.getRoot();
        GameObjectGraph graph = root.getSpatialGraph();
        graph.removeOccupants(subject);
    }

    private void initializeAssociation(PerishableObject subject) {
        if (isRanged(subject)) {
            RangedCombatant ranged = (RangedCombatant)subject;
            GameObject associatedGameObject = ranged.getAssociatedItem();
            if (associatedGameObject != null) {
                associatedGameObject.setVisible(false);
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

    private boolean isInstantlyDestroyable(GameObject subject) {
        if (subject instanceof Unit) {
            Unit unit = (Unit)subject;
            UnitType type = (UnitType)unit.getType();
            return type.isFlying() || type.isSiege() || type.isSpellCaster();
        }
        return false;
    }

    private boolean decompose() {
        GameObject gameObject = getSubject();
        if (isDecomposable(gameObject)) {
            Unit unit = (Unit) gameObject;
            unit.setAnimation(Decompose);
        }
        return ActionIncomplete;
    }

    private boolean isDecomposable(GameObject subject) {
        if (subject instanceof Unit) {
            Unit unit = (Unit)subject;
            UnitType type = (UnitType)unit.getType();
            return type.isMelee() || type.isRanged() || type.isNavalUnit();
        }
        return false;
    }

    private boolean remove() {
        GameObject subject = getSubject();
        remove(subject);

        GameObject explosion = getTarget();
        remove(explosion);

        Collection<GameObject> associations = getAssociations(subject);
        remove(associations);

        return ActionComplete;
    }
    
    private void remove(Collection<GameObject> gameObjects) {
        for (GameObject gameObject : gameObjects) {
            remove(gameObject);
        }
    }

    private void remove(GameObject gameObject) {
        if (gameObject != null) {
            GameObjectGroup parent = gameObject.getParent();
            parent.removeObject(gameObject);
            events.add(new RemoveEvent(gameObject));
        }
    }

    private Collection<GameObject> getAssociations(GameObject gameObject) {
        if (gameObject instanceof Unit) {
            Unit unit = (Unit) gameObject;
            return unit.getAssociatedObjects();
        }
        return Collections.emptyList();
    }

}
