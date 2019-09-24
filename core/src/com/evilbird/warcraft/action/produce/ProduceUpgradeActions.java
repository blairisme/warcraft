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
    RangedDamage1Upgrade,
    RangedDamage2Upgrade,

    RangedDamage1UpgradeCancel,
    RangedDamage2UpgradeCancel;

    public boolean isCancel() {
        return isBetween(this, RangedDamage1UpgradeCancel, RangedDamage2UpgradeCancel);
    }

    public Upgrade getProduct() {
        return Upgrade.valueOf(getProductName());
    }

    public static ProduceUnitActions forProduct(Upgrade type) {
        return ProduceUnitActions.valueOf(type.name() + "Upgrade");
    }

    public static ProduceUnitActions forProductCancel(Upgrade type) {
        return ProduceUnitActions.valueOf(type.name() + "UpgradeCancel");
    }

    private String getProductName() {
        String name = this.name();
        name = StringUtils.removeEnd(name, "Upgrade");
        name = StringUtils.removeEnd(name, "UpgradeCancel");
        return name;
    }
}
