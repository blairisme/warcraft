package com.evilbird.warcraft.item.hud.state.building;

import com.evilbird.engine.item.control.GridPane;
import com.evilbird.engine.item.control.TextLabel;
import com.evilbird.warcraft.item.unit.building.Building;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class TownHallDetailsPane extends GridPane
{
    private Building building;
    private TextLabel gold;
    private TextLabel lumber;
    private TextLabel oil;

    public TownHallDetailsPane(Building building)
    {
        super(1, 4);

        /*
        Player player = (Player)building.getParent();

        TextLabel production = createLabel("Production");
        TextLabel gold = createDetailsLabel("Gold", player.getResource(ResourceType.Gold));
        TextLabel lumber = createDetailsLabel("Lumber", player.getResource(ResourceType.Wood));
        TextLabel oil = createDetailsLabel("Oil", player.getResource(ResourceType.Oil));

        setSize(160, 100);
        setCellSpacing(4);
        setCell(production, 0, 0);
        setCell(gold, 0, 1);
        setCell(lumber, 0, 2);
        setCell(oil, 0, 3);
        setCellWidthMinimum(160);
        setCellHeightMinimum(12);
        */
    }

    @Override
    public void update(float delta)
    {

        super.update(delta);
    }
}
