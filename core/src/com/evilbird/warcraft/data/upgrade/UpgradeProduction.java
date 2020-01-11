/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.data.upgrade;

import com.evilbird.engine.common.time.Duration;
import com.evilbird.warcraft.data.resource.ResourceSet;

import static com.evilbird.engine.common.time.DurationUtils.Seconds;
import static com.evilbird.warcraft.data.resource.ResourceOperations.Resources;

/**
 * Defines the resources and times required to produce an upgrade.
 *
 * @author Blair Butterworth
 */
public enum UpgradeProduction
{
    MeleeDamage1        (Resources(800, 0, 0, 0),      Seconds(0)),
    MeleeDamage2        (Resources(2400, 0, 0, 0),     Seconds(0)),
    MeleeDefence1       (Resources(300, 300, 0, 0),    Seconds(0)),
    MeleeDefence2       (Resources(900, 500, 0, 0),    Seconds(0)),
    MeleeType1          (Resources(1000, 0, 0, 0),     Seconds(0)),
    RangedDamage1       (Resources(300, 300, 0, 0),    Seconds(0)),
    RangedDamage2       (Resources(600, 0, 0, 0),      Seconds(0)),
    RangedAccuracy1     (Resources(2500, 0, 0, 0),     Seconds(0)),
    RangedSight1        (Resources(1500, 0, 0, 0),     Seconds(0)),
    RangedType1         (Resources(1500, 0, 0, 0),     Seconds(0)),
    RangedWeapon1       (Resources(2000, 0, 0, 0),     Seconds(0)),
    NavalDamage1        (Resources(700, 100, 1000, 0), Seconds(0)),
    NavalDamage2        (Resources(2000, 250, 3000, 0),Seconds(0)),
    NavalDefence1       (Resources(500, 500, 0, 0),    Seconds(0)),
    NavalDefence2       (Resources(1500, 900, 0, 0),   Seconds(0)),
    SiegeDamage1        (Resources(1500, 0, 0, 0),     Seconds(0)),
    SiegeDamage2        (Resources(4000, 0, 0, 0),     Seconds(0)),
    GoldProduction1     (Resources(0, 0, 0, 0),        Seconds(0)),
    GoldProduction2     (Resources(0, 0, 0, 0),        Seconds(0)),
    OilProduction1      (Resources(0, 0, 0, 0),        Seconds(0)),
    OilProduction2      (Resources(0, 0, 0, 0),        Seconds(0)),
    WoodProduction1     (Resources(0, 0, 0, 0),        Seconds(0)),
    WoodProduction2     (Resources(0, 0, 0, 0),        Seconds(0)),

    BlizzardUpgrade     (Resources(2000, 0, 0, 0),      Seconds(0)),
    BloodlustUpgrade    (Resources(1000, 0, 0, 0),      Seconds(0)),
    DeathAndDecayUpgrade(Resources(2000, 0, 0, 0),      Seconds(0)),
    ExorcismUpgrade     (Resources(2000, 0, 0, 0),      Seconds(0)),
    FlameShieldUpgrade  (Resources(1000, 0, 0, 0),      Seconds(0)),
    HasteUpgrade        (Resources(500, 0, 0, 0),       Seconds(0)),
    HealingUpgrade      (Resources(1000, 0, 0, 0),      Seconds(0)),
    InvisibilityUpgrade (Resources(2500, 0, 0, 0),      Seconds(0)),
    PolymorphUpgrade    (Resources(2000, 0, 0, 0),      Seconds(0)),
    RaiseTheDeadUpgrade (Resources(1500, 0, 0, 0),      Seconds(0)),
    RunesUpgrade        (Resources(1000, 0, 0, 0),      Seconds(0)),
    SlowUpgrade         (Resources(500, 0, 0, 0),       Seconds(0)),
    UnholyArmourUpgrade (Resources(2500, 0, 0, 0),      Seconds(0)),
    WhirlwindUpgrade    (Resources(1500, 0, 0, 0),      Seconds(0)),
    None                (Resources(0, 0, 0, 0),         Seconds(0));

    private ResourceSet cost;
    private Duration duration;

    UpgradeProduction(ResourceSet cost, Duration duration) {
        this.cost = cost;
        this.duration = duration;
    }

    /**
     * Returns the resource cost of producing the update.
     */
    public ResourceSet getCost() {
        return cost;
    }

    /**
     * Returns the time required to produce an update.
     */
    public Duration getDuration() {
        return duration;
    }

    /**
     * Returns an upgrade production instance defining the resources and times
     * required to produce the given upgrade.
     */
    public static UpgradeProduction forProduct(Upgrade upgrade) {
        if (org.apache.commons.lang3.EnumUtils.isValidEnum(UpgradeProduction.class, upgrade.name())) {
            return UpgradeProduction.valueOf(upgrade.name());
        }
        return None;
    }
}
