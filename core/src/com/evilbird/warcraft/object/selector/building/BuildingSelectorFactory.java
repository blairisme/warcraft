/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.selector.building;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.events.EventQueue;
import com.evilbird.engine.game.GameContext;
import com.evilbird.engine.game.GameFactory;
import com.evilbird.warcraft.object.selector.SelectorType;
import com.evilbird.warcraft.state.WarcraftContext;
import org.apache.commons.lang3.Validate;

import javax.inject.Inject;

import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;

/**
 * Instances of this factory create {@link BuildingSelector} objects, visual
 * representations of buildings before their construction.
 *
 * @author Blair Butterworth
 */
public class BuildingSelectorFactory implements GameFactory<BuildingSelector>
{
    private EventQueue events;
    private AssetManager manager;
    private BuildingSelectorAssets assets;
    private BuildingSelectorBuilder builder;

    @Inject
    public BuildingSelectorFactory(Device device, EventQueue events) {
        this(device.getAssetStorage(), events);
    }

    public BuildingSelectorFactory(AssetManager manager, EventQueue events) {
        this.events = events;
        this.manager = manager;
    }

    @Override
    public void load(GameContext context) {
        Validate.isInstanceOf(WarcraftContext.class, context);
        load((WarcraftContext)context);
    }

    private void load(WarcraftContext context) {
        assets = new BuildingSelectorAssets(manager, context);
        builder = new BuildingSelectorBuilder(assets);
        assets.load();
    }

    @Override
    public void unload(GameContext context) {
        if (assets != null) {
            assets.unload();
        }
    }

    @Override
    public BuildingSelector get(Identifier identifier) {
        Validate.isInstanceOf(SelectorType.class, identifier);
        return get((SelectorType)identifier);
    }

    private BuildingSelector get(SelectorType type) {
        BuildingSelector selector = builder.build(type);
        selector.setIdentifier(objectIdentifier("Placeholder", selector));
        selector.setType(type);
        selector.setEvents(events);
        return selector;
    }
}