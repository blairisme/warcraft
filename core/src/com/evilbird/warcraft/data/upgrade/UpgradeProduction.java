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
import org.apache.commons.lang3.EnumUtils;

import static com.evilbird.engine.common.time.Duration.ZERO;
import static com.evilbird.engine.common.time.DurationUtils.Seconds;
import static com.evilbird.warcraft.data.resource.ResourceOperations.Resources;

/**
 * Defines the resources and times required to produce an upgrade.
 *
 * @author Blair Butterworth
 */
public enum UpgradeProduction
{
    MeleeDamage1        (Resources(800, 0, 0, 0),      Seconds(50)),
    MeleeDamage2        (Resources(2400, 0, 0, 0),     Seconds(60)),
    MeleeDefence1       (Resources(300, 300, 0, 0),    Seconds(50)),
    MeleeDefence2       (Resources(900, 500, 0, 0),    Seconds(60)),
    MeleeType1          (Resources(1000, 0, 0, 0),     Seconds(70)),
    RangedDamage1       (Resources(300, 300, 0, 0),    Seconds(50)),
    RangedDamage2       (Resources(600, 0, 0, 0),      Seconds(60)),
    RangedAccuracy1     (Resources(2500, 0, 0, 0),     Seconds(60)),
    RangedSight1        (Resources(1500, 0, 0, 0),     Seconds(60)),
    RangedType1         (Resources(1500, 0, 0, 0),     Seconds(60)),
    RangedWeapon1       (Resources(2000, 0, 0, 0),     Seconds(60)),
    NavalDamage1        (Resources(700, 100, 1000, 0), Seconds(50)),
    NavalDamage2        (Resources(2000, 250, 3000, 0),Seconds(60)),
    NavalDefence1       (Resources(500, 500, 0, 0),    Seconds(60)),
    NavalDefence2       (Resources(1500, 900, 0, 0),   Seconds(80)),
    SiegeDamage1        (Resources(1500, 0, 0, 0),     Seconds(60)),
    SiegeDamage2        (Resources(4000, 0, 0, 0),     Seconds(60)),
    GoldProduction1     (Resources(0, 0, 0, 0),        ZERO),
    GoldProduction2     (Resources(0, 0, 0, 0),        ZERO),
    OilProduction1      (Resources(0, 0, 0, 0),        ZERO),
    OilProduction2      (Resources(0, 0, 0, 0),        ZERO),
    WoodProduction1     (Resources(0, 0, 0, 0),        ZERO),
    WoodProduction2     (Resources(0, 0, 0, 0),        ZERO),

    BlizzardUpgrade     (Resources(2000, 0, 0, 0),      Seconds(60)),
    BloodlustUpgrade    (Resources(1000, 0, 0, 0),      Seconds(30)),
    DeathAndDecayUpgrade(Resources(2000, 0, 0, 0),      Seconds(90)),
    ExorcismUpgrade     (Resources(2000, 0, 0, 0),      Seconds(50)),
    FlameShieldUpgrade  (Resources(1000, 0, 0, 0),      Seconds(30)),
    HasteUpgrade        (Resources(500, 0, 0, 0),       Seconds(40)),
    HealingUpgrade      (Resources(1000, 0, 0, 0),      Seconds(50)),
    InvisibilityUpgrade (Resources(2500, 0, 0, 0),      Seconds(50)),
    PolymorphUpgrade    (Resources(2000, 0, 0, 0),      Seconds(60)),
    RaiseTheDeadUpgrade (Resources(1500, 0, 0, 0),      Seconds(40)),
    RunesUpgrade        (Resources(1000, 0, 0, 0),      Seconds(50)),
    SlowUpgrade         (Resources(500, 0, 0, 0),       Seconds(30)),
    UnholyArmourUpgrade (Resources(2500, 0, 0, 0),      Seconds(60)),
    WhirlwindUpgrade    (Resources(1500, 0, 0, 0),      Seconds(60)),
    None                (Resources(0, 0, 0, 0),         ZERO);

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
        if (EnumUtils.isValidEnum(UpgradeProduction.class, upgrade.name())) {
            return UpgradeProduction.valueOf(upgrade.name());
        }
        return None;
    }
}
