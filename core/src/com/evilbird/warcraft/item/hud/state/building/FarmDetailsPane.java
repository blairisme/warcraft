package com.evilbird.warcraft.item.hud.state.building;

import com.evilbird.engine.item.control.GridPane;
import com.evilbird.engine.item.control.TextLabel;
import com.evilbird.warcraft.item.unit.building.Building;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class FarmDetailsPane extends GridPane
{
    private Building building;
    private TextLabel grown;
    private TextLabel used;

    public FarmDetailsPane()
    {
        super(1, 3);

        /*
        TextLabel title = createLabel("Food Usage:", 160, 12);
        TextLabel grown = createLabel("    Grown: XXX", 160, 12);
        TextLabel used = createLabel("    Used: XXX", 160, 12);

        setSize(160, 100);
        setCellSpacing(4);
        setCell(title, 0, 0);
        setCell(grown, 0, 1);
        setCell(used, 0, 2);
        setCellWidthMinimum(160);
        setCellHeightMinimum(12);
        */
    }

    public void setBuilding(Building building)
    {

    }

    @Override
    public void update(float delta)
    {

        super.update(delta);
    }
}
