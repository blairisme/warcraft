package com.evilbird.warcraft.item.hud.actionpane;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.evilbird.engine.common.inject.AssetProvider;
import com.evilbird.engine.device.Device;

import javax.inject.Inject;

import static com.evilbird.warcraft.item.common.texture.TextureUtils.getDrawable;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class ActionPaneProvider implements AssetProvider<ActionPane>
{
    private AssetManager assets;
    private ActionButtonProvider actionButtonProvider;

    @Inject
    public ActionPaneProvider(Device device, ActionButtonProvider actionButtonProvider) {
        this.assets = device.getAssetStorage().getAssets();
        this.actionButtonProvider = actionButtonProvider;
    }

    @Override
    public void load() {
        assets.load("data/textures/human/hud/action_panel.png", Texture.class);
    }

    @Override
    public ActionPane get() {
        ActionPane result = new ActionPane(actionButtonProvider);
        result.setBackground(getDrawable(assets, "data/textures/human/hud/action_panel.png"));
        return result;
    }
}
