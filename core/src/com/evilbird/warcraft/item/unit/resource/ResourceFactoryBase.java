/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.resource;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.game.GameFactory;
import com.evilbird.warcraft.item.unit.UnitType;
import com.evilbird.warcraft.state.WarcraftContext;
import org.apache.commons.lang3.Validate;

/**
 * A reusable class for creating {@link Resource}s, loading the
 * necessary assets and defining the appropriate attributes.
 *
 * @author Blair Butterworth
 */
public abstract class ResourceFactoryBase implements GameFactory<Resource>
{
    protected UnitType type;
    protected AssetManager manager;

    protected ResourceAssets assets;
    protected ResourceBuilder builder;

    public ResourceFactoryBase(AssetManager manager, UnitType type) {
        this.manager = manager;
        this.type = type;
    }

    @Override
    public void load(Identifier context) {
        Validate.isInstanceOf(WarcraftContext.class, context);
        load((WarcraftContext)context);
    }

    private void load(WarcraftContext context) {
        assets = new ResourceAssets(manager, type, context);
        builder = new ResourceBuilder(assets);
        assets.load();
    }

    @Override
    public void unload(Identifier context) {
        assets.unload();
    }
}
