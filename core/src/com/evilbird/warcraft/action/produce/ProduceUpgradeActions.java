/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.produce;

import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.warcraft.data.upgrade.Upgrade;
import org.apache.commons.lang3.StringUtils;

import static com.evilbird.engine.common.collection.EnumUtils.isBetween;

/**
 * Defines options of specifying production action varieties.
 *
 * @author Blair Butterworth
 */
public enum ProduceUpgradeActions implements ActionIdentifier
{
    MeleeDamage1Upgrade,
    MeleeDamage2Upgrade,
    MeleeDefence1Upgrade,
    MeleeDefence2Upgrade,
    MeleeType1Upgrade,
    RangedDamage1Upgrade,
    RangedDamage2Upgrade,
    RangedAccuracy1Upgrade,
    RangedSight1Upgrade,
    RangedType1Upgrade,
    RangedWeapon1Upgrade,
    NavalDamage1Upgrade,
    NavalDamage2Upgrade,
    NavalDefence1Upgrade,
    NavalDefence2Upgrade,
    SiegeDamage1Upgrade,
    SiegeDamage2Upgrade,
    GoldProduction1Upgrade,
    GoldProduction2Upgrade,
    OilProduction1Upgrade,
    OilProduction2Upgrade,
    WoodProduction1Upgrade,
    WoodProduction2Upgrade,

    BlizzardUpgrade,
    BloodlustUpgrade,
    DeathAndDecayUpgrade,
    ExorcismUpgrade,
    FlameShieldUpgrade,
    HasteUpgrade,
    HealingUpgrade,
    InvisibilityUpgrade,
    PolymorphUpgrade,
    RaiseTheDeadUpgrade,
    RunesUpgrade,
    SlowUpgrade,
    UnholyArmourUpgrade,
    WhirlwindUpgrade,

    MeleeDamage1UpgradeCancel,
    MeleeDamage2UpgradeCancel,
    MeleeDefence1UpgradeCancel,
    MeleeDefence2UpgradeCancel,
    MeleeType1UpgradeCancel,
    RangedDamage1UpgradeCancel,
    RangedDamage2UpgradeCancel,
    RangedAccuracy1UpgradeCancel,
    RangedSight1UpgradeCancel,
    RangedType1UpgradeCancel,
    RangedWeapon1UpgradeCancel,
    NavalDamage1UpgradeCancel,
    NavalDamage2UpgradeCancel,
    NavalDefence1UpgradeCancel,
    NavalDefence2UpgradeCancel,
    SiegeDamage1UpgradeCancel,
    SiegeDamage2UpgradeCancel,
    GoldProduction1UpgradeCancel,
    GoldProduction2UpgradeCancel,
    OilProduction1UpgradeCancel,
    OilProduction2UpgradeCancel,
    WoodProduction1UpgradeCancel,
    WoodProduction2UpgradeCancel,

    BlizzardUpgradeCancel,
    BloodlustUpgradeCancel,
    DeathAndDecayUpgradeCancel,
    ExorcismUpgradeCancel,
    FlameShieldUpgradeCancel,
    HasteUpgradeCancel,
    HealingUpgradeCancel,
    InvisibilityUpgradeCancel,
    PolymorphUpgradeCancel,
    RaiseTheDeadUpgradeCancel,
    RunesUpgradeCancel,
    SlowUpgradeCancel,
    UnholyArmourUpgradeCancel,
    WhirlwindUpgradeCancel;

    public boolean isCancel() {
        return isBetween(this, MeleeDamage1UpgradeCancel, HealingUpgradeCancel);
    }

    public boolean isTypeUpgrade() {
        return this == MeleeType1Upgrade || this == RangedType1Upgrade;
    }

    public boolean isSpellUpgrade() {
        return isBetween(this, BlizzardUpgrade, WhirlwindUpgrade)
            || isBetween(this, BlizzardUpgradeCancel, WhirlwindUpgradeCancel);
    }

    public Upgrade getProduct() {
        return Upgrade.valueOf(getProductName());
    }

    public String getProductName() {
        return isSpellUpgrade() ? getSpellUpgradeName() : getPropertyUpgradeName();
    }

    private String getSpellUpgradeName() {
        return StringUtils.removeEnd(name(), "Cancel");
    }

    private String getPropertyUpgradeName() {
        String name = this.name();
        name = StringUtils.removeEnd(name, "Upgrade");
        name = StringUtils.removeEnd(name, "UpgradeCancel");
        return name;
    }

    public static ProduceUpgradeActions forProduct(Upgrade upgrade) {
        if (upgrade != Upgrade.None) {
            return ProduceUpgradeActions.valueOf(getUpgradeName(upgrade));
        }
        throw new IllegalArgumentException("Upgrade.None");
    }

    public static ProduceUpgradeActions forProductCancel(Upgrade upgrade) {
        if (upgrade != Upgrade.None) {
            return ProduceUpgradeActions.valueOf(getUpgradeName(upgrade) + "Cancel");
        }
        throw new IllegalArgumentException("Upgrade.None");
    }

    private static String getUpgradeName(Upgrade upgrade) {
        String name = upgrade.name();
        if (!name.endsWith("Upgrade")) {
            name += "Upgrade";
        }
        return name;
    }
}
