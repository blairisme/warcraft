/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.hud.common;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.evilbird.engine.common.inject.AssetProvider;
import com.evilbird.engine.device.Device;

import javax.inject.Inject;

import static com.evilbird.engine.common.graphics.TextureUtils.getDrawable;

/**
 * Instances of this factory create {@link UnitPane UnitPanes}.
 *
 * @author Blair Butterworth
 */
public class UnitPaneProvider implements AssetProvider<UnitPane>
{
    private static final String BACKGROUND = "data/textures/neutral/perennial/selection.png";
    private AssetManager assets;
    private HealthBarProvider healthBarProvider;

    @Inject
    public UnitPaneProvider(Device device, HealthBarProvider healthBarProvider) {
        this.assets = device.getAssetStorage();
        this.healthBarProvider = healthBarProvider;
    }

    @Override
    public void load() {
        assets.load(BACKGROUND, Texture.class);
    }

    @Override
    public UnitPane get() {
        UnitPane result = new UnitPane(healthBarProvider);
        result.setBackground(getDrawable(assets, BACKGROUND));
        return result;
    }
}
