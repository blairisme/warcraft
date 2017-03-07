package com.evilbird.warcraft.item.layer;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.evilbird.engine.common.function.Supplier;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemRoot;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class LayerCamera implements Supplier<OrthographicCamera>
{
    private Item item;

    public LayerCamera(Item item)
    {
        this.item = item;
    }

    @Override
    public OrthographicCamera get()
    {
        ItemRoot root = item.getRoot();
        Viewport viewport = root.getViewport();
        return (OrthographicCamera)viewport.getCamera();
    }
}
