/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.display.views.resource;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.game.GameContext;
import com.evilbird.engine.game.GameFactory;
import com.evilbird.warcraft.state.WarcraftContext;
import org.apache.commons.lang3.Validate;

import javax.inject.Inject;

/**
 * Instances of this factory create {@link ResourceBar ResourcePanes}.
 *
 * @author Blair Butterworth
 */
public class ResourceBarFactory implements GameFactory<ResourceBar>
{
    private AssetManager manager;
    private ResourceBarAssets assets;
    private ResourceBarBuilder builder;

    @Inject
    public ResourceBarFactory(Device device) {
        this(device.getAssetStorage());
    }

    public ResourceBarFactory(AssetManager manager) {
        this.manager = manager;
    }

    @Override
    public ResourceBar get(Identifier type) {
        return builder.build();
    }

    @Override
    public void load(GameContext context) {
        Validate.isInstanceOf(WarcraftContext.class, context);
        load((WarcraftContext)context);
    }

    private void load(WarcraftContext context) {
        assets = new ResourceBarAssets(manager, context);
        builder = new ResourceBarBuilder(assets);
        assets.load();
    }

    @Override
    public void unload(GameContext context) {
        if (assets != null) {
            assets.unload();
        }
    }
}
