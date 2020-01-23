/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.unit.resource;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.evilbird.engine.assets.AssetBundle;
import com.evilbird.engine.assets.SyntheticTexture;
import com.evilbird.engine.audio.sound.Sound;
import com.evilbird.warcraft.object.unit.UnitType;
import com.evilbird.warcraft.state.WarcraftContext;

import java.util.Map;

import static com.evilbird.engine.assets.SyntheticTextureParameters.withColour;
import static com.evilbird.engine.common.collection.Maps.of;
import static com.evilbird.engine.common.graphics.Colours.FOREST_GREEN;
import static com.evilbird.engine.common.graphics.Colours.LIGHT_BLUE;
import static com.evilbird.engine.common.text.StringUtils.toSnakeCase;
import static com.evilbird.warcraft.object.unit.UnitSize.Large;

/**
 * Provides access to the assets that are required to display a
 * {@link Resource}, as well as any sound effects used by it.
 *
 * @author Blair Butterworth
 */
public class ResourceAssets extends AssetBundle
{
    public ResourceAssets(AssetManager manager, UnitType type, WarcraftContext context) {
        super(manager, assetPathVariables(type, context));

        register("base", "data/textures/neutral/resource/${season}/${name}.png");
        register("destruction", "data/textures/common/building/${season}/destroyed_site.png");
        register("selection", "selection_${size}", SyntheticTexture.class,
            withColour(FOREST_GREEN, Large.getDimensions()));
        register("highlight", "highlight_${size}", SyntheticTexture.class,
            withColour(LIGHT_BLUE, Large.getDimensions()));

        register("selected", "data/sounds/neutral/resource/${name}/selected/1.mp3");
        registerOptionalSequence("destroyed", "data/sounds/common/building/destroyed/", ".mp3", 3);
    }

    private static Map<String, String> assetPathVariables(UnitType type, WarcraftContext context) {
        return of("name", toSnakeCase(type.name()),
                "season", toSnakeCase(context.getAssetSet().name()),
                "size", Large.getLabel());
    }

    public Texture getGeneralTexture() {
        return getTexture("base");
    }

    public Texture getDestructionTexture() {
        return getTexture("destruction");
    }

    public Texture getSelectionTexture() {
        return getSyntheticTexture("selection");
    }

    public Texture getHighlightTexture() {
        return getSyntheticTexture("highlight");
    }

    public Sound getDestroyedSound() {
        return getSoundEffectSet("destroyed", 3);
    }

    public Sound getSelectedSound() {
        return getSoundEffect("selected");
    }
}
