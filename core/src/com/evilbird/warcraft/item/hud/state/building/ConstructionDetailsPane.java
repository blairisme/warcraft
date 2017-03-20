package com.evilbird.warcraft.item.hud.state.building;

import com.evilbird.engine.item.control.GridPane;
import com.evilbird.warcraft.item.unit.building.Building;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class ConstructionDetailsPane extends GridPane
{
    private Building building;
    private BuildingBar progressBar;

    public ConstructionDetailsPane(BuildingBarProvider buildingBarProvider)
    {
        super(1, 1);
        this.progressBar = buildingBarProvider.get();

        setSize(160, 100);
        setHorizontalCellPadding(4);
        setVerticalCellPadding(80);
        setCell(progressBar, 0, 0);
    }

    public void setBuilding(Building building)
    {
        this.building = building;
    }

    @Override
    public void update(float delta)
    {
        progressBar.setProgress(building.getProgress());
        super.update(delta);
    }
}
