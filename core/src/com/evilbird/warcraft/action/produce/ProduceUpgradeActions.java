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
import com.evilbird.warcraft.item.data.player.PlayerUpgrade;
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
    RangedDamage1UpgradeCancel;

    public boolean isCancel() {
        return isBetween(this, RangedDamage1UpgradeCancel, RangedDamage1UpgradeCancel);
    }

    public PlayerUpgrade getProduct() {
        return PlayerUpgrade.valueOf(getProductName());
    }

    public static ProduceUnitActions forProduct(PlayerUpgrade type) {
        return ProduceUnitActions.valueOf(type.name() + "Upgrade");
    }

    public static ProduceUnitActions forProductCancel(PlayerUpgrade type) {
        return ProduceUnitActions.valueOf(type.name() + "UpgradeCancel");
    }

    private String getProductName() {
        String name = this.name();
        name = StringUtils.removeEnd(name, "Upgrade");
        name = StringUtils.removeEnd(name, "UpgradeCancel");
        return name;
    }
}
