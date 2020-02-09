/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.data.upgrade;

import com.evilbird.engine.common.time.Duration;
import com.evilbird.warcraft.data.product.Production;
import com.evilbird.warcraft.data.resource.ResourceSet;
import com.evilbird.warcraft.object.unit.UnitArchetype;

import static com.evilbird.engine.common.time.Duration.ZERO;
import static com.evilbird.engine.common.time.DurationUtils.Seconds;
import static com.evilbird.warcraft.data.resource.ResourceOperations.Resources;
import static com.evilbird.warcraft.object.unit.UnitArchetype.CavalryUpgrader;
import static com.evilbird.warcraft.object.unit.UnitArchetype.CombatantUpgrader;
import static com.evilbird.warcraft.object.unit.UnitArchetype.CommandCentre;
import static com.evilbird.warcraft.object.unit.UnitArchetype.NavalUpgrader;
import static com.evilbird.warcraft.object.unit.UnitArchetype.OilDepot;
import static com.evilbird.warcraft.object.unit.UnitArchetype.WizardProducer;
import static com.evilbird.warcraft.object.unit.UnitArchetype.WoodDepot;

/**
 * Defines the resources and times required to produce an upgrade.
 *
 * @author Blair Butterworth
 */
@SuppressWarnings("checkstyle:MethodParamPad")
public enum UpgradeProduction implements Production
{
    /*                  | Facility          | Cost                        | Time      */
    MeleeDamage1        (CombatantUpgrader, Resources(800, 0, 0, 0),      Seconds(50)),
    MeleeDamage2        (CombatantUpgrader, Resources(2400, 0, 0, 0),     Seconds(60)),
    MeleeDefence1       (CombatantUpgrader, Resources(300, 300, 0, 0),    Seconds(50)),
    MeleeDefence2       (CombatantUpgrader, Resources(900, 500, 0, 0),    Seconds(60)),
    MeleeType1          (CavalryUpgrader,   Resources(1000, 0, 0, 0),     Seconds(70)),
    RangedDamage1       (WoodDepot,         Resources(300, 300, 0, 0),    Seconds(50)),
    RangedDamage2       (WoodDepot,         Resources(600, 0, 0, 0),      Seconds(60)),
    RangedAccuracy1     (WoodDepot,         Resources(2500, 0, 0, 0),     Seconds(60)),
    RangedSight1        (WoodDepot,         Resources(1500, 0, 0, 0),     Seconds(60)),
    RangedType1         (WoodDepot,         Resources(1500, 0, 0, 0),     Seconds(60)),
    RangedWeapon1       (WoodDepot,         Resources(2000, 0, 0, 0),     Seconds(60)),
    NavalDamage1        (NavalUpgrader,     Resources(700, 100, 1000, 0), Seconds(50)),
    NavalDamage2        (NavalUpgrader,     Resources(2000, 250, 3000, 0),Seconds(60)),
    NavalDefence1       (NavalUpgrader,     Resources(500, 500, 0, 0),    Seconds(60)),
    NavalDefence2       (NavalUpgrader,     Resources(1500, 900, 0, 0),   Seconds(80)),
    SiegeDamage1        (CombatantUpgrader, Resources(1500, 0, 0, 0),     Seconds(60)),
    SiegeDamage2        (CombatantUpgrader, Resources(4000, 0, 0, 0),     Seconds(60)),
    GoldProduction1     (CommandCentre,     Resources(0, 0, 0, 0),        ZERO),
    GoldProduction2     (CommandCentre,     Resources(0, 0, 0, 0),        ZERO),
    OilProduction1      (OilDepot,          Resources(0, 0, 0, 0),        ZERO),
    OilProduction2      (OilDepot,          Resources(0, 0, 0, 0),        ZERO),
    WoodProduction1     (WoodDepot,         Resources(0, 0, 0, 0),        ZERO),
    WoodProduction2     (WoodDepot,         Resources(0, 0, 0, 0),        ZERO),

    /*                  | Facility          | Cost                      | Time      */
    BlizzardUpgrade     (WizardProducer,    Resources(2000, 0, 0, 0),      Seconds(60)),
    BloodlustUpgrade    (CavalryUpgrader,   Resources(1000, 0, 0, 0),      Seconds(30)),
    DeathAndDecayUpgrade(WizardProducer,    Resources(2000, 0, 0, 0),      Seconds(90)),
    ExorcismUpgrade     (CavalryUpgrader,   Resources(2000, 0, 0, 0),      Seconds(50)),
    FlameShieldUpgrade  (WizardProducer,    Resources(1000, 0, 0, 0),      Seconds(30)),
    HasteUpgrade        (WizardProducer,    Resources(500, 0, 0, 0),       Seconds(40)),
    HealingUpgrade      (CavalryUpgrader,   Resources(1000, 0, 0, 0),      Seconds(50)),
    InvisibilityUpgrade (WizardProducer,    Resources(2500, 0, 0, 0),      Seconds(50)),
    PolymorphUpgrade    (WizardProducer,    Resources(2000, 0, 0, 0),      Seconds(60)),
    RaiseTheDeadUpgrade (WizardProducer,    Resources(1500, 0, 0, 0),      Seconds(40)),
    RunesUpgrade        (CavalryUpgrader,   Resources(1000, 0, 0, 0),      Seconds(50)),
    SlowUpgrade         (WizardProducer,    Resources(500, 0, 0, 0),       Seconds(30)),
    UnholyArmourUpgrade (WizardProducer,    Resources(2500, 0, 0, 0),      Seconds(60)),
    WhirlwindUpgrade    (WizardProducer,    Resources(1500, 0, 0, 0),      Seconds(60)),

    None                (null,              Resources(0, 0, 0, 0),         ZERO);

    private UnitArchetype producer;
    private ResourceSet cost;
    private Duration duration;

    UpgradeProduction(UnitArchetype producer, ResourceSet cost, Duration duration) {
        this.cost = cost;
        this.duration = duration;
        this.producer = producer;
    }

    /**
     * Returns the resource cost of producing the update.
     */
    @Override
    public ResourceSet getCost() {
        return cost;
    }

    /**
     * Returns the time required to produce the upgrade.
     */
    @Override
    public Duration getDuration() {
        return duration;
    }

    /**
     * Returns the facility required to produce the upgrade.
     */
    @Override
    public UnitArchetype getProducer() {
        return producer;
    }
}
