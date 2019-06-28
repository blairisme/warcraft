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
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Defines options of specifying production action varieties.
 *
 * @author Blair Butterworth
 */
public enum ProduceUpgradeActions implements ActionIdentifier
{
    UpgradeArrowDamage,
    UpgradeArrowDamageCancel;

    public boolean isCancel() {
        return this.ordinal() >= UpgradeArrowDamageCancel.ordinal();
    }

    public PlayerUpgrade getProduct() {
        return getProductValue(PlayerUpgrade.class, getProductName());
    }

    private String getProductName() {
        String name = this.name();
        name = StringUtils.removeStart(name, "Upgrade");
        name = StringUtils.removeEnd(name, "Cancel");
        return name;
    }

    private <T extends Enum<T>> T getProductValue(Class<T> type, String name) {
        if (EnumUtils.isValidEnum(type, name)) {
            return Enum.valueOf(type, name);
        }
        throw new UnsupportedOperationException();
    }
}
