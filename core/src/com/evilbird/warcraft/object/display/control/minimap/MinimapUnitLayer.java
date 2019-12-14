/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.display.control.minimap;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;
import com.evilbird.engine.common.graphics.Colours;
import com.evilbird.engine.common.graphics.renderable.BaseRenderable;
import com.evilbird.engine.common.graphics.renderable.Renderable;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectContainer;
import com.evilbird.engine.object.spatial.GameObjectGraph;
import com.evilbird.engine.object.spatial.GameObjectNode;
import com.evilbird.warcraft.common.TeamColour;
import com.evilbird.warcraft.object.data.player.Player;
import com.evilbird.warcraft.object.layer.LayerType;
import com.evilbird.warcraft.object.layer.fog.Fog;
import com.evilbird.warcraft.object.unit.Unit;
import com.evilbird.warcraft.object.unit.UnitType;
import com.evilbird.warcraft.object.unit.building.Building;
import com.evilbird.warcraft.object.unit.combatant.Combatant;
import com.evilbird.warcraft.object.unit.resource.Resource;

import static com.badlogic.gdx.math.Vector2.Zero;
import static com.evilbird.engine.object.utility.GameObjectPredicates.hasType;
import static com.evilbird.warcraft.object.unit.UnitType.GoldMine;
import static com.evilbird.warcraft.object.unit.UnitType.OilPatch;

/**
 * A minimap layer that displays the positions and team colour of units.
 *
 * @author Blair Butterworth
 */
public class MinimapUnitLayer extends BaseRenderable implements Renderable, Disposable
{
    private Texture texture;
    private Pixmap pixmap;
    private GameObjectGraph graph;
    private Fog fog;

    public MinimapUnitLayer(GameObjectContainer container) {
        this.graph = container.getSpatialGraph();
        this.fog = (Fog)container.find(hasType(LayerType.OpaqueFog));
        this.pixmap = new Pixmap(graph.getNodeCountX(), graph.getNodeCountY(), Pixmap.Format.RGBA8888);
        this.pixmap.setBlending(Pixmap.Blending.None);
    }

    @Override
    public void dispose() {
        pixmap.dispose();
        texture.dispose();
    }

    @Override
    public void draw(Batch batch, float x, float y, float width, float height) {
        if (texture != null) {
            batch.draw(texture, x, y, width, height);
        }
    }

    public void invalidate(Vector2 position, Vector2 size) {
        GridPoint2 start = graph.toSpatial(position);
        GridPoint2 count = graph.toSpatial(size);
        update(start, count);
        if (texture != null) {
            this.texture.dispose();
        }
        this.texture = new Texture(pixmap);
    }

    @Override
    public void update(float time) {
        if (texture == null) {
            invalidate(Zero, graph.getGraphSize());
        }
    }

    private void update(GridPoint2 start, GridPoint2 count) {
        int startX = Math.max(start.x - 1, 0);
        int startY = Math.max(start.y - 1, 0);
        int endX = Math.min(start.x + count.x + 2, graph.getNodeCountX());
        int endY = Math.min(start.y + count.y + 2, graph.getNodeCountY());

        for (int y = startY; y < endY; y++) {
            for (int x = startX; x < endX; x++) {
                update(x, y);
            }
        }
    }

    private void update(int x, int y) {
        pixmap.setColor(fog.isRevealed(x, y) ? getColour(x, y) : Color.BLACK);
        pixmap.drawPixel(x, graph.getNodeCountY() - 1 - y);
    }

    private Color getColour(int x, int y) {
        GameObjectNode node = graph.getNode(x, y);
        GameObject occupant = getOccupant(node);
        return getColour(occupant);
    }

    private Color getColour(GameObject object) {
        if (object instanceof Resource) {
            return getResourceColour(object);
        }
        if (object instanceof Combatant || object instanceof Building) {
            return getUnitColour((Unit)object);
        }
        return Colours.TRANSPARENT;
    }

    private Color getResourceColour(GameObject resource) {
        UnitType type = (UnitType)resource.getType();

        if (type == GoldMine) {
            return Color.GOLD;
        }
        if (type == OilPatch) {
            return Color.NAVY;
        }
        return Color.BLACK;
    }

    private Color getUnitColour(Unit unit) {
        if (unit.getVisible() && unit.isAlive()) {
            Player player = unit.getTeam();
            TeamColour colour = player.getColour();
            return colour.getGdxColour();
        }
        return Colours.TRANSPARENT;
    }

    private GameObject getOccupant(GameObjectNode node) {
        for (GameObject occupant: node.getOccupants()) {
            if (occupant instanceof Unit) {
                return occupant;
            }
        }
        return null;
    }
}
