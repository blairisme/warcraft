/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
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
