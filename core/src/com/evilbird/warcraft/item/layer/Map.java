package com.evilbird.warcraft.item.layer;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.item.Item;

import javax.inject.Inject;

public class Map extends Item
{
    private LayerRenderer layerRenderer;

    @Inject
    public Map()
    {
        setType(new Identifier("Map"));
    }

    public void setLayer(TiledMapTileLayer layer)
    {
        layerRenderer = new LayerRenderer(layer, new LayerCamera(this));
    }

    @Override
    public void draw(Batch batch, float alpha)
    {
        layerRenderer.draw(batch, alpha);
    }
}
