/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.produce;

import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.warcraft.item.common.upgrade.Upgrade;
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

    ExorcismUpgrade,
    HealingUpgrade,

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

    ExorcismUpgradeCancel,
    HealingUpgradeCancel;

    public boolean isCancel() {
        return isBetween(this, MeleeDamage1UpgradeCancel, HealingUpgradeCancel);
    }

    public boolean isTypeUpgrade() {
        return this == MeleeType1Upgrade || this == RangedType1Upgrade;
    }

    public boolean isSpellUpgrade() {
        return isBetween(this, ExorcismUpgrade, HealingUpgrade)
            || isBetween(this, ExorcismUpgradeCancel, HealingUpgradeCancel);
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
