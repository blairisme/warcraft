package com.evilbird.warcraft.item.layer;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.evilbird.engine.item.Item;

public class Map extends Item
{
    protected TiledMapTileLayer layer;

    public Map(TiledMapTileLayer layer)
    {
        this.layer = layer;
    }

    @Override
    public void draw(Batch batch, float alpha)
    {
        OrthogonalTiledMapRenderer renderer = new OrthogonalTiledMapRenderer(null, batch);
        renderer.setView(getCamera());
        renderer.renderTileLayer(layer);
    }

    private OrthographicCamera getCamera()
    {
        Stage stage = getStage();
        return (OrthographicCamera)stage.getCamera();
    }
}
