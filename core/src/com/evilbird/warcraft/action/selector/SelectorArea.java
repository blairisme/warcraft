/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.selector;

import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.events.EventQueue;
import com.evilbird.engine.events.Events;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectContainer;
import com.evilbird.warcraft.action.selection.SelectEvent;
import com.evilbird.warcraft.object.common.capability.SelectableObject;
import com.evilbird.warcraft.object.selector.selection.AreaSelector;
import com.evilbird.warcraft.object.unit.combatant.Combatant;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collection;

import static com.evilbird.engine.common.function.Predicates.both;
import static com.evilbird.engine.object.utility.GameObjectPredicates.overlapping;
import static com.evilbird.engine.object.utility.GameObjectPredicates.touchable;
import static com.evilbird.engine.object.utility.GameObjectPredicates.withType;
import static com.evilbird.warcraft.object.common.query.UnitPredicates.isSelected;
import static com.evilbird.warcraft.object.selector.SelectorType.AreaSelector;

/**
 * Instances of this {@link Action} manipulate a {@link AreaSelector}, a user
 * define graphical area that denotes the items the user wishes to select.
 *
 * @author Blair Butterworth
 */
public class SelectorArea extends BasicAction
{
    private transient Events events;

    @Inject
    public SelectorArea(EventQueue events) {
        this.events = events;
    }

    @Override
    public boolean act(float delta) {
        GameObject gameObject = getSubject();
        GameObjectContainer root = gameObject.getRoot();

        switch((SelectorActions)getIdentifier()) {
            case ShowAreaSelector: return addBox(root);
            case ResizeAreaSelector: return updateBox(root);
            case HideAreaSelector: return removeBox(root);
            default: throw new UnsupportedOperationException();
        }
    }

    private boolean addBox(GameObjectContainer root) {
        getBox(root);
        return true;
    }

    public GameObject getBox(GameObjectContainer root) {
        GameObject box = root.find(withType(AreaSelector));
        if (box == null) {
            UserInput input = getCause();
            box = new AreaSelector();
            box.setPosition(root.unproject(input.getPosition()));
            box.setSize(new Vector2(1, 1));
            root.addObject(box);
        }
        return box;
    }

    private boolean updateBox(GameObjectContainer root) {
        GameObject box = getBox(root);
        UserInput input = getCause();

        Vector2 point1 = root.unproject(input.getPosition());
        Vector2 point2 = root.unproject(input.getDelta());

        Vector2 position = new Vector2(Math.min(point1.x, point2.x), Math.min(point1.y, point2.y));
        Vector2 size = new Vector2(Math.abs(point1.x - point2.x), Math.abs(point1.y - point2.y));

        box.setPosition(position);
        box.setSize(size);

        return true;
    }

    private boolean removeBox(GameObjectContainer root) {
        GameObject box = getBox(root);
        root.removeObject(box);

        Collection<GameObject> overlapping = root.findAll(both(touchable(), overlapping(box)));
        Collection<GameObject> oldSelection = root.findAll(isSelected());
        Collection<GameObject> newSelection = new ArrayList<>(overlapping);

        newSelection.removeAll(oldSelection);
        oldSelection.removeAll(overlapping);

        select(newSelection);
        deselect(oldSelection);

        return true;
    }

    private void deselect(Collection<GameObject> gameObjects) {
        for (GameObject gameObject : gameObjects) {
            deselect(gameObject);
        }
    }

    private void deselect(GameObject gameObject) {
        if (gameObject instanceof SelectableObject) {
            SelectableObject selectable = (SelectableObject) gameObject;
            if (selectable.getSelectable() && selectable.getSelected()) {
                selectable.setSelected(false);
                events.add(new SelectEvent(selectable, false));
            }
        }
    }

    private void select(Collection<GameObject> gameObjects) {
        for (GameObject gameObject : gameObjects) {
            select(gameObject);
        }
    }

    private void select(GameObject gameObject) {
        if (gameObject instanceof Combatant) {
            Combatant selectable = (Combatant) gameObject;
            if (selectable.getSelectable() && !selectable.getSelected()) {
                selectable.setSelected(true);
                events.add(new SelectEvent(selectable, true));
            }
        }
    }
}
