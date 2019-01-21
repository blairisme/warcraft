package com.evilbird.warcraft.item.hud.control.state.building;

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
    private BuildingProgress progressBar;

    public ConstructionDetailsPane(BuildingProgressProvider buildingProgressProvider)
    {
        super(1, 1);
        progressBar = buildingProgressProvider.get();

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
