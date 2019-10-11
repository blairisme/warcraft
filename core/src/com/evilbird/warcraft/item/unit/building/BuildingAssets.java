/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.building;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.GridPoint2;
import com.evilbird.engine.common.assets.AssetBundle;
import com.evilbird.engine.common.assets.SyntheticTexture;
import com.evilbird.engine.common.audio.sound.Sound;
import com.evilbird.warcraft.item.unit.UnitType;
import com.evilbird.warcraft.state.WarcraftContext;

import java.util.Map;

import static com.evilbird.engine.common.assets.SyntheticTextureParameters.withColour;
import static com.evilbird.engine.common.collection.Maps.of;
import static com.evilbird.engine.common.graphics.Colours.FOREST_GREEN;
import static com.evilbird.engine.common.text.CaseUtils.toSnakeCase;
import static com.evilbird.warcraft.item.unit.UnitDimensions.getDimensionName;
import static com.evilbird.warcraft.item.unit.UnitDimensions.getDimensions;
import static com.evilbird.warcraft.item.unit.UnitType.BombardTower;
import static com.evilbird.warcraft.item.unit.UnitType.CannonTower;
import static com.evilbird.warcraft.item.unit.UnitType.GuardTower;
import static com.evilbird.warcraft.item.unit.UnitType.LookoutTower;

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

    public BuildingAssets(AssetManager manager, UnitType type, WarcraftContext context) {
        super(manager, assetPathVariables(type, context));
        this.type = type;
        this.dimensions = getDimensions(type);

        register("base", "data/textures/${faction}/building/${season}/${name}.png");
        register("construction", "data/textures/common/building/perennial/construction_${size}.png");
        registerOptional("construction", "data/textures/common/building/perennial/${name}_construction_site.png");
        register("destruction", "data/textures/common/building/winter/destroyed_site.png");
        register("selection", "selection_${size}", SyntheticTexture.class, withColour(FOREST_GREEN, dimensions));

        register("selected", "data/sounds/common/building/selected/1.mp3");
        register("placement", "data/sounds/common/building/placement/1.mp3");
        register("destroyed-1", "data/sounds/common/building/destroyed/1.mp3");
        register("destroyed-2", "data/sounds/common/building/destroyed/2.mp3");
        register("destroyed-3", "data/sounds/common/building/destroyed/3.mp3");
        registerOptional("attack", "data/sounds/common/unit/attack/${weapon}/1.mp3");
    }

    private static Map<String, String> assetPathVariables(UnitType type, WarcraftContext context) {
        return of("faction", toSnakeCase(type.getFaction().name()),
                "season", toSnakeCase(context.getAssetSet().name()),
                "name", toSnakeCase(type.name()),
                "size", getDimensionName(type),
                "weapon", getWeaponName(type));
    }

    private static String getWeaponName(UnitType type) {
        if (type.isOffensiveTower()) {
            if (type == GuardTower || type == LookoutTower) {
                return "Bow";
            }
            if (type == CannonTower || type == BombardTower) {
                return "siege";
            }
        }
        return "none";
    }

    public Texture getBaseTexture() {
        return getTexture("base");
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

    public Sound getDestroyedSound() {
        return getSoundEffectSet("destroyed-1", "destroyed-2", "destroyed-3");
    }

    public Sound getSelectedSound() {
        return getSoundEffect("selected");
    }

    public Sound getPlacementSound() {
        return getSoundEffect("placement");
    }

    public Sound getAttackSound() {
        return getOptionalSoundEffect("attack");
    }

    public GridPoint2 getSize() {
        return dimensions;
    }

    public UnitType getType() {
        return type;
    }
}
