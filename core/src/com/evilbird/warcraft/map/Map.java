package com.evilbird.warcraft.map;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.evilbird.warcraft.item.Item;

public class Map extends Item
{
    private OrthogonalTiledMapRenderer renderer;
    private int[] renderedLayers;

    public Map(TiledMap map)
    {
        renderer = new OrthogonalTiledMapRenderer(map);
        renderedLayers = new int[1];
        renderedLayers[0] = 0;
    }

    @Override
    public void draw(Batch batch, float alpha)
    {
        batch.end();
        renderer.setView(getCamera());
        renderer.render(renderedLayers);
        batch.begin();
    }

    private OrthographicCamera getCamera()
    {
        Stage stage = getStage();
        return (OrthographicCamera)stage.getCamera();
    }
}
