package com.evilbird.warcraft.action.type;

import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.warcraft.item.unit.UnitType;

public enum BuildAction implements ActionIdentifier
{
    BuildBarracks   (UnitType.Barracks),
    BuildFarm       (UnitType.Farm),
    BuildTownHall   (UnitType.TownHall);

    private UnitType unitType;

    private BuildAction(UnitType unitType) {
        this.unitType = unitType;
    }

    public UnitType getUnitType() {
        return unitType;
    }
}
