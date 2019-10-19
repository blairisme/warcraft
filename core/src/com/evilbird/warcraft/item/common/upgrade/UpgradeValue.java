/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.common.upgrade;

import com.google.gson.annotations.JsonAdapter;

import java.util.Set;

/**
 * Implementors of this interface represent a game object property that can be
 * modified by the presence of player {@link Upgrade Upgrades}.
 *
 * @param <T> the values type.
 *
 * @author Blair Butterworth
 */
@JsonAdapter(UpgradeValueSerializer.class)
public interface UpgradeValue<T>
{
    Set<Upgrade> getUpgrades();

    T getValue(Set<Upgrade> researched);

    T getBaseValue();
}
