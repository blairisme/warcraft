/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit;

import com.badlogic.gdx.math.GridPoint2;
import com.evilbird.warcraft.item.unit.building.Building;

import static com.evilbird.warcraft.item.unit.UnitType.BombardTower;
import static com.evilbird.warcraft.item.unit.UnitType.CannonTower;
import static com.evilbird.warcraft.item.unit.UnitType.Castle;
import static com.evilbird.warcraft.item.unit.UnitType.CircleOfPower;
import static com.evilbird.warcraft.item.unit.UnitType.DarkPortal;
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
public class UnitDimensions
{
    private static final GridPoint2 EXTRA_SMALL = new GridPoint2(32, 32);
    private static final GridPoint2 SMALL = new GridPoint2(64, 64);
    private static final GridPoint2 MEDIUM = new GridPoint2(88, 88);
    private static final GridPoint2 LARGE = new GridPoint2(96, 96);
    private static final GridPoint2 EXTRA_LARGE = new GridPoint2(128, 128);

    private UnitDimensions() {
    }

    public static GridPoint2 getDimensions(UnitType type) {
        if (type.isBuilding()) {
            return getBuildingDimensions(type);
        }
        if (type.isCombatant()) {
            return getCombatantDimensions(type);
        }
        if (type.isResource()) {
            return getResourceDimensions();
        }
        return MEDIUM;
    }

    private static GridPoint2 getBuildingDimensions(UnitType type) {
        if (isSmallBuilding(type)) {
            return SMALL;
        }
        if (isLargeBuilding(type)) {
            return EXTRA_LARGE;
        }
        return LARGE;
    }

    private static GridPoint2 getCombatantDimensions(UnitType type) {
        if (type.isSiege()) {
            return SMALL;
        }
        if (type.isShip()) {
            return MEDIUM;
        }
        return EXTRA_SMALL;
    }

    private static GridPoint2 getResourceDimensions() {
        return LARGE;
    }

    public static String getDimensionName(UnitType type) {
        GridPoint2 dimensions = getDimensions(type);

        if (dimensions == EXTRA_SMALL) {
            return "extra_small";
        }
        if (dimensions == SMALL) {
            return "small";
        }
        if (dimensions == MEDIUM) {
            return "medium";
        }
        if (dimensions == LARGE) {
            return "large";
        }
        return "extra_large";
    }

    private static boolean isSmallBuilding(UnitType type) {
        return type == CannonTower || type == BombardTower
            || type == GuardTower || type == LookoutTower
            || type == ScoutTower || type == WatchTower
            || type == Runestone  || type == CircleOfPower
            || type == Farm       || type == PigFarm;
    }

    private static boolean isLargeBuilding(UnitType type) {
        return type == TownHall || type == GreatHall
            || type == Keep    || type == Stronghold
            || type == Castle  || type == Fortress
            || type == DarkPortal;
    }
}
