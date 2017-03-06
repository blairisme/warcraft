package com.evilbird.warcraft.item.hud;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.common.lang.Objects;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.control.GridPane;
import com.evilbird.engine.item.control.TextLabel;
import com.evilbird.warcraft.item.unit.Unit;

import javax.inject.Provider;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class DetailsPane extends GridPane
{
    private Provider<UnitPane> unitPaneProvider;

    public DetailsPane(Provider<UnitPane> unitPaneProvider)
    {
        super(1, 2);
        this.unitPaneProvider = unitPaneProvider;
        initialize();
    }

    private void initialize()
    {
        setSize(176, 176);
        setCellPadding(5);
        setCellSpacing(15);
        setCellWidthMinimum(170);
        setCellHeightMinimum(50);
        setId(new Identifier("DetailsPane"));
        setType(new Identifier("DetailsPane"));
        setTouchable(Touchable.disabled);
    }

    public void setItem(Item item)
    {
        clearCells();
        setTitle(item);
        setDetails(item);
    }

    private void setTitle(Item item)
    {
        UnitPane unitPane = unitPaneProvider.get();
        unitPane.setItem(item);
        unitPane.setSize(54, 53);

        TextLabel name = createLabel(getName(item), 100, 15);
        TextLabel level = createLabel(getLevel(item), 100, 15);

        GridPane nameContainer = new GridPane(1, 2);
        nameContainer.setSize(110, 50);
        nameContainer.setCellPadding(4);
        nameContainer.setCellSpacing(4);
        nameContainer.setCell(name, 0, 0);
        nameContainer.setCell(level, 0, 1);

        GridPane titleContainer = new GridPane(2, 1);
        titleContainer.setSize(160, 50);
        titleContainer.setCellSpacing(0);
        titleContainer.setCell(unitPane, 0, 0);
        titleContainer.setCell(nameContainer, 1, 0);

        setCell(titleContainer, 0, 0);
    }

    private String getName(Item item)
    {
        return item.getType().toString(); //TODO: Replace with item.getName()
    }

    private String getLevel(Item item)
    {
        if (item instanceof Unit)
        {
            Unit unit = (Unit)item;
            return "Level " + String.valueOf(unit.getLevel()); //TODO: Localize
        }
        return "";
    }

    private void setDetails(Item item)
    {
        if (Objects.equals(new Identifier("Farm"), item.getType())){
            setFarmDetails(item);
        }
        else if (Objects.equals(new Identifier("TownHall"), item.getType())){
            setTownHallDetails(item);
        }
        else if (Objects.equals(new Identifier("GoldMine"), item.getType())){
            setGoldMineDetails(item);
        }
        else if (Objects.equals(new Identifier("Footman"), item.getType()) ||
                Objects.equals(new Identifier("Peasant"), item.getType())){
            setUnitDetails((Unit)item);
        }
    }

    private void setFarmDetails(Item item)
    {
        TextLabel title = createLabel("Food Usage:", 160, 12);
        TextLabel grown = createLabel("    Grown: XXX", 160, 12);
        TextLabel used = createLabel("    Used: XXX", 160, 12);

        GridPane detailsContainer = new GridPane(1, 3);
        detailsContainer.setSize(160, 100);
        detailsContainer.setCellSpacing(4);
        detailsContainer.setCell(title, 0, 0);
        detailsContainer.setCell(grown, 0, 1);
        detailsContainer.setCell(used, 0, 2);
        detailsContainer.setCellWidthMinimum(160);
        detailsContainer.setCellHeightMinimum(12);

        setCell(detailsContainer, 0, 1);
    }

    private void setTownHallDetails(Item item)
    {
        TextLabel production = createLabel("Production:", 160, 12);
        TextLabel gold = createLabel("    Gold: XXX", 160, 12);
        TextLabel lumber = createLabel("    Lumber: XXX", 160, 12);
        TextLabel oil = createLabel("    Oil: XXX", 160, 12);

        GridPane detailsContainer = new GridPane(1, 4);
        detailsContainer.setSize(160, 100);
        detailsContainer.setCellSpacing(4);
        detailsContainer.setCell(production, 0, 0);
        detailsContainer.setCell(gold, 0, 1);
        detailsContainer.setCell(lumber, 0, 2);
        detailsContainer.setCell(oil, 0, 3);
        detailsContainer.setCellWidthMinimum(160);
        detailsContainer.setCellHeightMinimum(12);

        setCell(detailsContainer, 0, 1);
    }

    private void setGoldMineDetails(Item item)
    {
        TextLabel title = createLabel("Gold Left: XXX", 160, 12);

        GridPane detailsContainer = new GridPane(1, 1);
        detailsContainer.setSize(160, 100);
        detailsContainer.setCellSpacing(4);
        detailsContainer.setCell(title, 0, 0);
        detailsContainer.setCellWidthMinimum(160);
        detailsContainer.setCellHeightMinimum(12);

        setCell(detailsContainer, 0, 1);
    }

    private void setUnitDetails(Unit unit)
    {
        TextLabel armour = createDetailsLabel("Armour", unit.getArmour());
        TextLabel damage = createDetailsLabel("Damage", unit.getDamageMinimum(), unit.getDamageMaximum());
        TextLabel range = createDetailsLabel("Range", unit.getRange());
        TextLabel sight = createDetailsLabel("Sight", unit.getSight());
        TextLabel speed = createDetailsLabel("Speed", unit.getSpeed());

        GridPane detailsContainer = new GridPane(1, 5);
        detailsContainer.setSize(160, 100);
        detailsContainer.setCellSpacing(4);
        detailsContainer.setCell(armour, 0, 0);
        detailsContainer.setCell(damage, 0, 1);
        detailsContainer.setCell(range, 0, 2);
        detailsContainer.setCell(sight, 0, 3);
        detailsContainer.setCell(speed, 0, 4);
        detailsContainer.setCellWidthMinimum(160);
        detailsContainer.setCellHeightMinimum(12);

        setCell(detailsContainer, 0, 1);
    }

    //TODO: Localize
    private TextLabel createDetailsLabel(String prefix, float suffix)
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(prefix);
        stringBuilder.append(": ");
        stringBuilder.append(Math.round(suffix));
        return createLabel(stringBuilder.toString(), 160, 12);
    }

    //TODO: Localize
    private TextLabel createDetailsLabel(String prefix, float suffixMin, float suffixMax)
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(prefix);
        stringBuilder.append(": ");
        stringBuilder.append(Math.round(suffixMin));
        stringBuilder.append("-");
        stringBuilder.append(Math.round(suffixMax));
        return createLabel(stringBuilder.toString(), 160, 12);
    }

    private TextLabel createLabel(String text, float width, float height)
    {
        TextLabel result = new TextLabel();
        result.setText(text);
        result.setSize(width, height);
        return result;
    }
}
