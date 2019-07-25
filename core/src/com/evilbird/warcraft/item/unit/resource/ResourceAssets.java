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
import com.badlogic.gdx.graphics.Texture;
import com.evilbird.engine.common.assets.AssetBundle;
import com.evilbird.engine.common.assets.SyntheticTexture;
import com.evilbird.engine.common.audio.SoundEffect;
import com.evilbird.engine.common.collection.Maps;
import com.evilbird.warcraft.common.WarcraftContext;
import com.evilbird.warcraft.item.unit.UnitType;

import java.util.Map;

import static com.evilbird.engine.common.assets.SyntheticTextureParameters.withColour;
import static com.evilbird.engine.common.collection.Maps.of;
import static com.evilbird.engine.common.graphics.Colours.FOREST_GREEN;
import static com.evilbird.engine.common.text.CaseUtils.toSnakeCase;
import static com.evilbird.warcraft.item.unit.UnitDimensions.LARGE;

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
        register("selection", "selection_${size}", SyntheticTexture.class, withColour(FOREST_GREEN, LARGE));

        register("selected", "data/sounds/neutral/resource/${name}/selected/1.mp3");
        registerSequence("destroyed", "data/sounds/common/building/destroyed/", ".mp3", 3);
    }

    private static Map<String, String> assetPathVariables(UnitType type, WarcraftContext context) {
        return of("name", toSnakeCase(type.name()),
                "season", toSnakeCase(context.getAssetSet().name()),
                "size", LARGE);
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

    public SoundEffect getDestroyedSound() {
        return getSoundEffectSet("destroyed", 3);
    }

    public SoundEffect getSelectedSound() {
        return getSoundEffect("selected");
    }
}
