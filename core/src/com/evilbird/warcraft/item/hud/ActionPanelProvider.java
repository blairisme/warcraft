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
public class ActionPanelProvider implements AssetObjectProvider<Item>
{
    private AssetManager assets;
    private ActionTileProvider actionTileProvider;

    @Inject
    public ActionPanelProvider(
        Device device,
        ActionTileProvider actionTileProvider)
    {
        this.assets = device.getAssetStorage().getAssets();
        this.actionTileProvider = actionTileProvider;
    }

    @Override
    public void load()
    {
        assets.load("data/textures/human/hud/action_panel.png", Texture.class);
    }

    @Override
    public Item get()
    {
        ActionPanel result = new ActionPanel(actionTileProvider);
        result.setBackground(getBackground());
        result.setProperty(new Identifier("Id"), new Identifier("ActionPanel"));
        return result;
    }

    private TextureRegion getBackground()
    {
        Texture texture = assets.get("data/textures/human/hud/action_panel.png");
        TextureRegion region = new TextureRegion(texture);
        return region;
    }
}
