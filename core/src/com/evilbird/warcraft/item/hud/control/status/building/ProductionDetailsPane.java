/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.hud.control.status.building;

import com.evilbird.engine.common.control.GridPane;
import com.evilbird.engine.common.control.TextLabel;
import com.evilbird.engine.common.control.TextLabelAlignment;
import com.evilbird.warcraft.item.unit.building.Building;

/**
 * Instances of this user interface are shown when a building is producing
 * something, usually a unit or upgrade.
 *
 * @author Blair Butterworth
 */
//TODO: Show tile with icon and name of thing being produced
public class ProductionDetailsPane extends GridPane
{
    private Building building;
    private BuildingProgress progressBar;

    public ProductionDetailsPane(BuildingProgressProvider buildingProgressProvider) {
        super(1, 1);
        progressBar = buildingProgressProvider.get();

        TextLabel training = createLabel("Training");

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

    private TextLabel createLabel(String text) {
        TextLabel result = new TextLabel();
        result.setText(text);
        result.setSize(160, 12);
        result.setAlignment(TextLabelAlignment.Center);
        return result;
    }
}
