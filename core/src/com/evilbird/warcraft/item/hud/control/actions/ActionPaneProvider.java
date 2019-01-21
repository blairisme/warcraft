/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.hud.control.actions;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.evilbird.engine.common.inject.AssetProvider;
import com.evilbird.engine.device.Device;

import javax.inject.Inject;

import static com.evilbird.warcraft.item.common.texture.TextureUtils.getDrawable;

/**
 * Instances of this factory create {@link ActionPane ActionPanes}.
 *
 * @author Blair Butterworth
 */
public class ActionPaneProvider implements AssetProvider<ActionPane>
{
    private static final String BACKGROUND = "data/textures/human/hud/action_panel.png";

    private AssetManager assets;
    private ActionButtonProvider actionButtonProvider;

    @Inject
    public ActionPaneProvider(Device device, ActionButtonProvider actionButtonProvider) {
        this.assets = device.getAssetStorage().getAssets();
        this.actionButtonProvider = actionButtonProvider;
    }

    @Override
    public void load() {
        assets.load(BACKGROUND, Texture.class);
    }

    @Override
    public ActionPane get() {
        ActionPane result = new ActionPane(actionButtonProvider);
        result.setBackground(getDrawable(assets, BACKGROUND));
        return result;
    }
}
