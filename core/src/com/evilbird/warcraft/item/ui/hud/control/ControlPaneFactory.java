/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.ui.hud.control;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.common.inject.AssetProvider;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.device.DeviceControls;
import com.evilbird.warcraft.item.unit.UnitFaction;

import javax.inject.Inject;

/**
 * Instances of this factory create {@link ControlPane ControlPanes}, loading
 * the appropriate assets for their correct display.
 *
 * @author Blair Butterworth.
 */
public class ControlPaneFactory implements AssetProvider<ControlPane>
{
    private ControlPaneAssets assets;
    private ControlPaneBuilder builder;

    @Inject
    public ControlPaneFactory(Device device) {
        this(device.getAssetStorage(), device.getDeviceControls());
    }

    public ControlPaneFactory(AssetManager manager, DeviceControls controls) {
        this.assets = new ControlPaneAssets(manager, UnitFaction.Human);
        this.builder = new ControlPaneBuilder(assets);
    }

    @Override
    public void load() {
        assets.load();
    }

    @Override
    public ControlPane get() {
        return builder.newControlPane();
    }
}
