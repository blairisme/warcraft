package com.evilbird.warcraft.item.hud.state.building;

import com.evilbird.engine.item.control.GridPane;
import com.evilbird.engine.item.control.TextLabel;
import com.evilbird.engine.item.control.TextLabelAlignment;
import com.evilbird.warcraft.item.data.player.Player;
import com.evilbird.warcraft.item.unit.resource.ResourceType;
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

    public TownHallDetailsPane()
    {
        super(1, 4);

        gold = createLabel("Gold");
        lumber = createLabel("Lumber");
        oil = createLabel("Oil");

        setSize(160, 100);
        setCellSpacing(4);
        setCell(createLabel("Production"), 0, 0);
        setCell(gold, 0, 1);
        setCell(lumber, 0, 2);
        setCell(oil, 0, 3);
        setCellWidthMinimum(160);
        setCellHeightMinimum(12);
    }

    public void setBuilding(Building building)
    {
        this.building = building;
    }

    @Override
    public void update(float delta)
    {
        Player player = (Player)building.getParent();
        gold.setText(getText("Gold", player.getResource(ResourceType.Gold)));
        lumber.setText(getText("Lumber", player.getResource(ResourceType.Wood)));
        oil.setText(getText("Oil", player.getResource(ResourceType.Oil)));
        super.update(delta);
    }

    private String getText(String prefix, float suffix)
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(prefix);
        stringBuilder.append(": ");
        stringBuilder.append(Math.round(suffix));
        return stringBuilder.toString();
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