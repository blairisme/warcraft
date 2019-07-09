/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.building;

import com.badlogic.gdx.math.GridPoint2;
import com.evilbird.warcraft.item.unit.UnitType;

import static com.evilbird.warcraft.item.unit.UnitType.BombardTower;
import static com.evilbird.warcraft.item.unit.UnitType.CannonTower;
import static com.evilbird.warcraft.item.unit.UnitType.Castle;
import static com.evilbird.warcraft.item.unit.UnitType.CircleOfPower;
import static com.evilbird.warcraft.item.unit.UnitType.Farm;
import static com.evilbird.warcraft.item.unit.UnitType.Fortress;
import static com.evilbird.warcraft.item.unit.UnitType.GreatHall;
import static com.evilbird.warcraft.item.unit.UnitType.GuardTower;
import static com.evilbird.warcraft.item.unit.UnitType.Keep;
import static com.evilbird.warcraft.item.unit.UnitType.LookoutTower;
import static com.evilbird.warcraft.item.unit.UnitType.PigFarm;
import static com.evilbird.warcraft.item.unit.UnitType.Runestone;
import static com.evilbird.warcraft.item.unit.UnitType.ScoutTower;
import static com.evilbird.warcraft.item.unit.UnitType.Stronghold;
import static com.evilbird.warcraft.item.unit.UnitType.TownHall;
import static com.evilbird.warcraft.item.unit.UnitType.WatchTower;

/**
 * Defines the dimensions of {@link Building} assets.
 *
 * @author Blair Butterworth
 */
public class BuildingAssetDimensions
{
    private static final GridPoint2 SMALL = new GridPoint2(64, 64);
    private static final GridPoint2 MEDIUM = new GridPoint2(96, 96);
    private static final GridPoint2 LARGE = new GridPoint2(128, 128);

    private BuildingAssetDimensions() {
    }

    public static GridPoint2 getDimensions(UnitType type) {
        if (isSmall(type)) {
            return SMALL;
        }
        if (isLarge(type)) {
            return LARGE;
        }
        return MEDIUM;
    }

    private static boolean isSmall(UnitType type) {
        return type == CannonTower || type == BombardTower
            || type == GuardTower || type == LookoutTower
            || type == ScoutTower || type == WatchTower
            || type == Runestone  || type == CircleOfPower
            || type == Farm       || type == PigFarm;
    }

    private static boolean isLarge(UnitType type) {
        return type == TownHall || type == GreatHall
            || type == Keep    || type == Stronghold
            || type == Castle  || type == Fortress;
    }
}
