/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.display.control.minimap;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;
import com.evilbird.engine.common.graphics.SpriteFrameBuffer;
import com.evilbird.engine.common.graphics.renderable.BaseRenderable;
import com.evilbird.engine.common.graphics.renderable.Renderable;
import com.evilbird.engine.common.maps.TiledMapRenderer;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectContainer;
import com.evilbird.engine.object.spatial.GameObjectGraph;
import com.evilbird.warcraft.object.layer.Layer;
import com.evilbird.warcraft.object.layer.LayerType;

import static com.badlogic.gdx.graphics.Pixmap.Format.RGBA8888;

/**
 * A minimap layer that displays game world terrain.
 *
 * @author Blair Butterworth
 */
public class MinimapTerrain extends BaseRenderable implements Renderable, Disposable
{
    private Texture terrain;
    private GameObjectContainer container;

    public MinimapTerrain(GameObjectContainer container) {
        this.container = container;
    }

    @Override
    public void dispose() {
        if (terrain != null) {
            terrain.dispose();
        }
    }

    @Override
    public void draw(Batch batch, float x, float y, float width, float height) {
        if (terrain != null) {
            batch.draw(terrain, x, y, width, height);
        }
    }

    @Override
    public void update(float time) {
        if (terrain == null) {
            terrain = generateTerrain();
        }
    }

    private Texture generateTerrain() {
        GameObjectGraph graph = container.getSpatialGraph();
        Vector2 graphSize = graph.getGraphSize();
        return generateTerrain((int)graphSize.x, (int)graphSize.y);
    }

    private Texture generateTerrain(int width, int height) {
        SpriteFrameBuffer frameBuffer = new SpriteFrameBuffer(RGBA8888, width, height, false);
        frameBuffer.begin();

        SpriteBatch spriteBatch = new SpriteBatch();
        spriteBatch.begin();

        renderTerrain(spriteBatch, width, height);

        spriteBatch.end();
        frameBuffer.end();

        terrain = frameBuffer.obtainTexture();

        frameBuffer.dispose();
        spriteBatch.dispose();

        return terrain;
    }

    private void renderTerrain(Batch batch, int width, int height) {
        OrthographicCamera camera = new OrthographicCamera(width, height);
        camera.setToOrtho(true, width, height);
        
        TiledMapRenderer renderer = new TiledMapRenderer();
        renderer.setBatch(batch);
        renderer.setView(camera);

        renderTerrain(renderer, LayerType.Map);
        renderTerrain(renderer, LayerType.Shore);
        renderTerrain(renderer, LayerType.Sea);
        renderTerrain(renderer, LayerType.Mountain);
        renderTerrain(renderer, LayerType.Forest);

        renderer.dispose();
    }
    
    private void renderTerrain(TiledMapRenderer renderer, LayerType type) {
        for (GameObject object: container.getObjects()) {
            if (object.getType() == type) {
                Layer terrain = (Layer)object;
                renderer.renderTileLayer(terrain.getLayer());
                return;
            }
        }
    }
}
