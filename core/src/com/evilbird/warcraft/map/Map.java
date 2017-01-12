package com.evilbird.warcraft.map;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.evilbird.warcraft.item.Item;

public class Map extends Item
{
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    public Map(AssetManager assets)
    {
        map = assets.get("data/levels/human/level1.tmx", TiledMap.class);
        renderer = new OrthogonalTiledMapRenderer(map);
    }

    @Override
    public void draw(Batch batch, float alpha)
    {
        Stage stage = getStage();
        Camera camera = stage.getCamera();

        int[] layers = new int[1];
        layers[0] = 0;

        batch.end();
        renderer.setView((OrthographicCamera)camera);
        renderer.render(layers);
        batch.begin();
    }
}
