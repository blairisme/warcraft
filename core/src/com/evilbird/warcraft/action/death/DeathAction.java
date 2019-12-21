/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.death;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.common.time.GameTimer;
import com.evilbird.engine.object.AnimatedObject;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectContainer;
import com.evilbird.engine.object.GameObjectFactory;
import com.evilbird.engine.object.GameObjectGroup;
import com.evilbird.engine.object.spatial.GameObjectGraph;
import com.evilbird.warcraft.action.selection.SelectEvents;
import com.evilbird.warcraft.common.WarcraftPreferences;
import com.evilbird.warcraft.object.common.capability.PerishableObject;
import com.evilbird.warcraft.object.common.capability.SelectableObject;
import com.evilbird.warcraft.object.unit.Unit;
import org.apache.commons.lang3.Validate;

import javax.inject.Inject;
import java.util.Collection;
import java.util.Collections;

import static com.evilbird.engine.action.ActionConstants.ActionComplete;
import static com.evilbird.engine.action.ActionConstants.ActionIncomplete;
import static com.evilbird.engine.common.lang.Alignment.Center;
import static com.evilbird.warcraft.object.common.query.UnitOperations.isBuilding;
import static com.evilbird.warcraft.object.effect.EffectType.Explosion;
import static com.evilbird.warcraft.object.unit.UnitAnimation.Death;
import static com.evilbird.warcraft.object.unit.UnitSound.Die;

/**
 * Instances of this {@link Action} animate and remove an item after its been
 * killed.
 *
 * @author Blair Butterworth
 */
public class DeathAction extends BasicAction
{
    private static final float DECOMPOSE_TIME = 30;

    private GameTimer timer;
    private GameObjectFactory factory;
    private WarcraftPreferences preferences;
    private SelectEvents selectEvents;
    private RemoveEvents removeEvents;

    @Inject
    public DeathAction(
        SelectEvents selectEvents,
        RemoveEvents removeEvents,
        GameObjectFactory factory,
        WarcraftPreferences preferences)
    {
        this.factory = factory;
        this.preferences = preferences;
        this.selectEvents = selectEvents;
        this.removeEvents = removeEvents;
    }

    @Override
    public boolean act(float time) {
        if (! initialized()) {
            return initialize();
        }
        if (timer.advance(time)) {
            return remove();
        }
        return ActionIncomplete;
    }

    @Override
    public void reset() {
        super.reset();
        timer = null;
    }

    @Override
    public void restart() {
        super.restart();
        timer = null;
    }

    @Override
    public void setSubject(GameObject gameObject) {
        Validate.isInstanceOf(PerishableObject.class, gameObject);
        super.setSubject(gameObject);
    }

    private boolean initialized() {
        return timer != null;
    }

    private boolean initialize() {
        PerishableObject subject = (PerishableObject)getSubject();
        initializeStatus(subject);
        initializeVisuals(subject);
        initializeSelection(subject);
        initializeAssociation(subject);
        initializeGraph(subject);
        initializeTimer();
        return ActionIncomplete;
    }

    private void initializeStatus(PerishableObject subject) {
        subject.setHealth(0);
    }

    private void initializeVisuals(PerishableObject subject) {
        setDeathAnimation(subject);
        setExplosionEffect(subject);
    }

    private void setDeathAnimation(PerishableObject subject) {
        if (subject instanceof AnimatedObject) {
            AnimatedObject animated = (AnimatedObject) subject;
            if (animated.hasAnimation(Death)) {
                animated.setAnimation(Death);
                animated.setSound(Die, preferences.getEffectsVolume());
                animated.setZIndex(0);
            }
        }
    }

    private void setExplosionEffect(PerishableObject subject) {
        if (isBuilding(subject)) {
            GameObject explosion = factory.get(Explosion);
            setTarget(explosion);

            Vector2 position = subject.getPosition(Center);
            explosion.setPosition(position, Center);

            GameObjectGroup parent = subject.getParent();
            parent.addObject(explosion);
        }
    }

    private void initializeSelection(PerishableObject subject) {
        subject.setTouchable(Touchable.disabled);
        if (subject instanceof SelectableObject) {
            SelectableObject selectable = (SelectableObject)subject;
            selectable.setSelected(false);
            selectable.setSelectable(false);
            selectEvents.selectionUpdated(selectable, false);
        }
    }

    private void initializeGraph(PerishableObject subject) {
        GameObjectContainer root = subject.getRoot();
        GameObjectGraph graph = root.getSpatialGraph();
        graph.removeOccupants(subject);
    }

    private void initializeAssociation(PerishableObject subject) {
        for (GameObject association: getAssociations(subject)) {
            association.setVisible(false);
        }
    }

    private void initializeTimer() {
        timer = new GameTimer(DECOMPOSE_TIME);
    }

    private boolean remove() {
        remove(getSubject());
        remove(getTarget());
        remove(getAssociations());
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
            removeEvents.objectRemoved(gameObject);
        }
    }

    private Collection<GameObject> getAssociations() {
        return getAssociations(getSubject());
    }

    private Collection<GameObject> getAssociations(GameObject gameObject) {
        if (gameObject instanceof Unit) {
            Unit unit = (Unit) gameObject;
            return unit.getAssociatedObjects();
        }
        return Collections.emptyList();
    }
}