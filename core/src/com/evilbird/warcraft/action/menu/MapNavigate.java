/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.menu;

import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.common.math.VectorUtils;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.object.GameObjectContainer;
import com.evilbird.engine.object.spatial.GameObjectGraph;
import com.evilbird.warcraft.action.camera.CameraAction;
import com.evilbird.warcraft.object.data.camera.Camera;
import com.evilbird.warcraft.object.display.components.map.MapPane;

import javax.inject.Inject;

import static com.evilbird.engine.action.ActionConstants.ActionComplete;

/**
 * A action that moves the camera to the location on the minimap selected by
 * the user.
 *
 * @author Blair Butterworth
 */
public class MapNavigate extends CameraAction
{
    @Inject
    public MapNavigate() {
        setIdentifier(MenuActions.MapNavigate);
    }

    @Override
    public boolean act(float delta) {
        MapPane mapPane = (MapPane)getSubject();

        Vector2 controlPosition = mapPane.getMapPosition();
        Vector2 selectPosition = getMapSelectPosition(mapPane);

        Vector2 mapPosition = selectPosition.sub(controlPosition);
        Vector2 mapSize = mapPane.getMapSize();
        Vector2 mapScale = VectorUtils.divide(mapPosition, mapSize);

        Vector2 worldSize = getWorldSize(mapPane).cpy();
        Vector2 worldPosition = worldSize.scl(mapScale);

        Camera camera = getCamera();
        camera.setPosition(worldPosition);

        return ActionComplete;
    }

    private Vector2 getMapSelectPosition(MapPane mapPane) {
        UserInput input = getCause();
        Vector2 inputPosition = input.getPosition();
        GameObjectContainer hudContainer = mapPane.getRoot();
        return hudContainer.unproject(inputPosition);
    }

    private Vector2 getWorldSize(MapPane mapPane) {
        GameObjectContainer worldContainer = getWorldContainer(mapPane);
        GameObjectGraph spatialGraph = worldContainer.getSpatialGraph();
        return spatialGraph.getGraphSize();
    }

}
