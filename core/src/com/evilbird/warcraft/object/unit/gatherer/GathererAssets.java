/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.unit.gatherer;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.GridPoint2;
import com.evilbird.engine.common.assets.AssetBundle;
import com.evilbird.engine.common.assets.SyntheticTexture;
import com.evilbird.engine.common.audio.sound.Sound;
import com.evilbird.warcraft.object.unit.UnitType;

import java.util.Map;

import static com.evilbird.engine.common.assets.SyntheticTextureParameters.withColour;
import static com.evilbird.engine.common.collection.Maps.of;
import static com.evilbird.engine.common.graphics.Colours.FOREST_GREEN;
import static com.evilbird.engine.common.graphics.Colours.LIGHT_BLUE;
import static com.evilbird.engine.common.text.CaseUtils.toSnakeCase;
import static com.evilbird.warcraft.object.unit.UnitDimensions.getDimensionName;
import static com.evilbird.warcraft.object.unit.UnitDimensions.getDimensions;

/**
 * Provides access to the assets that are required to display a
 * {@link Gatherer}, as well as any sound effects used by it.
 *
 * @author Blair Butterworth
 */
public class GathererAssets extends AssetBundle
{
    private GridPoint2 dimensions;

    public GathererAssets(AssetManager manager, UnitType type) {
        super(manager, assetPathVariables(type));
        dimensions = getDimensions(type);

        registerTextures();
        registerSounds();
    }

    private void registerTextures() {
        register("base", "data/textures/${faction}/unit/${name}.png");
        registerOptional("mask", "data/textures/${faction}/unit/${name}_mask.png");
        register("decompose", "data/textures/common/unit/decompose.png");
        register("selection", "selection_${size}", SyntheticTexture.class, withColour(FOREST_GREEN, dimensions));
        register("highlight", "highlight_${size}", SyntheticTexture.class, withColour(LIGHT_BLUE, dimensions));

        registerOptional("moveWithGold", "data/textures/${faction}/unit/${name}_with_gold.png");
        registerOptional("moveWithWood", "data/textures/${faction}/unit/${name}_with_wood.png");
        registerOptional("moveWithOil", "data/textures/${faction}/unit/${name}_with_oil.png");
    }

    private void registerSounds() {
        register("construct", "data/sounds/common/unit/construct/1.mp3");
        register("complete", "data/sounds/${faction}/unit/${name}/complete/1.mp3");
        register("dead", "data/sounds/${faction}/unit/common/dead/1.mp3");
        register("ready", "data/sounds/${faction}/unit/${name}/ready/1.mp3");

        registerOptionalSequence("attack", "data/sounds/common/unit/attack/sword/", ".mp3", 3);
        registerOptionalSequence("chopping", "data/sounds/common/unit/chopping/", ".mp3", 4);
        registerOptionalSequence("acknowledge", "data/sounds/${faction}/unit/${name}/acknowledge/", ".mp3", 4);
        registerOptionalSequence("selected", "data/sounds/${faction}/unit/${name}/selected/", ".mp3", 6);
    }

    private static Map<String, String> assetPathVariables(UnitType type) {
        return of("name", toSnakeCase(type.name()),
                "faction", toSnakeCase(type.getFaction().name()),
                "size", getDimensionName(type));
    }

    public Texture getBaseTexture() {
        return getTexture("base");
    }

    public Texture getMaskTexture() {
        return getOptionalTexture("mask");
    }

    public Texture getDecomposeTexture() {
        return getTexture("decompose");
    }

    public Texture getMoveWithGoldTexture() {
        return getOptionalTexture("moveWithGold");
    }

    public Texture getMoveWithOilTexture() {
        return getOptionalTexture("moveWithOil");
    }

    public Texture getMoveWithWoodTexture() {
        return getOptionalTexture("moveWithWood");
    }

    public Texture getSelectionTexture() {
        return getSyntheticTexture("selection");
    }

    public Texture getHighlightTexture() {
        return getSyntheticTexture("highlight");
    }

    public Sound getChoppingSound() {
        return getSoundEffectSet("chopping", 4);
    }

    public Sound getSelectedSound() {
        return getSoundEffectSet("selected", 6);
    }

    public Sound getAcknowledgeSound() {
        return getSoundEffectSet("acknowledge", 4);
    }

    public Sound getAttackSound() {
        return getSoundEffectSet("attack", 3);
    }

    public Sound getCompleteSound() {
        return getSoundEffect("complete");
    }

    public Sound getConstructSound() {
        return getSoundEffect("construct");
    }

    public Sound getReadySound() {
        return getSoundEffect("ready");
    }

    public Sound getDeadSound() {
        return getSoundEffect("dead");
    }

    public GridPoint2 getSize() {
        return dimensions;
    }
}
