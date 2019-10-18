/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.common.upgrade;

import java.util.Collection;

/**
 * Implementors of this interface represent a repository of upgrades.
 *
 * @author Blair Butterworth
 */
public interface UpgradeContainer
{
    /**
     * Removes any {@link Upgrade Upgrades} contained in the upgrade container.
     */
    void clearUpgrades();

    /**
     * Returns a {@link Collection} of those {@link Upgrade Upgrades} contained
     * in the upgrade container.
     */
    Collection<Upgrade> getUpgrades();

    /**
     * Stores the given {@link Collection} of {@link Upgrade Upgrades} in the
     * upgrade container.
     */
    void setUpgrades(Collection<Upgrade> upgrades);
}
