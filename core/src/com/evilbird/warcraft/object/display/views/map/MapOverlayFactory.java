/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.display.views.map;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.device.DeviceControls;
import com.evilbird.engine.game.GameContext;
import com.evilbird.engine.game.GameFactory;
import com.evilbird.warcraft.state.WarcraftContext;
import org.apache.commons.lang3.Validate;

import javax.inject.Inject;

/**
 * Instances of this factory create {@link MapOverlay MapOverlaya}, loading
 * the appropriate assets for their correct display.
 *
 * @author Blair Butterworth.
 */
public class MapOverlayFactory implements GameFactory<MapOverlay>
{
    private AssetManager manager;
    private DeviceControls controls;
    private MapOverlayAssets assets;
    private MapOverlayBuilder builder;

    @Inject
    public MapOverlayFactory(Device device) {
        this(device.getAssetStorage(), device.getDeviceControls());
    }

    public MapOverlayFactory(AssetManager manager, DeviceControls controls) {
        this.manager = manager;
        this.controls = controls;
    }

    @Override
    public MapOverlay get(Identifier type) {
        return builder.build();
    }

    @Override
    public void load(GameContext context) {
        Validate.isInstanceOf(WarcraftContext.class, context);
        load((WarcraftContext)context);
    }

    private void load(WarcraftContext context) {
        assets = new MapOverlayAssets(manager, context);
        builder = new MapOverlayBuilder(assets);
        assets.load();
    }

    @Override
    public void unload(GameContext context) {
        if (assets != null) {
            assets.unload();
        }
    }
}
