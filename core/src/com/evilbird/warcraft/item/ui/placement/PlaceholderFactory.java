/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.ui.placement;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.events.EventQueue;
import com.evilbird.engine.game.GameContext;
import com.evilbird.engine.game.GameFactory;
import com.evilbird.warcraft.state.WarcraftContext;
import org.apache.commons.lang3.Validate;

import javax.inject.Inject;

import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;

/**
 * Instances of this factory create {@link Placeholder Placeholders}, visual
 * representations of buildings before their construction.
 *
 * @author Blair Butterworth
 */
public class PlaceholderFactory implements GameFactory<Placeholder>
{
    private EventQueue events;
    private AssetManager manager;
    private PlaceholderAssets assets;
    private PlaceholderBuilder builder;

    @Inject
    public PlaceholderFactory(Device device, EventQueue events) {
        this(device.getAssetStorage(), events);
    }

    public PlaceholderFactory(AssetManager manager, EventQueue events) {
        this.events = events;
        this.manager = manager;
    }

    @Override
    public void load(GameContext context) {
        Validate.isInstanceOf(WarcraftContext.class, context);
        load((WarcraftContext)context);
    }

    private void load(WarcraftContext context) {
        assets = new PlaceholderAssets(manager, context);
        builder = new PlaceholderBuilder(assets);
        assets.load();
    }

    @Override
    public void unload(GameContext context) {
        if (assets != null) {
            assets.unload();
        }
    }

    @Override
    public Placeholder get(Identifier identifier) {
        Validate.isInstanceOf(PlaceholderType.class, identifier);
        return get((PlaceholderType)identifier);
    }

    private Placeholder get(PlaceholderType type) {
        Placeholder placeholder = builder.build(type);
        placeholder.setIdentifier(objectIdentifier("Placeholder", placeholder));
        placeholder.setType(type);
        placeholder.setEvents(events);
        return placeholder;
    }

}