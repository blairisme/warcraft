/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.unit.critter;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.evilbird.engine.assets.AssetBundle;
import com.evilbird.engine.assets.SyntheticTexture;
import com.evilbird.engine.audio.sound.Sound;
import com.evilbird.engine.common.collection.Maps;
import com.evilbird.warcraft.object.unit.UnitType;

import java.util.Map;

import static com.evilbird.engine.assets.SyntheticTextureParameters.withColour;
import static com.evilbird.engine.common.graphics.Colours.FOREST_GREEN;
import static com.evilbird.engine.common.graphics.Colours.LIGHT_BLUE;
import static com.evilbird.engine.common.text.CaseUtils.toSnakeCase;
import static com.evilbird.warcraft.object.unit.UnitDimensions.EXTRA_SMALL;
import static com.evilbird.warcraft.object.unit.UnitDimensions.EXTRA_SMALL_NAME;

/**
 * Defines the assets that are required to display a {@link Critter}, as well
 * as any sound effects used by it.
 *
 * @author Blair Butterworth
 */
public class CritterAssets extends AssetBundle
{
    public CritterAssets(AssetManager assetManager, UnitType type) {
        super(assetManager, assetPathVariables(type));
        register("base", "data/textures/neutral/unit/${name}.png");
        register("decompose", "data/textures/common/unit/decompose.png");
        register("annoyed", "data/sounds/neutral/unit/${name}/annoyed/1.mp3");
        register("selected", "data/sounds/neutral/unit/${name}/selected/1.mp3");
        register("selection", "selection_${size}", SyntheticTexture.class, withColour(FOREST_GREEN, EXTRA_SMALL));
        register("highlight", "highlight_${size}", SyntheticTexture.class, withColour(LIGHT_BLUE, EXTRA_SMALL));
    }

    private static Map<String, String> assetPathVariables(UnitType type) {
        return Maps.of("name", toSnakeCase(type.name()), "size", EXTRA_SMALL_NAME);
    }

    public Texture getBaseTexture() {
        return getTexture("base");
    }

    public Texture getDecomposeTexture() {
        return getTexture("decompose");
    }

    public Texture getSelectionTexture() {
        return getSyntheticTexture("selection");
    }

    public Texture getHighlightTexture() {
        return getSyntheticTexture("highlight");
    }

    public Sound getDieSound() {
        return getSoundEffect("annoyed");
    }

    public Sound getSelectedSound() {
        return getSoundEffect("selected");
    }
}
