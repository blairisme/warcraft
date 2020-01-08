/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.display.views.control;

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
        builder.showActions(true);
        builder.showMenuOption(! controls.supportsMenuOption());
        builder.showMiniMap(controls.supportsMiniMap());
        builder.showStatus(true);
        return builder.build();
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
