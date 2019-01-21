/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.state.scenario;

import com.evilbird.engine.common.inject.IdentifiedAssetProviderSet;
import com.evilbird.engine.item.ItemRoot;

import javax.inject.Inject;

public class ScenarioProvider extends IdentifiedAssetProviderSet<ItemRoot>
{
    @Inject
    public ScenarioProvider()
    {
    }
}
