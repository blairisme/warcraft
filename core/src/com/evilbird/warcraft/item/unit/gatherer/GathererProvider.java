/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.gatherer;

import com.evilbird.engine.common.inject.IdentifiedAssetProviderSet;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.unit.UnitType;
import com.evilbird.warcraft.item.unit.gatherer.human.PeasantProvider;

import javax.inject.Inject;

public class GathererProvider extends IdentifiedAssetProviderSet<Item>
{
    @Inject
    public GathererProvider(PeasantProvider peasantProvider) {
        super();
        addProvider(UnitType.Peasant, peasantProvider);
    }
}
