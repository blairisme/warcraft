/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.placeholder;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.evilbird.engine.events.Event;
import com.evilbird.engine.events.EventQueue;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemBasic;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.engine.item.spatial.ItemGraph;
import com.evilbird.engine.item.spatial.ItemNode;
import com.evilbird.engine.item.utility.ItemOperations;
import com.evilbird.warcraft.action.common.create.CreateEvent;
import com.evilbird.warcraft.action.common.remove.RemoveEvent;
import com.evilbird.warcraft.action.move.MoveEvent;
import com.evilbird.warcraft.item.layer.LayerType;

import java.util.Collection;

/**
 * The visual representation of a building before construction, allowing the
 * user to move it to the correct location and ensure it doesn't overlap an
 * existing structure.
 *
 * @author Blair Butterworth
 */
public class Placeholder extends ItemBasic
{
    private transient Skin skin;
    private transient Drawable building;
    private transient Drawable overlay;
    private transient Drawable allowed;
    private transient Drawable blocked;
    private transient boolean isClear;
    private transient EventQueue events;

    public Placeholder(Skin skin) {
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
        PlaceholderStyle style = skin.get(name, PlaceholderStyle.class);
        building = style.building;
        allowed = style.allowed;
        blocked = style.prohibited;
        overlay = isClear ? allowed : blocked;
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
    public void setRoot(ItemRoot root) {
        super.setRoot(root);
        evaluateOccupation(root);
    }

    @Override
    public void positionChanged() {
        evaluateOccupation(getRoot());
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
            if (ItemOperations.overlaps(this, event.getSubject())) {
                evaluateOccupation(getRoot());
            }
        }
    }

    private void evaluateOccupation(ItemRoot root) {
        if (root != null) {
            ItemGraph graph = root.getSpatialGraph();
            Collection<ItemNode> nodes = graph.getNodes(getPosition(), getSize());

            isClear = nodes.stream().allMatch(this::isUnoccupied);
            overlay = isClear ? allowed : blocked;
        }
    }

    private boolean isUnoccupied(ItemNode node) {
        Collection<Item> occupants = node.getOccupants();
        if (occupants.size() == 1) {
            Item occupant = occupants.iterator().next();
            return occupant.getType() == LayerType.Map;
        }
        return false;
    }
}
