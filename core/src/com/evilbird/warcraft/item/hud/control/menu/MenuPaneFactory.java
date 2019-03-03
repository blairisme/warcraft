/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.hud.control.menu;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.evilbird.engine.common.inject.AssetProvider;
import com.evilbird.engine.device.Device;
import com.evilbird.warcraft.item.hud.control.minimap.MinimapPane;

import javax.inject.Inject;

import static com.evilbird.engine.common.graphics.TextureUtils.getDrawable;

public class MenuPaneFactory implements AssetProvider<MenuPane>
{
    private static final String BACKGROUND = "data/textures/human/hud/menu_panel.png";
    private AssetManager assets;

    @Inject
    public MenuPaneFactory(Device device) {
        this.assets = device.getAssetStorage().getAssets();
    }

    @Override
    public void load() {
        assets.load(BACKGROUND, Texture.class);
    }

    @Override
    public MenuPane get() {
        MenuPane result = new MenuPane();
        result.setBackground(getDrawable(assets, BACKGROUND));
        return result;
    }
}
