/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.state.hud.orc;

import com.evilbird.engine.common.inject.AssetProvider;
import com.evilbird.engine.item.ItemRoot;

import javax.inject.Inject;

public class OrcHudProvider implements AssetProvider<ItemRoot>
{
    @Inject
    public OrcHudProvider()
    {
    }

    @Override
    public void load()
    {

    }

    @Override
    public ItemRoot get()
    {
        return null;
    }
}
