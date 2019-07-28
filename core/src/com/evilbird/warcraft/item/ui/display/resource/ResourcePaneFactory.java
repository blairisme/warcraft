/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.ui.display.resource;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.game.GameContext;
import com.evilbird.engine.game.GameFactory;
import com.evilbird.warcraft.state.WarcraftContext;
import org.apache.commons.lang3.Validate;

import javax.inject.Inject;

/**
 * Instances of this factory create {@link ResourcePane ResourcePanes}.
 *
 * @author Blair Butterworth
 */
public class ResourcePaneFactory implements GameFactory<ResourcePane>
{
    private AssetManager manager;
    private ResourcePaneAssets assets;
    private ResourcePaneBuilder builder;

    @Inject
    public ResourcePaneFactory(Device device) {
        this(device.getAssetStorage());
    }

    public ResourcePaneFactory(AssetManager manager) {
        this.manager = manager;
    }

    @Override
    public ResourcePane get(Identifier type) {
        return builder.build();
    }

    @Override
    public void load(GameContext context) {
        Validate.isInstanceOf(WarcraftContext.class, context);
        load((WarcraftContext)context);
    }

    private void load(WarcraftContext context) {
        assets = new ResourcePaneAssets(manager, context);
        builder = new ResourcePaneBuilder(assets);
        assets.load();
    }

    @Override
    public void unload(GameContext context) {
        if (assets != null) {
            assets.unload();
        }
    }
}
