/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.display.control;

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
 * Instances of this factory create {@link ControlPane ControlPanes}, loading
 * the appropriate assets for their correct display.
 *
 * @author Blair Butterworth.
 */
public class ControlPaneFactory implements GameFactory<ControlPane>
{
    private AssetManager manager;
    private DeviceControls controls;
    private ControlPaneAssets assets;
    private ControlPaneBuilder builder;

    @Inject
    public ControlPaneFactory(Device device) {
        this(device.getAssetStorage(), device.getDeviceControls());
    }

    public ControlPaneFactory(AssetManager manager, DeviceControls controls) {
        this.manager = manager;
        this.controls = controls;
    }

    @Override
    public ControlPane get(Identifier type) {
        if (controls.supportMenuOption()) {
            return builder.newCompactControlPane();
        }
        return builder.newControlPane();
    }

    @Override
    public void load(GameContext context) {
        Validate.isInstanceOf(WarcraftContext.class, context);
        load((WarcraftContext)context);
    }

    private void load(WarcraftContext context) {
        assets = new ControlPaneAssets(manager, context);
        builder = new ControlPaneBuilder(assets);
        assets.load();
    }

    @Override
    public void unload(GameContext context) {
        if (assets != null) {
            assets.unload();
        }
    }
}
