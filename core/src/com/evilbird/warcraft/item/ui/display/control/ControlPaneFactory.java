/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.ui.display.control;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.device.DeviceControls;
import com.evilbird.engine.game.GameFactory;
import com.evilbird.warcraft.common.WarcraftContext;

import javax.inject.Inject;

/**
 * Instances of this factory create {@link ControlPane ControlPanes}, loading
 * the appropriate assets for their correct display.
 *
 * @author Blair Butterworth.
 */
public class ControlPaneFactory implements GameFactory<ControlPane>
{
    private AssetManager manager;
    private ControlPaneAssets assets;
    private ControlPaneBuilder builder;

    @Inject
    public ControlPaneFactory(Device device) {
        this(device.getAssetStorage(), device.getDeviceControls());
    }

    public ControlPaneFactory(AssetManager manager, DeviceControls controls) {
        this.manager = manager;
    }

    @Override
    public ControlPane get(Identifier type) {
        return builder.newControlPane();
    }

    @Override
    public void load(Identifier context) {
        assets = new ControlPaneAssets(manager, (WarcraftContext)context);
        builder = new ControlPaneBuilder(assets);
        assets.load();
    }

    @Override
    public void unload(Identifier context) {
        assets.unload();
    }
}
