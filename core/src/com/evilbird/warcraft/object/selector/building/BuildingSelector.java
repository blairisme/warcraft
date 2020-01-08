/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.selector.building;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.events.Event;
import com.evilbird.engine.events.EventQueue;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectContainer;
import com.evilbird.engine.object.spatial.GameObjectGraph;
import com.evilbird.engine.object.spatial.GameObjectNode;
import com.evilbird.engine.object.utility.GameObjectOperations;
import com.evilbird.warcraft.action.common.create.CreateEvent;
import com.evilbird.warcraft.action.death.RemoveEvent;
import com.evilbird.warcraft.action.move.MoveEvent;
import com.evilbird.warcraft.object.selector.Selector;
import com.evilbird.warcraft.object.selector.SelectorType;
import com.evilbird.warcraft.object.unit.Unit;

import java.util.Collection;
import java.util.function.Predicate;

import static com.evilbird.engine.common.collection.CollectionUtils.containsAll;
import static com.evilbird.engine.common.collection.CollectionUtils.containsAny;
import static com.evilbird.engine.common.collection.CollectionUtils.containsEqual;
import static com.evilbird.engine.common.collection.CollectionUtils.flatten;
import static com.evilbird.engine.object.utility.GameObjectPredicates.hasType;
import static com.evilbird.warcraft.object.layer.LayerType.Map;
import static com.evilbird.warcraft.object.layer.LayerType.Sea;
import static com.evilbird.warcraft.object.layer.LayerType.Shore;
import static com.evilbird.warcraft.object.selector.SelectorType.DockyardSelector;
import static com.evilbird.warcraft.object.selector.SelectorType.FoundrySelector;
import static com.evilbird.warcraft.object.selector.SelectorType.MetalworksSelector;
import static com.evilbird.warcraft.object.selector.SelectorType.OilPlatformSelector;
import static com.evilbird.warcraft.object.selector.SelectorType.OilRefinerySelector;
import static com.evilbird.warcraft.object.selector.SelectorType.OilRigSelector;
import static com.evilbird.warcraft.object.selector.SelectorType.RefinerySelector;
import static com.evilbird.warcraft.object.selector.SelectorType.ShipyardSelector;
import static com.evilbird.warcraft.object.unit.UnitType.OilPatch;

/**
 * The visual representation of a building before construction, allowing the
 * user to move it to the correct location and ensure it doesn't overlap an
 * existing structure.
 *
 * @author Blair Butterworth
 */
public class BuildingSelector extends Selector
{
    private transient Skin skin;
    private transient Drawable building;
    private transient Drawable overlay;
    private transient Drawable allowed;
    private transient Drawable blocked;
    private transient boolean isClear;
    private transient EventQueue events;
    private transient SelectorType type;

    public BuildingSelector(Skin skin) {
        this.skin = skin;
        setStyle("default");
    }

    public boolean isClear() {
        return isClear;
    }

    public void setEvents(EventQueue events) {
        this.events = events;
    }

    public void setStyle(String name) {
        BuildingSelectorStyle style = skin.get(name, BuildingSelectorStyle.class);
        building = style.building;
        allowed = style.allowed;
        blocked = style.prohibited;
        overlay = isClear ? allowed : blocked;
    }

    @Override
    public void setRoot(GameObjectContainer root) {
        super.setRoot(root);
        evaluateOccupation(root);
    }

    public void setSize(GridPoint2 size) {
        super.setSize(size.x, size.y);
    }

    @Override
    public void setType(Identifier type) {
        super.setType(type);
        this.type = (SelectorType)type;
    }

    @Override
    public void positionChanged() {
        evaluateOccupation(getRoot());
    }

    @Override
    public void draw(Batch batch, float alpha) {
        float x = getX();
        float y = getY();
        float width = getWidth();
        float height = getHeight();
        building.draw(batch, x, y, width, height);
        overlay.draw(batch, x, y, width, height);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        evaluateEvents(events.getEvents(CreateEvent.class));
        evaluateEvents(events.getEvents(RemoveEvent.class));
        evaluateEvents(events.getEvents(MoveEvent.class));
    }

    private void evaluateEvents(Collection<? extends Event> events) {
        for (Event event: events) {
            if (GameObjectOperations.overlaps(this, event.getSubject())) {
                evaluateOccupation(getRoot());
            }
        }
    }

    private void evaluateOccupation(GameObjectContainer root) {
        if (root != null) {
            GameObjectGraph graph = root.getSpatialGraph();
            Collection<GameObjectNode> nodes = graph.getNodes(getPosition(), getSize());
            Collection<GameObject> gameObjects = flatten(nodes, GameObjectNode::getOccupants);
            isClear = isUnoccupied(gameObjects);
            overlay = isClear ? allowed : blocked;
        }
    }

    private boolean isUnoccupied(Collection<GameObject> gameObjects) {
        if (isOilPatchBased(type)) {
            return containsAll(gameObjects, hasType(OilPatch, Sea).or(hasSelector(this)))
                && containsEqual(gameObjects, hasType(OilPatch), hasType(Sea));
        }
        if (isShoreBased(type)) {
            return containsAll(gameObjects, hasType(Shore, Sea).or(hasSelector(this)))
                && containsAny(gameObjects, hasType(Shore));
        }
        return containsAll(gameObjects, hasType(Map).or(hasSelector(this)));
    }

    private boolean isShoreBased(SelectorType type) {
        return type == ShipyardSelector || type == RefinerySelector || type == FoundrySelector
            || type == DockyardSelector || type == OilRefinerySelector || type == MetalworksSelector;
    }

    private boolean isOilPatchBased(SelectorType type) {
        return type == OilPlatformSelector || type == OilRigSelector;
    }

    private Predicate<GameObject> hasSelector(Selector selector) {
        return object -> {
            if (object instanceof Unit) {
                Unit unit = (Unit)object;
                return unit.getSelector() == selector;
            }
            return false;
        };
    }
}
