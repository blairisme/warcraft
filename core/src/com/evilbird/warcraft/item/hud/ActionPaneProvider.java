package com.evilbird.warcraft.item.hud;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.evilbird.engine.common.inject.AssetObjectProvider;
import com.evilbird.engine.device.Device;

import javax.inject.Inject;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class ActionPaneProvider implements AssetObjectProvider<ActionPane>
{
    private AssetManager assets;
    private ActionButtonProvider actionButtonProvider;

    @Inject
    public ActionPaneProvider(
        Device device,
        ActionButtonProvider actionButtonProvider)
    {
        this.assets = device.getAssetStorage().getAssets();
        this.actionButtonProvider = actionButtonProvider;
    }

    @Override
    public void load()
    {
        assets.load("data/textures/human/hud/action_panel.png", Texture.class);
    }

    @Override
    public ActionPane get()
    {
        ActionPane result = new ActionPane(actionButtonProvider);
        result.setBackground(getBackground());
        return result;
    }

    private Drawable getBackground()
    {
        Texture texture = assets.get("data/textures/human/hud/action_panel.png");
        TextureRegion region = new TextureRegion(texture);
        Drawable drawable = new TextureRegionDrawable(region);
        return drawable;
    }
}
