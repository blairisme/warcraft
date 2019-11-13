/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.layer;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.evilbird.engine.common.maps.TiledMapRenderer;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectContainer;
import com.evilbird.engine.object.GameObjectGroup;

/**
 * Instances of this class represent a rendered {@link GameObject} that spans the
 * game space. Usually layers are composed of a collection of smaller entities.
 *
 * @author Blair Butterworth
 */
public class Layer extends GameObjectGroup
{
    protected transient TiledMapTileLayer layer;
    protected transient OrthographicCamera camera;
    protected transient TiledMapRenderer renderer;

    public Layer() {
        renderer = new TiledMapRenderer();
    }

    @Override
    public void draw(Batch batch, float alpha) {
        if (getVisible()) {
            renderer.setBatch(batch);
            renderer.setView(getCamera());
            renderer.renderTileLayer(getLayer());
        }
    }

    public TiledMapTileLayer getLayer() {
        return layer;
    }

    public void setLayer(TiledMapTileLayer layer) {
        this.layer = layer;
        float width = layer.getWidth() * layer.getTileWidth();
        float height = layer.getHeight() * layer.getTileHeight();
        setSize(width, height);
    }

    protected OrthographicCamera getCamera() {
        if (camera == null){
            GameObjectContainer root = getRoot();
            Viewport viewport = root.getViewport();
            camera = (OrthographicCamera)viewport.getCamera();
        }
        return camera;
    }
}
