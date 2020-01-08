/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.data.upgrade;

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
