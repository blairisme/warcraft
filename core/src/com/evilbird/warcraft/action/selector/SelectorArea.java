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
import com.evilbird.engine.action.ActionResult;
import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.common.collection.CollectionUtils;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.events.EventQueue;
import com.evilbird.engine.events.Events;
import com.evilbird.engine.game.GameController;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectContainer;
import com.evilbird.engine.object.GameObjectContainerType;
import com.evilbird.engine.state.State;
import com.evilbird.warcraft.action.selection.SelectEvent;
import com.evilbird.warcraft.object.common.capability.SelectableObject;
import com.evilbird.warcraft.object.selector.selection.AreaSelector;
import com.evilbird.warcraft.object.unit.combatant.Combatant;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Predicate;

import static com.evilbird.engine.action.ActionResult.Complete;
import static com.evilbird.engine.common.function.Predicates.both;
import static com.evilbird.engine.object.utility.GameObjectPredicates.overlapping;
import static com.evilbird.engine.object.utility.GameObjectPredicates.touchable;
import static com.evilbird.engine.object.utility.GameObjectPredicates.withType;
import static com.evilbird.warcraft.object.common.query.UnitPredicates.isCorporeal;
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
    private static transient final Predicate<GameObject> SelectableUnits =
        both(isCorporeal(), touchable());

    private transient Events events;

    @Inject
    public SelectorArea(EventQueue events) {
        this.events = events;
    }

    @Override
    public ActionResult act(float delta) {
        GameObjectContainer container = getWorldContainer();
        switch((SelectorActions)getIdentifier()) {
            case ShowAreaSelector: return addBox(container);
            case ResizeAreaSelector: return updateBox(container);
            case HideAreaSelector: return removeBox(container);
            default: throw new UnsupportedOperationException();
        }
    }

    private ActionResult addBox(GameObjectContainer container) {
        getBox(container);
        return Complete;
    }

    public GameObject getBox(GameObjectContainer container) {
        GameObject box = container.find(withType(AreaSelector));
        if (box == null) {
            UserInput input = getCause();
            Vector2 screenPosition = input.getPosition();
            Vector2 worldPosition = container.unproject(screenPosition);

            box = new AreaSelector();
            box.setPosition(worldPosition);
            box.setSize(new Vector2(1, 1));
            container.addObject(box);
        }
        return box;
    }

    private ActionResult updateBox(GameObjectContainer container) {
        GameObject box = getBox(container);
        UserInput input = getCause();

        Vector2 point1 = container.unproject(input.getPosition());
        Vector2 point2 = container.unproject(input.getDelta());

        Vector2 position = new Vector2(Math.min(point1.x, point2.x), Math.min(point1.y, point2.y));
        Vector2 size = new Vector2(Math.abs(point1.x - point2.x), Math.abs(point1.y - point2.y));

        box.setPosition(position);
        box.setSize(size);

        return Complete;
    }

    private ActionResult removeBox(GameObjectContainer container) {
        GameObject box = getBox(container);
        container.removeObject(box);

        Collection<GameObject> overlapping = container.findAll(overlapping(box));
        overlapping = CollectionUtils.filter(overlapping, SelectableUnits);

        Collection<GameObject> oldSelection = container.findAll(isSelected());
        Collection<GameObject> newSelection = new ArrayList<>(overlapping);

        newSelection.removeAll(oldSelection);
        oldSelection.removeAll(overlapping);

        select(newSelection);
        deselect(oldSelection);

        return Complete;
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

    private GameObjectContainer getWorldContainer() {
        GameObject subject = getSubject();
        GameObjectContainer root = subject.getRoot();
        if (root.getIdentifier() != GameObjectContainerType.World) {
            GameController controller = root.getController();
            State state = controller.getState();
            root = state.getWorld();
        }
        return root;
    }
}
