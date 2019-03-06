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
 * Instances of this factory create {@link HealthBar HealthBars}.
 *
 * @author Blair Butterworth
 */
public class HealthBarProvider implements AssetProvider<HealthBar>
{
    private static final String BAR_HIGH = "data/textures/neutral/perennial/health_bar_high.png";
    private static final String BAR_MEDIUM = "data/textures/neutral/perennial/health_bar_medium.png";
    private static final String BAR_LOW = "data/textures/neutral/perennial/health_bar_low.png";
    private AssetManager assets;

    @Inject
    public HealthBarProvider(Device device) {
        this.assets = device.getAssetStorage();
    }

    @Override
    public void load() {
        assets.load(BAR_HIGH, Texture.class);
        assets.load(BAR_MEDIUM, Texture.class);
        assets.load(BAR_LOW, Texture.class);
    }

    @Override
    public HealthBar get() {
        HealthBar result = new HealthBar();
        result.setHighHealthTexture(getDrawable(assets, BAR_HIGH));
        result.setMediumHealthTexture(getDrawable(assets, BAR_MEDIUM));
        result.setLowHealthTexture(getDrawable(assets, BAR_LOW));
        return result;
    }
}
