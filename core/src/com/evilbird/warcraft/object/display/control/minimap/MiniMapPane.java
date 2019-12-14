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
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectContainer;
import com.evilbird.engine.object.control.Table;
import com.evilbird.engine.object.spatial.GameObjectGraph;
import com.evilbird.engine.object.spatial.GameObjectNode;
import com.evilbird.warcraft.object.data.player.Player;
import com.evilbird.warcraft.object.layer.Layer;
import com.evilbird.warcraft.object.layer.LayerCell;
import com.evilbird.warcraft.object.layer.LayerType;
import com.evilbird.warcraft.object.layer.fog.Fog;
import com.evilbird.warcraft.object.unit.Unit;
import com.evilbird.warcraft.object.unit.UnitType;
import com.evilbird.warcraft.object.unit.building.Building;
import com.evilbird.warcraft.object.unit.combatant.Combatant;
import com.evilbird.warcraft.object.unit.resource.Resource;

import static com.evilbird.engine.object.utility.GameObjectPredicates.hasType;
import static com.evilbird.warcraft.object.display.HudControl.MinimapPane;
import static com.evilbird.warcraft.object.unit.UnitType.GoldMine;
import static com.evilbird.warcraft.object.unit.UnitType.OilPatch;

/**
 * Represents a user interface panel displayed near the top of the heads
 * up display control bar. Contains a zoomed out overview of the game world.
 *
 * @author Blair Butterworth
 */
public class MiniMapPane extends Table
{
    public MiniMapPane(Skin skin) {
        setSkin(skin);
        setSize(176, 136);
        setBackground("minimap-panel");
        setTouchable(Touchable.enabled);
        setIdentifier(MinimapPane);
        setType(MinimapPane);
    }

    public void initialize(GameObjectContainer container) {
        clearObjects();
        Image image = new Image(getMapTexture(container));
        Cell<Actor> cell = super.add(image);
        cell.width(176 - 40);
        cell.height(136 - 10);
        cell.pad(5, 20, 5, 20);
    }

    private Texture getMapTexture(GameObjectContainer container) {
        GameObjectGraph graph = container.getSpatialGraph();
        Fog fog = (Fog)container.find(hasType(LayerType.OpaqueFog));

        Pixmap pixmap = new Pixmap(graph.getNodeCountX(), graph.getNodeCountY(), Pixmap.Format.RGBA8888);
        for (int y = 0; y < graph.getNodeCountY(); y++) {
            for (int x = 0; x < graph.getNodeCountX(); x++) {
                if (fog.isRevealed(x, y)) {
                    GameObjectNode node = graph.getNode(x, y);
                    GameObject occupant = getOccupant(node);
                    pixmap.setColor(getColour(occupant));
                    pixmap.drawPixel(x, graph.getNodeCountY() - 1 - y);
                }
                else {
                    pixmap.setColor(Color.BLACK);
                    pixmap.drawPixel(x, graph.getNodeCountY() - 1 - y);
                }
            }
        }
        return new Texture(pixmap);
    }

    private GameObject getOccupant(GameObjectNode node) {
        GameObject result = null;
        for (GameObject occupant: node.getOccupants()) {
            if (getOrder(result) < getOrder(occupant)) {
                result = occupant;
            }
        }
        return result;
    }

    private int getOrder(GameObject object) {
        if (object instanceof Unit) {
            return 3;
        }
        if (object instanceof LayerCell) {
            return 2;
        }
        if (object instanceof Layer) {
            return 1;
        }
        return 0;
    }

    private Color getColour(GameObject object) {
        if (object instanceof Resource) {
            return getResourceColour(object);
        }
        if (object instanceof Combatant || object instanceof Building) {
            return getUnitColour((Unit)object);
        }
        if (object instanceof Layer || object instanceof LayerCell) {
            return getLayerColour(object);
        }
        return Color.BLACK;
    }

    private Color getLayerColour(GameObject layer) {
        LayerType type = (LayerType)layer.getType();

        if (type == LayerType.Map || type == LayerType.Shore) {
            return Color.WHITE;
        }
        if (type == LayerType.Sea) {
            return Color.BLUE;
        }
        if (type == LayerType.Tree) {
            return Color.GREEN;
        }
        if (type == LayerType.Mountain) {
            return Color.DARK_GRAY;
        }
        if (type == LayerType.WallSection) {
            return Color.LIGHT_GRAY;
        }
        return Color.BLACK;
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
        Player player = unit.getTeam();

        if (player.isNeutral()) {
            return Color.RED;
        }
        return player.getColour().getGdxColour();
    }
}
