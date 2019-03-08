/*
 * Blair Butterworth (c) 2018
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.resource.oilpatch;

import com.evilbird.engine.common.inject.AssetProvider;
import com.evilbird.warcraft.item.unit.resource.Resource;

import javax.inject.Inject;

public class OilPatchProvider implements AssetProvider<Resource>
{
    @Inject
    public OilPatchProvider() {
    }

    @Override
    public void load() {
    }

    @Override
    public Resource get() {
        return null;
    }
}
