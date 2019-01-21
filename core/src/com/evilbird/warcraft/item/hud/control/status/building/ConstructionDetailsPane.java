/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.hud.control.status.building;

import com.evilbird.engine.item.control.GridPane;
import com.evilbird.warcraft.item.unit.building.Building;

/**
 * Instances of this user interface are shown when a building is under
 * construction.
 *
 * @author Blair Butterworth
 */
public class ConstructionDetailsPane extends GridPane
{
    private Building building;
    private BuildingProgress progressBar;

    public ConstructionDetailsPane(BuildingProgressProvider buildingProgressProvider) {
        super(1, 1);
        progressBar = buildingProgressProvider.get();

        setSize(160, 100);
        setHorizontalCellPadding(4);
        setVerticalCellPadding(80);
        setCell(progressBar, 0, 0);
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    @Override
    public void update(float delta) {
        progressBar.setProgress(building.getProgress());
        super.update(delta);
    }
}
