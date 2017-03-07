package com.evilbird.warcraft.item.layer;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.evilbird.engine.common.function.Supplier;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class LayerRenderer
{
    private TiledMapTileLayer layer;
    private OrthographicCamera camera;
    private Supplier<OrthographicCamera> cameraSupplier;

    public LayerRenderer(TiledMapTileLayer layer, Supplier<OrthographicCamera> cameraSupplier)
    {
        this.layer = layer;
        this.cameraSupplier = cameraSupplier;
    }

    public void draw(Batch batch, float alpha)
    {
        OrthogonalTiledMapRenderer renderer = new OrthogonalTiledMapRenderer(null, batch);
        renderer.setView(getCamera());
        renderer.renderTileLayer(layer);
    }

    private OrthographicCamera getCamera()
    {
        if (camera == null){
            camera = cameraSupplier.get();
        }
        return camera;
    }
}
