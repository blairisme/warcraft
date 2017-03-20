package com.evilbird.warcraft.item.hud.state.building;

import com.evilbird.engine.item.control.GridPane;
import com.evilbird.warcraft.item.unit.building.Building;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class ProductionDetailsPane extends GridPane
{
    private Building building;

    public ProductionDetailsPane(Building building)
    {
        super(1, 1);

        /*
        TextLabel training = createLabel("Training", 160, 12);

        BuildingBar buildingBar = buildingBarProvider.get();
        buildingBar.setBuilding(building);

        setSize(160, 100);
        setHorizontalCellPadding(4);
        setVerticalCellPadding(80);
        setCell(buildingBar, 0, 0);
        */
    }

    @Override
    public void update(float delta)
    {

        super.update(delta);
    }
}
