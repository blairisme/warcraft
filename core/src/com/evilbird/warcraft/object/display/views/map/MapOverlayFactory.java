/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.display.views.map;

import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.game.GameContext;
import com.evilbird.engine.game.GameFactory;

import javax.inject.Inject;

/**
 * Instances of this factory create {@link MapOverlay MapOverlaya}, loading
 * the appropriate assets for their correct display.
 *
 * @author Blair Butterworth.
 */
public class MapOverlayFactory implements GameFactory<MapOverlay>
{
    private MapOverlayBuilder builder;

    @Inject
    public MapOverlayFactory() {
        this.builder = new MapOverlayBuilder();
    }

    @Override
    public MapOverlay get(Identifier type) {
        return builder.build();
    }

    @Override
    public void load(GameContext context) {
    }

    @Override
    public void unload(GameContext context) {
    }
}
