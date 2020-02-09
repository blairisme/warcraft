/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.unit;

import com.evilbird.engine.common.lang.Identifier;

import static com.evilbird.engine.common.collection.EnumUtils.isBetween;

/**
 * Defines unit archetypes: unit base types for which faction or improved
 * specializations may exist.
 *
 * @author Blair Butterworth
 */
public enum UnitArchetype implements Identifier
{
    CommandCentre,
    CombatantProducer,
    NavalProducer,
    FlyingProducer,
    WizardProducer,
    FoodProducer,
    OilProducer,
    WoodDepot,
    OilDepot,
    Tower,
    CavalryRequisite,
    CombatantUpgrader,
    CavalryUpgrader,
    FlyingUpgrader,
    NavalUpgrader,

    Warrior,
    Cavalry,
    Archer,
    Demolition,
    Warship,
    HeavyWarship,
    Submarine,
    Transportation,
    SiegeEngine,
    Flying,
    Wizard,
    Worker,
    Tanker,

    ConjuredUnit,
    ConjuredEffect,

    Critter,
    Waypoint,

    GoldResource,
    OilResource,
    WoodResource;

    /**
     * Returns whether the archetype represents a building or not.
     */
    public boolean isBuilding() {
        return isBetween(this, CommandCentre, NavalUpgrader);
    }

    /**
     * Returns whether the archetype represents a movable unit or not.
     */
    public boolean isUnit() {
        return isBetween(this, Warrior, ConjuredUnit);
    }
}
