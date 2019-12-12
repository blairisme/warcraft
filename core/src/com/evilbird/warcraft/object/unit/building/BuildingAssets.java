/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.unit.building;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.GridPoint2;
import com.evilbird.engine.common.assets.AssetBundle;
import com.evilbird.engine.common.assets.SyntheticTexture;
import com.evilbird.engine.common.audio.sound.Sound;
import com.evilbird.warcraft.object.unit.UnitType;
import com.evilbird.warcraft.state.WarcraftContext;

import java.util.Map;

import static com.evilbird.engine.common.assets.SyntheticTextureParameters.withColour;
import static com.evilbird.engine.common.collection.Maps.of;
import static com.evilbird.engine.common.graphics.Colours.FOREST_GREEN;
import static com.evilbird.engine.common.graphics.Colours.LIGHT_BLUE;
import static com.evilbird.engine.common.text.CaseUtils.toSnakeCase;
import static com.evilbird.warcraft.object.unit.UnitDimensions.getDimensionName;
import static com.evilbird.warcraft.object.unit.UnitDimensions.getDimensions;
import static com.evilbird.warcraft.object.unit.UnitType.BombardTower;
import static com.evilbird.warcraft.object.unit.UnitType.CannonTower;
import static com.evilbird.warcraft.object.unit.UnitType.GuardTower;
import static com.evilbird.warcraft.object.unit.UnitType.LookoutTower;

/**
 * Provides access to the assets that are required to display a
 * {@link Building}, as well as any sound effects used by it.
 *
 * @author Blair Butterworth
 */
public class BuildingAssets extends AssetBundle
{
    private UnitType type;
    private GridPoint2 dimensions;

    /**
     * Constructs a new instance of this class given an {@link AssetManager}
     * from which assets will be obtained and the {@link UnitType} whose assets
     * will be obtained.
     *
     * @param manager   an asset manager used to load and unload assets.
     * @param type      the type of unit whose assets will be loaded and
     *                  unloaded.
     * @param context   a {@link WarcraftContext} whose season will determine
     *                  the type of textures returned by the asset bundle.
     *
     * @throws IllegalArgumentException if either the given asset manager or
     *                                  unit type is {@code null}.
     */
    public BuildingAssets(AssetManager manager, UnitType type, WarcraftContext context) {
        super(manager, assetPathVariables(type, context));
        this.type = type;
        this.dimensions = getDimensions(type);
        registerTextures();
        registerSounds();
    }

    private static Map<String, String> assetPathVariables(UnitType type, WarcraftContext context) {
        return of("faction", toSnakeCase(type.getFaction().name()),
                "season", toSnakeCase(context.getAssetSet().name()),
                "name", toSnakeCase(type.name()),
                "size", getDimensionName(type));
    }

    private void registerTextures() {
        register("base", "data/textures/${faction}/building/${season}/${name}.png");
        registerOptional("mask", "data/textures/${faction}/building/${season}/${name}_mask.png");
        register("construction", "data/textures/common/building/perennial/construction_${size}.png");
        registerOptional("construction", "data/textures/common/building/perennial/${name}_construction_site.png");
        register("destruction", "data/textures/common/building/winter/destroyed_site.png");
        register("fire", "data/textures/common/environmental/fire.png");
        register("flame", "data/textures/common/environmental/flame.png");
        register("selection", "selection_${size}", SyntheticTexture.class, withColour(FOREST_GREEN, dimensions));
        register("highlight", "highlight_${size}", SyntheticTexture.class, withColour(LIGHT_BLUE, dimensions));
    }

    private void registerSounds() {
        register("selected", "data/sounds/common/building/selected/1.mp3");
        register("placement", "data/sounds/common/building/placement/1.mp3");
        register("burning", "data/sounds/common/explosion/burning.mp3");
        registerSequence("destroyed", "data/sounds/common/building/destroyed/", ".mp3", 3);

        if (type == GuardTower || type == LookoutTower) {
            register("attack", "data/sounds/common/unit/attack/bow/1.mp3");
        }
        if (type == CannonTower || type == BombardTower) {
            register("attack", "data/sounds/common/unit/attack/siege/1.mp3");
        }
    }

    public Texture getBaseTexture() {
        return getTexture("base");
    }

    public Texture getMaskTexture() {
        return getOptionalTexture("mask");
    }

    public Texture getConstructionTexture() {
        return getTexture("construction");
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

    public Texture getLightDamageTexture() {
        return getTexture("flame");
    }

    public Texture getHeavyDamageTexture() {
        return getTexture("fire");
    }

    public Sound getAttackSound() {
        return getOptionalSoundEffect("attack");
    }

    public Sound getBurningSound() {
        return getSoundEffect("burning");
    }

    public Sound getDestroyedSound() {
        return getSoundEffectSet("destroyed", 3);
    }

    public Sound getSelectedSound() {
        return getSoundEffect("selected");
    }

    public Sound getPlacementSound() {
        return getSoundEffect("placement");
    }

    public GridPoint2 getSize() {
        return dimensions;
    }

    public UnitType getType() {
        return type;
    }
}
