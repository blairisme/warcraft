/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.gatherer.orc;

import com.evilbird.engine.common.inject.AssetProvider;
import com.evilbird.engine.item.Item;

public class PeonFactory implements AssetProvider<Item>
{
    @Override
    public void load() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Item get() {
        throw new UnsupportedOperationException();

        //armour: 0
        //damage: 1-5
        //range: 1
        //sight: 4
        //speed: 10
        //health: 30
    }
}
