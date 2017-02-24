package com.evilbird.warcraft.item.hud;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.utility.AssetObjectProvider;
import com.evilbird.engine.utility.Identifier;

import javax.inject.Inject;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class SelectionPanelProvider implements AssetObjectProvider<Item>
{
    private AssetManager assets;
    //private ActionFactory actionFactory;

    @Inject
    public SelectionPanelProvider(Device device)
    {
        this.assets = device.getAssetStorage().getAssets();
    }

    @Override
    public void load()
    {
        assets.load("data/textures/human/hud/selection_panel.png", Texture.class);
    }

    @Override
    public Item get()
    {
        SelectionPanel result = new SelectionPanel();
        result.setBackground(getBackground());
        result.setProperty(new Identifier("Id"), new Identifier("SelectionPanel"));
        //result.setActionFactory(actionFactory);
        return result;
    }

    private TextureRegion getBackground()
    {
        Texture texture = assets.get("data/textures/human/hud/selection_panel.png");
        TextureRegion region = new TextureRegion(texture);
        return region;
    }
}
