package com.evilbird.warcraft.item.hud.control.state.building;

import com.evilbird.engine.item.control.GridPane;
import com.evilbird.engine.item.control.TextLabel;
import com.evilbird.engine.item.control.TextLabelAlignment;
import com.evilbird.warcraft.item.unit.building.Building;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class ProductionDetailsPane extends GridPane
{
    private Building building;
    private BuildingProgress progressBar;

    public ProductionDetailsPane(BuildingProgressProvider buildingProgressProvider)
    {
        super(1, 1);
        progressBar = buildingProgressProvider.get();

        TextLabel training = createLabel("Training");

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

    private TextLabel createLabel(String text)
    {
        TextLabel result = new TextLabel();
        result.setText(text);
        result.setSize(160, 12);
        result.setAlignment(TextLabelAlignment.Center);
        return result;
    }
}
