/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.data.player;

import com.evilbird.engine.common.inject.AssetProvider;
import com.evilbird.engine.item.Item;

import javax.inject.Inject;

public class PlayerFactory implements AssetProvider<Item>
{
    @Inject
    public PlayerFactory() {
    }

    @Override
    public void load() {
    }

    @Override
    public Item get() {
        return new Player();
    }
}
