/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.hud.control.status;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.evilbird.engine.common.inject.AssetProvider;
import com.evilbird.engine.device.Device;
import com.evilbird.warcraft.item.hud.common.UnitPaneProvider;

import javax.inject.Inject;

import static com.evilbird.engine.common.graphics.TextureUtils.getDrawable;

/**
 * Instances of this factory crate {@link SelectionPane SelectionPanes}.
 *
 * @author Blair Butterworth
 */
public class SelectionPaneProvider implements AssetProvider<SelectionPane>
{
    private static final String BACKGROUND = "data/textures/human/hud/selection_panel.png";

    private AssetManager assets;
    private UnitPaneProvider tileProvider;

    @Inject
    public SelectionPaneProvider(Device device, UnitPaneProvider tileProvider) {
        this.assets = device.getAssetStorage();
        this.tileProvider = tileProvider;
    }

    @Override
    public void load() {
        assets.load(BACKGROUND, Texture.class);
    }

    @Override
    public SelectionPane get() {
        SelectionPane result = new SelectionPane(tileProvider);
        result.setBackground(getDrawable(assets, BACKGROUND));
        return result;
    }
}
