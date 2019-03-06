/*
 * Blair Butterworth (c) 2018
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.hud.control.minimap;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.evilbird.engine.common.inject.AssetProvider;
import com.evilbird.engine.device.Device;

import javax.inject.Inject;

import static com.evilbird.engine.common.graphics.TextureUtils.getDrawable;

public class MinimapPaneProvider implements AssetProvider<MinimapPane>
{
    private static final String TEXTURE = "data/textures/human/hud/minimap_panel.png";
    private AssetManager assets;

    @Inject
    public MinimapPaneProvider(Device device) {
        this.assets = device.getAssetStorage();
    }

    @Override
    public void load() {
        assets.load(TEXTURE, Texture.class);
    }

    @Override
    public MinimapPane get() {
        MinimapPane result = new MinimapPane();
        result.setBackground(getDrawable(assets, TEXTURE));
        return result;
    }
}
