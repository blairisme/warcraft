/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.unit.conjured;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.evilbird.engine.assets.AssetBundle;
import com.evilbird.engine.audio.sound.Sound;
import com.evilbird.warcraft.object.unit.UnitType;

import java.util.Map;

import static com.evilbird.engine.common.collection.Maps.of;
import static com.evilbird.engine.common.text.CaseUtils.toSnakeCase;

/**
 * Provides access to the assets that are required to display a effect game
 * objects.
 *
 * @author Blair Butterworth
 */
public class ConjuredAssets extends AssetBundle
{
    public ConjuredAssets(AssetManager assetManager, UnitType type) {
        super(assetManager, assetPathVariables(type));
        register("base", "data/textures/common/spell/${name}.png");
        register("background", "data/sounds/common/spell/${name}.mp3");
    }

    private static Map<String, String> assetPathVariables(UnitType type) {
        return of("name", toSnakeCase(type.name()));
    }

    public Texture getBaseTexture() {
        return getTexture("base");
    }

    public Sound getBaseSound() {
        return getSoundEffect("background");
    }
}
