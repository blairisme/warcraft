/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.item.specialized.layer;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.evilbird.engine.item.ItemGroup;
import com.evilbird.engine.item.ItemRoot;

/**
 * Instances of this class represent a rendered item that spans the game space.
 * Usually layers are composed of a collection of smaller entities.
 *
 * @author Blair Butterworth
 */
public class Layer extends ItemGroup
{
    protected TiledMapTileLayer layer;
    protected OrthographicCamera camera;

    public void draw(Batch batch, float alpha) {
        OrthogonalTiledMapRenderer renderer = new OrthogonalTiledMapRenderer(null, batch);
        renderer.setView(getCamera());
        renderer.renderTileLayer(getLayer());
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
            ItemRoot root = getRoot();
            Viewport viewport = root.getViewport();
            camera = (OrthographicCamera)viewport.getCamera();
        }
        return camera;
    }
}
