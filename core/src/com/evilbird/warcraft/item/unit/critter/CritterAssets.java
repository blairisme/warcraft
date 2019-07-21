/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.critter;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.evilbird.engine.common.assets.AssetBundle;
import com.evilbird.engine.common.assets.SyntheticTexture;
import com.evilbird.engine.common.audio.SoundEffect;
import com.evilbird.engine.common.collection.Maps;
import com.evilbird.warcraft.item.unit.UnitType;

import java.util.Map;

import static com.evilbird.engine.common.assets.SyntheticTextureParameters.withColour;
import static com.evilbird.engine.common.graphics.Colours.FOREST_GREEN;
import static com.evilbird.engine.common.text.CaseUtils.toSnakeCase;
import static com.evilbird.warcraft.item.unit.UnitDimensions.EXTRA_SMALL;
import static com.evilbird.warcraft.item.unit.UnitDimensions.EXTRA_SMALL_NAME;

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

    public SoundEffect getDieSound() {
        return getSoundEffect("annoyed");
    }

    public SoundEffect getSelectedSound() {
        return getSoundEffect("selected");
    }
}
