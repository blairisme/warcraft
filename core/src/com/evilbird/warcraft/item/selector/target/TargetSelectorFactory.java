/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.selector.target;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.game.GameContext;
import com.evilbird.engine.game.GameFactory;
import com.evilbird.warcraft.item.selector.SelectorType;
import com.evilbird.warcraft.state.WarcraftContext;
import org.apache.commons.lang3.Validate;

import javax.inject.Inject;

import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;

/**
 * A factory that creates {@link TargetSelector} objects, visual guides used
 * to select locations within the game world for use by subsequent actions.
 *
 * @author Blair Butterworth
 */
public class TargetSelectorFactory implements GameFactory<TargetSelector>
{
    private AssetManager manager;
    private TargetSelectorAssets assets;
    private TargetSelectorBuilder builder;

    @Inject
    public TargetSelectorFactory(Device device) {
        this(device.getAssetStorage());
    }

    public TargetSelectorFactory(AssetManager manager) {
        this.manager = manager;
    }

    @Override
    public void load(GameContext context) {
        Validate.isInstanceOf(WarcraftContext.class, context);
        load((WarcraftContext)context);
    }

    private void load(WarcraftContext context) {
        assets = new TargetSelectorAssets(manager);
        builder = new TargetSelectorBuilder(assets);
        assets.load();
    }

    @Override
    public void unload(GameContext context) {
        if (assets != null) {
            assets.unload();
        }
    }

    @Override
    public TargetSelector get(Identifier identifier) {
        Validate.isInstanceOf(SelectorType.class, identifier);
        return get((SelectorType)identifier);
    }

    private TargetSelector get(SelectorType type) {
        TargetSelector placeholder = builder.build();
        placeholder.setIdentifier(objectIdentifier("TargetSelector", placeholder));
        placeholder.setType(type);
        return placeholder;
    }
}
