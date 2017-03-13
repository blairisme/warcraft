package com.evilbird.warcraft.item.unit.combatant;

import com.evilbird.engine.common.inject.IdentifiedAssetProviderSet;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.unit.UnitType;
import com.evilbird.warcraft.item.unit.combatant.human.FootmanProvider;
import com.evilbird.warcraft.item.unit.combatant.human.PeasantProvider;
import com.evilbird.warcraft.item.unit.combatant.orc.GruntProvider;

import javax.inject.Inject;

public class CombatantProvider extends IdentifiedAssetProviderSet<Item>
{
    @Inject
    public CombatantProvider(
        FootmanProvider footmanProvider,
        PeasantProvider peasantProvider,
        GruntProvider gruntProvider)
    {
        super();
        addProvider(UnitType.Footman, footmanProvider);
        addProvider(UnitType.Peasant, peasantProvider);
        addProvider(UnitType.Grunt, gruntProvider);
    }
}
