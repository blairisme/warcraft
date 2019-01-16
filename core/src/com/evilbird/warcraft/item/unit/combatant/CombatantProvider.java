/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.combatant;

import com.evilbird.engine.common.inject.IdentifiedAssetProviderSet;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.unit.UnitType;
import com.evilbird.warcraft.item.unit.combatant.human.FootmanProvider;
import com.evilbird.warcraft.item.unit.gatherer.human.PeasantProvider;
import com.evilbird.warcraft.item.unit.combatant.orc.GruntProvider;

import javax.inject.Inject;

public class CombatantProvider extends IdentifiedAssetProviderSet<Item>
{
    @Inject
    public CombatantProvider(
        FootmanProvider footmanProvider,
        GruntProvider gruntProvider)
    {
        super();
        addProvider(UnitType.Footman, footmanProvider);
        addProvider(UnitType.Grunt, gruntProvider);
    }
}
